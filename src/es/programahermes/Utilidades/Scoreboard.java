package es.programahermes.Utilidades;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

import es.programahermes.MySQL;
import es.programahermes.SoporteVital.Oxygen;

public class Scoreboard {

	static ScoreboardManager manager = Bukkit.getScoreboardManager();

	public static void showScore(Player player) {

		int sed = (int) MySQL.getSed(player);
		int res = (int) MySQL.getResidual(player);
		int oxy = (int) MySQL.getOxygen(player);
		int fat = (int) MySQL.getFatiga(player);
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		org.bukkit.scoreboard.Scoreboard board = manager.getNewScoreboard();
		org.bukkit.scoreboard.Scoreboard blank = manager.getNewScoreboard();
		Objective obj = board.registerNewObjective("test", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(ChatColor.BOLD + "Soporte Vital");
		Score residual = null;
		Score hidratacion = null;
		Score hambre = null;
		Score oxygen = null;
		Score fatiga = null;
		int food = (100 * player.getFoodLevel() / 20);

		// sed
		if (MySQL.getSed(player) > 70) {
			hidratacion = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN
					+ "Hidrata: " + sed + "%"));
		} else {
			if (MySQL.getSed(player) < 30) {
				hidratacion = obj.getScore(Bukkit
						.getOfflinePlayer(ChatColor.RED + "Hidrata: " + sed
								+ "%"));
			} else {
				hidratacion = obj.getScore(Bukkit
						.getOfflinePlayer(ChatColor.GOLD + "Hidrata: " + sed
								+ "%"));

			}
		}

		// fatiga
		if (MySQL.getFatiga(player) < 50) {
			fatiga = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN
					+ "Fatiga: " + fat + "%"));
		} else {
			if (MySQL.getFatiga(player) > 70) {
				fatiga = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.RED
						+ "Fatiga: " + fat + "%"));
			} else {
				fatiga = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD
						+ "Fatiga: " + fat + "%"));

			}
		}
		
		
		// hambre
		if (food > 70) {
			hambre = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN
					+ "Hambre: " + food + "%"));
		} else {
			if (food < 30) {
				hambre = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD
						+ "Hambre: " + food + "%"));
			} else {
				hambre = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.RED
						+ "Hambre: " + food + "%"));

			}
		}

		// residuos
		if (MySQL.getResidual(player) < 50) {
			residual = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN
					+ "Residuos: " + res + "%"));
		} else {
			if (MySQL.getResidual(player) > 70) {
				residual = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD
						+ "Residuos: " + res + "%"));
			} else {
				residual = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.RED
						+ "Residuos: " + res + "%"));

			}
		}

		if (Oxygen.hasSuit(player)) {
			if (MySQL.getOxygen(player) > 70) {
				oxygen = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN
						+ "Oxígeno: " + oxy + " L"));
			} else {
				if (MySQL.getOxygen(player) < 30) {
					oxygen = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.RED
							+ "Oxígeno: " + oxy + " L"));
				} else {
					oxygen = obj.getScore(Bukkit
							.getOfflinePlayer(ChatColor.GOLD + "Oxígeno: "
									+ oxy + " L"));

				}

			}
		} else {
			oxygen = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD
					+ "Oxígeno: -"));
		}

		hambre.setScore(0);
		residual.setScore(0);
		hidratacion.setScore(0);
		oxygen.setScore(0);
		fatiga.setScore(0);
		if (player.getInventory().contains(Material.NETHER_BRICK_ITEM)) {
			player.setScoreboard(board);
		}else{
			player.setScoreboard(blank);
		}
	}

}
