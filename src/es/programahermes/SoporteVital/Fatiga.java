package es.programahermes.SoporteVital;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.plugin.Plugin;

import es.programahermes.MySQL;
import es.programahermes.PHDS.DeathSQL;
import es.programahermes.Training.TrainingSQL;
import es.programahermes.Utilidades.Miscelaneo;
import es.programahermes.Utilidades.ModiferConverter;
import es.programahermes.Utilidades.Scoreboard;

public class Fatiga implements Listener {

	public static HashMap<Player, Long> sleeped = new HashMap<Player, Long>();
	public static HashMap<Player, Location> walked = new HashMap<Player, Location>();

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		MySQL.addFatiga(event.getPlayer().getName(), 0.02 * ModiferConverter
				.SacalaReverse(TrainingSQL.getFTS(event.getPlayer().getName())));
		Scoreboard.showScore(event.getPlayer());
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		MySQL.addFatiga(event.getPlayer().getName(), 0.02 * ModiferConverter
				.SacalaReverse(TrainingSQL.getFTS(event.getPlayer().getName())));
		Scoreboard.showScore(event.getPlayer());
	}

	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent event) {
		Player player = event.getPlayer();
		if (sleeped.containsKey(player)) {
			sleeped.remove(player);
			sleeped.put(player, System.currentTimeMillis());
		} else {
			sleeped.put(player, System.currentTimeMillis());
		}
	}

	@EventHandler
	public void onSprint(PlayerToggleSprintEvent event) {
		Player player = event.getPlayer();
		if (player.getGameMode().equals(GameMode.SURVIVAL)) {
			if (!player.isSprinting()) {
				if (!(MySQL.getFatiga(player.getName()) >= 100)) {
					MySQL.addFatiga(player.getName(), 0.3 * ModiferConverter
							.SacalaReverse(TrainingSQL.getFTI(event.getPlayer()
									.getName())));
				}
				if (!(MySQL.getSed(player.getName()) <= 0)) {
					MySQL.removeSed(player.getName(), 0.5);
				}
				/*if (!(MySQL.getOxygen(player.getName()) <= 0)) {
					MySQL.removeOxygen(player.getName(), 0.5);
				}*/

				Scoreboard.showScore(player);
				if (MySQL.getFatiga(player.getName()) > 70) {
					event.setCancelled(true);
					player.sendMessage(ChatColor.RED
							+ "Est�s demasiado cansado para hacer un sprint");
				}
			}
		}

	}

	@EventHandler
	public void onBedLeave(PlayerBedLeaveEvent event) {
		Player player = event.getPlayer();
		long timeSleeped = System.currentTimeMillis() - sleeped.get(player);
		if (timeSleeped >= 2 * 1000) {
			double percentage = (timeSleeped * 0.13) / 1000;
			player.sendMessage(ChatColor.RED
					+ "Tu fatiga se ha reducido en un " + percentage + "%");
			MySQL.removeFatiga(player.getName(), percentage);

		} else {
			player.sendMessage(ChatColor.RED
					+ "No has descansado suficiente para reducir tu cansancio");
		}

	}

	public static void waitFatigaCheck(Plugin plugin) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {

				for (Player player : Bukkit.getOnlinePlayers()) {
					if (player.getGameMode().equals(GameMode.SURVIVAL)) {
						if (!DeathSQL.isInLimbo(player.getName())) {
							Location loc = player.getLocation();
							if (walked.containsKey(player)) {

								int toX = (int) loc.getX();
								int toY = (int) loc.getY();
								int toZ = (int) loc.getZ();

								int fromX = (int) walked.get(player).getX();
								int fromY = (int) walked.get(player).getY();
								int fromZ = (int) walked.get(player).getZ();

								// misma loc
								if (fromX == toX && fromY == toY
										&& fromZ == toZ) {
									MySQL.removeFatiga(player.getName(), 0.75);

									// otra loc
								} else {
									MySQL.addFatiga(
											player.getName(),
											0.1 * ModiferConverter.SacalaReverse(TrainingSQL
													.getFTI(player.getName())));

								}
								walked.remove(player);
								walked.put(player, player.getLocation());
							} else {
								walked.put(player, player.getLocation());
							}

							if (MySQL.getFatiga(player.getName()) > 85) {
								Miscelaneo.setWalkSpeed(player, 0.1);
							}
							// fracturas, anemia, desmayos, etc
							if ((player.getWalkSpeed() < 0.2)
									&& (MySQL.getFatiga(player.getName()) < 85)) {
								Miscelaneo.setWalkSpeed(player, 0.2);
							}
							Scoreboard.showScore(player);
						}

					}
				}

			}
		}, 100L, 20 * 4);
	}
}
