package es.programahermes.Health;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.Plugin;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;

import es.programahermes.Main;

public class Septicemia implements Listener, CommandExecutor {

	public static HashMap<Player, Long> injured = new HashMap<Player, Long>();
	public static HashMap<Player, Long> healed = new HashMap<Player, Long>();
	static int taskID1;
	private Main plugin;
	public Septicemia(Main plugin){
		this.plugin = plugin;
		}

	@EventHandler
	public void onDamage(WeaponDamageEntityEvent event) {
			Player victim = (Player) event.getVictim();
			if (event.getVictim() instanceof Player) {
				desinfeccionCheck(plugin, victim);
				victim.sendMessage("1 MINUTO");
			}
		
	}

	public static void desinfeccionCheck(final Plugin plugin,
			final Player player) {
		taskID1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,
				new Runnable() {
					@Override
					public void run() {
						double rdm = Math.random() * 100;

						if (injured.containsKey(player)) {
							if (System.currentTimeMillis()
									- injured.get(player) < 1000 * 60 * 1) {
								injured.remove(player);
								player.sendMessage("NO INFECTADO!");
							} else {
								if (rdm > 35) {
									// random
									// setSepticemia
									injured.remove(player);
									player.sendMessage("INFECTADO!");
								}
							}
						} else {
							if (rdm > 35) {
								// random
								// setSepticemia
								player.sendMessage("INFECTADO!");
							}

						}
						plugin.getServer().getScheduler().cancelTask(taskID1);

					}
				}, 100L, 20 * 60 * 1);
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
				if (player.getItemInHand().getType().equals(Material.APPLE)) {
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

	public void setSepsis(Player player) {
		
	}
}
