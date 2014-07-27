package es.programahermes.Health;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;

import es.programahermes.Main;
import es.programahermes.Utilidades.Miscelaneo;

public class Septicemia implements Listener, CommandExecutor {

	public static HashMap<Player, Long> injured = new HashMap<Player, Long>();
	public static HashMap<Player, Long> healed = new HashMap<Player, Long>();
	static int taskID1;
	static int taskID2;

	@EventHandler
	public void onDamage(WeaponDamageEntityEvent event) {
		if (event.getVictim() instanceof Player) {
			Player victim = (Player) event.getVictim();
			desinfeccionCheck(JavaPlugin.getPlugin(Main.class), victim);
		}

	}

	public static void desinfeccionCheck(final Plugin plugin,
			final Player player) {
		taskID1 = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,
				new Runnable() {
					@Override
					public void run() {
						double rdm = Math.random() * 100;

						if (injured.containsKey(player)) {
							if (System.currentTimeMillis()
									- injured.get(player) < 1000 * 30) {
								injured.remove(player);

							} else {
								if (rdm > 35) {
									// random
									// setSepticemia
									injured.remove(player);
									setSepsis(player);

								} else {

								}
							}
						} else {
							if (rdm > 35) {

								setSepsis(player);
							} else {

							}

						}
					}
				}, 20 * 60 * 8L);
	}

	// desinfectar
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent event) {

		Player player = event.getPlayer();
		if (event.getRightClicked() instanceof Player) {
			Player target = (Player) event.getRightClicked();
			if (player.getItemInHand().getType().equals(Material.BLAZE_ROD)) {
				if (player.hasPermission("hermescore.desinfectar")) {
					if (player.getItemInHand().getAmount() > 1) {
						int amount = player.getItemInHand().getAmount();
						player.getItemInHand().setAmount(amount - 1);
					} else {
						player.getItemInHand().setAmount(0);
					}
					if (injured.containsKey(target)) {
						injured.remove(target);
						injured.put(target, System.currentTimeMillis());
					} else {
						injured.put(target, System.currentTimeMillis());
					}

					player.sendMessage(ChatColor.GREEN
							+ "Has desinfectado correctamente la herida");
					target.sendMessage(ChatColor.GREEN
							+ "Tu herida ha sido desinfectada");
				} else {
					player.sendMessage(ChatColor.RED
							+ "La desinfección de heridas solo puede ser realizada por personal especializado");
				}
			} else {
				if (player.getItemInHand().getType().equals(Material.APPLE)) {
					if (player.getItemInHand().getAmount() > 1) {
						int amount = player.getItemInHand().getAmount();
						player.getItemInHand().setAmount(amount - 1);
					} else {
						player.getItemInHand().setAmount(0);
					}
					double rdm = Math.random() * 100;
					if (rdm > 40) {
						injured.put(target, System.currentTimeMillis());
						player.sendMessage(ChatColor.GREEN
								+ "Has aplicado un vendaje");
						target.sendMessage(ChatColor.GREEN
								+ "Te ha sido aplicado un vendaje");
					} else {
						player.sendMessage(ChatColor.GREEN
								+ "Te has aplicado un vendaje");
						target.sendMessage(ChatColor.GREEN
								+ "Te ha sido aplicado un vendaje");
					}
				}

			}
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("vendarse")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.getItemInHand().getType().equals(Material.PAPER)) {
					if (player.getItemInHand().getAmount() > 1) {
						int amount = player.getItemInHand().getAmount();
						player.getItemInHand().setAmount(amount - 1);
					} else {
						player.getItemInHand().setAmount(0);
					}
					double rdm = Math.random() * 100;
					if (rdm > 40) {
						injured.put(player, System.currentTimeMillis());
						player.sendMessage(ChatColor.GREEN
								+ "Te has aplicado un vendaje");
					} else {
						player.sendMessage(ChatColor.GREEN
								+ "Te has aplicado un vendaje");
					}
				}
			} else {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	public static void setSepsis(Player player) {
		if (!HealthSQL.Septicemia(player)) {
			HealthSQL.setSepticemia(player, true);
			sepsis(JavaPlugin.getPlugin(Main.class), player);
			player.setMaxHealth(player.getMaxHealth() - 8);
			player.sendMessage(ChatColor.RED
					+ "Parece ser que alguna de tus heridas se ha infectado. Busca un médico o podrías tener graves problemas.");
		} else {
			return;
		}
	}

	public void healSepsis(Player player) {
		if (HealthSQL.Septicemia(player)) {
			HealthSQL.setSepticemia(player, false);
			player.sendMessage(ChatColor.GREEN
					+ "Parece que tu septicemia comienza a remitir...");
			Miscelaneo.setWalkSpeed(player, 0.2);
			player.setMaxHealth(20);
		} else {
			return;
		}

	}

	public static void sepsis(Plugin plugin, final Player player) {
		taskID2 = Bukkit.getServer().getScheduler()
				.scheduleSyncRepeatingTask(plugin, new Runnable() {
					@Override
					public void run() {
						if (HealthSQL.Septicemia(player)) {
							player.addPotionEffect(new PotionEffect(
									PotionEffectType.CONFUSION, 20 * 40, 2));
							player.addPotionEffect(new PotionEffect(
									PotionEffectType.HUNGER, 20 * 30, 1));
							Miscelaneo.setWalkSpeed(player, 0.1);
							player.sendMessage("Otra vez no...");
							player.playSound(player.getLocation(),
									Sound.AMBIENCE_THUNDER, (float) 1.0,
									(float) 1.0);
						} else {
							Bukkit.getScheduler().cancelTask(taskID2);
							Miscelaneo.setWalkSpeed(player, 0.2);
							player.setMaxHealth(20);

						}

					}
				}, 200L, 20 * 60 * 3L);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (HealthSQL.Septicemia(event.getPlayer())) {
			sepsis(JavaPlugin.getPlugin(Main.class), event.getPlayer());
		} else {
			return;
		}

	}
}
