package es.programahermes.Health;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import es.programahermes.Main;
import es.programahermes.MySQL;

public class Fracturas implements Listener {

	static int taskID1;

	@EventHandler
	public void onFall(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			// caida
			if (event.getCause().equals(DamageCause.FALL)) {
				if (event.getDamage() > 1) {
					setFracturaTI(player);

				}
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent event) {
		if (event.getRightClicked() instanceof Player) {
			Player player = event.getPlayer();
			Player target = (Player) event.getRightClicked();
			if (player.getItemInHand().getType().equals(Material.ARROW)) {
				if (player.hasPermission("hermescore.oseo")) {
					if (HealthSQL.FracturaTS(player)) {
						double rdm = Math.random() * 100;
						if (rdm < 85) {
							reconstruccionCheck(
									JavaPlugin.getPlugin(Main.class), player,
									target, "TS");
						} else {
							player.sendMessage(ChatColor.RED
									+ "Desgraciadamente, el proceso ha fallado");
							player.sendMessage(ChatColor.RED
									+ "Pierdes 10 puntos de habilidad");
							target.sendMessage(ChatColor.RED
									+ "Desgraciadamente, el proceso ha fallado");
							target.damage(5);
							MySQL.removePoints(player, 10);
						}
					} else {
						if (HealthSQL.FracturaTI(player)) {
							double rdm = Math.random() * 100;
							if (rdm < 85) {
								reconstruccionCheck(Main.getPlugin(Main.class),
										player, target, "TI");
							} else {
								player.sendMessage(ChatColor.RED
										+ "Desgraciadamente, el proceso ha fallado");
								player.sendMessage(ChatColor.RED
										+ "Pierdes 10 puntos de habilidad");
								target.sendMessage(ChatColor.RED
										+ "Desgraciadamente, el proceso ha fallado");
								target.damage(5);
								MySQL.removePoints(player, 10);
							}
						} else {
							player.sendMessage(ChatColor.GREEN
									+ "¡Buenas noticias! El paciente no tiene ninguna fractura ");
						}
					}
				} else {
					player.sendMessage(ChatColor.DARK_RED
							+ "¡La reconstrucción osea es un proceso muy complicado que solo puede ser realizado por personal cualificado!");
				}
			}
		}
	}

	public void setFracturaTS(Player player) {
		if (HealthSQL.FracturaTS(player)) {
			player.sendMessage(ChatColor.DARK_RED
					+ "¡Ten un poco de cuidado, no haces más que empeorar tu lesión! ¡Qué dolor!");
		} else {
			player.sendMessage(ChatColor.DARK_RED
					+ "¡Acabas de fracturate un hueso en el tren superior! ¡Qué dolor!");
			HealthSQL.setFracturaTS(player, true);
		}

	}

	public void setFracturaTI(Player player) {

		if (HealthSQL.FracturaTI(player)) {
			player.sendMessage(ChatColor.DARK_RED
					+ "¡Ten un poco de cuidado, no haces más que empeorar tu lesión! ¡Qué dolor!");
		} else {
			player.sendMessage(ChatColor.DARK_RED
					+ "¡Acabas de fracturate un hueso en el tren inferior! ¡Qué dolor!");
			HealthSQL.setFracturaTI(player, true);
			player.setWalkSpeed((float) 0.005);
		}

	}

	public static void healFracturaTS(Player player) {
		player.sendMessage(ChatColor.GREEN + "Te recuperas de tu lesión");
		HealthSQL.setFracturaTI(player, false);
	}

	public static void healFracturaTI(Player player) {
		player.sendMessage(ChatColor.GREEN + "Te recuperas de tu lesión");
		player.setWalkSpeed((float) 0.2);
		HealthSQL.setFracturaTI(player, false);
	}

	public static void reconstruccionCheck(final Plugin plugin,
			final Player player, final Player target, final String type) {
		taskID1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,
				new Runnable() {
					@Override
					public void run() {
						int counter = 0;
						counter++;

						while (counter < 10) {
							if (counter == 0) {
								player.sendMessage(ChatColor.GOLD
										+ "Preparando el reconstructor óseo");
								target.sendMessage(ChatColor.GOLD
										+ "Preparando el reconstructor óseo");
							}
							if (counter == 1) {
								player.sendMessage(ChatColor.GOLD
										+ "Iniciando reconstrucción ósea");
								target.sendMessage(ChatColor.GOLD
										+ "Iniciando reconstrucción ósea");
							}
							switch (type) {
							case "TS":
								player.sendMessage(ChatColor.RED
										+ "Reconstrcción ósea del tren superior al "
										+ counter * 10 + "%");
								target.sendMessage(ChatColor.RED
										+ "Reconstrcción ósea del tren superior al "
										+ counter * 10 + "%");

							case "TI":

								player.sendMessage(ChatColor.RED
										+ "Reconstrcción ósea del tren inferior al "
										+ counter * 10 + "%");
								target.sendMessage(ChatColor.RED
										+ "Reconstrcción ósea del tren inferior al "
										+ counter * 10 + "%");
							}
						}

						if (counter == 10) {
							plugin.getServer().getScheduler()
									.cancelTask(taskID1);
							double rdm = Math.random() * 100;
							if (rdm < 20) {
								player.sendMessage(ChatColor.RED
										+ "Desgraciadamente, el proceso ha fallado");
								player.sendMessage(ChatColor.RED
										+ "Pierdes 10 puntos de habilidad");
								target.sendMessage(ChatColor.RED
										+ "Desgraciadamente, el proceso ha fallado");
								target.damage(5);
								MySQL.removePoints(player, 10);
							} else {
								switch (type) {
								case "TS":
									player.sendMessage(ChatColor.GREEN
											+ "¡Reconstrucción ósea completada! ¡Ha sido un éxito! ¡Las fracturas del tren superior han sanado!");
									target.sendMessage(ChatColor.GREEN
											+ "¡Reconstrucción ósea completada! ¡Ha sido un éxito! ¡Tus fracturas del tren superior han sanado!");
									healFracturaTS(target);

								case "TI":
									player.sendMessage(ChatColor.GREEN
											+ "¡Reconstrucción ósea completada! ¡Ha sido todo un éxito! ¡Las fracturas del tren inferior han sanado!");
									target.sendMessage(ChatColor.GREEN
											+ "¡Reconstrucción ósea completada! ¡Ha sido todo un éxito! ¡Tus fracturas del tren inferior han sanado!");
									healFracturaTI(target);

								}
							}
						}

					}
				}, 100L, 20 * 1 * 10);
	}

}
