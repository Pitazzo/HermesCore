package es.programahermes.SoporteVital;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.plugin.Plugin;

import es.programahermes.MySQL;
import es.programahermes.Training.TrainingSQL;
import es.programahermes.Utilidades.ModiferConverter;
import es.programahermes.Utilidades.Scoreboard;

public class Fatiga implements Listener {

	public static HashMap<Player, Long> walked = new HashMap<Player, Long>();
	public static HashMap<Player, Long> sleeped = new HashMap<Player, Long>();

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		MySQL.addFatiga(event.getPlayer(), 0.02*ModiferConverter.SacalaReverse(TrainingSQL.getFTS(event.getPlayer())));
		Scoreboard.showScore(event.getPlayer());
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		MySQL.addFatiga(event.getPlayer(), 0.02*ModiferConverter.SacalaReverse(TrainingSQL.getFTS(event.getPlayer())));
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
				if (!(MySQL.getFatiga(player) >= 100)) {
					MySQL.addFatiga(player, 0.3*ModiferConverter.SacalaReverse(TrainingSQL.getFTI(event.getPlayer())));
				}
				if (!(MySQL.getSed(player) <= 0)) {
					MySQL.removeSed(player, 0.5);
				}
				if (!(MySQL.getOxygen(player) <= 0)) {
					MySQL.removeOxygen(player, 0.5);
				}

				Scoreboard.showScore(player);
				if (MySQL.getFatiga(player) > 70) {
					event.setCancelled(true);
					player.sendMessage(ChatColor.RED
							+ "Estás demasiado cansado para hacer un sprint");
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
			MySQL.removeFatiga(player, percentage);

		} else {
			player.sendMessage(ChatColor.RED
					+ "No has descansado suficiente para reducir tu cansancio");
		}

	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		int fromX = (int) event.getFrom().getX();
		int fromY = (int) event.getFrom().getY();
		int fromZ = (int) event.getFrom().getZ();

		int toX = (int) event.getTo().getX();
		int toY = (int) event.getTo().getY();
		int toZ = (int) event.getTo().getZ();

		if (player.getGameMode().equals(GameMode.SURVIVAL)) {
			if (fromX != toX || fromY != toY || fromZ != toZ) {
				MySQL.addFatiga(player, 0.01*ModiferConverter.SacalaReverse(TrainingSQL.getFTI(event.getPlayer())));
				Scoreboard.showScore(player);
				if (MySQL.getFatiga(player) > 85) {
					player.setWalkSpeed((float) 0.1);
				} else {
					player.setWalkSpeed((float) 0.2);
				}
				if (walked.containsValue(player)) {
					walked.remove(player);
					walked.put(player, System.currentTimeMillis());
				} else {
					walked.put(player, System.currentTimeMillis());
				}
			}
		} else {
			return;
		}

	}

	public static void waitFatigaCheck(Plugin plugin) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {

				for (Player player : Bukkit.getOnlinePlayers()) {
					if (player.getGameMode().equals(GameMode.SURVIVAL)) {
						if (walked.containsKey(player)) {
							if (System.currentTimeMillis() - walked.get(player) >= 20 * 1000) {
								if (!(MySQL.getFatiga(player) <= 0)) {
									MySQL.removeFatiga(player, 1.5);
								}

								Scoreboard.showScore(player);
							}
						}
					}
				}

			}
		}, 100L, 20 * 10);
	}

}
