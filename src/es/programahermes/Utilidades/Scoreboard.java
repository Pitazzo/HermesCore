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
import es.programahermes.PHDS.DeathSQL;
import es.programahermes.SoporteVital.Oxygen;

public class Scoreboard {

	static ScoreboardManager manager = Bukkit.getScoreboardManager();

	public static void showScore(Player player) {

		int sed = (int) MySQL.getSed(player.getName());
		int res = (int) MySQL.getResidual(player.getName());
		int oxy = (int) MySQL.getOxygen(player.getName());
		int fat = (int) MySQL.getFatiga(player.getName());
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		org.bukkit.scoreboard.Scoreboard board = manager.getNewScoreboard();
		org.bukkit.scoreboard.Scoreboard blank = manager.getNewScoreboard();
		Objective obj = board.registerNewObjective("test", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		Score residual = null;
		Score hidratacion = null;
		Score hambre = null;
		Score oxygen = null;
		Score fatiga = null;
		Score timeleft = null;
		int min = DeathSQL.getTimeLeft(player.getName()) / 60;
		int sec = DeathSQL.getTimeLeft(player.getName()) - min * 60;
		int food = (100 * player.getFoodLevel() / 20);

		if (DeathSQL.isInLimbo(player.getName())) {
			obj.setDisplayName(ChatColor.BOLD + "Tiempo restante");
			if (sec == 0) {
				timeleft = obj.getScore(Bukkit
						.getOfflinePlayer(ChatColor.DARK_RED + "0" + min
								+ ":00"));
			} else {
				timeleft = obj.getScore(Bukkit
						.getOfflinePlayer(ChatColor.DARK_RED + "0" + min + ":"
								+ sec));
			}

			timeleft.setScore(0);
		} else {
			// sed
			obj.setDisplayName(ChatColor.BOLD + "Soporte Vital");
			if (MySQL.getSed(player.getName()) > 70) {
				hidratacion = obj.getScore(Bukkit
						.getOfflinePlayer(ChatColor.GREEN + "Hidrata: " + sed
								+ "%"));
			} else {
				if (MySQL.getSed(player.getName()) < 30) {
					hidratacion = obj.getScore(Bukkit
							.getOfflinePlayer(ChatColor.RED + "Hidrata: " + sed
									+ "%"));
				} else {
					hidratacion = obj.getScore(Bukkit
							.getOfflinePlayer(ChatColor.GOLD + "Hidrata: "
									+ sed + "%"));

				}
			}

			// fatiga
			if (MySQL.getFatiga(player.getName()) < 50) {
				fatiga = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN
						+ "Fatiga: " + fat + "%"));
			} else {
				if (MySQL.getFatiga(player.getName()) > 70) {
					fatiga = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.RED
							+ "Fatiga: " + fat + "%"));
				} else {
					fatiga = obj.getScore(Bukkit
							.getOfflinePlayer(ChatColor.GOLD + "Fatiga: " + fat
									+ "%"));

				}
			}

			// hambre
			if (food > 70) {
				hambre = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN
						+ "Hambre: " + food + "%"));
			} else {
				if (food < 30) {
					hambre = obj.getScore(Bukkit
							.getOfflinePlayer(ChatColor.GOLD + "Hambre: "
									+ food + "%"));
				} else {
					hambre = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.RED
							+ "Hambre: " + food + "%"));

				}
			}

			// residuos
			if (MySQL.getResidual(player.getName()) < 50) {
				residual = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN
						+ "Residuos: " + res + "%"));
			} else {
				if (MySQL.getResidual(player.getName()) > 70) {
					residual = obj.getScore(Bukkit
							.getOfflinePlayer(ChatColor.RED + "Residuos: "
									+ res + "%"));
				} else {
					residual = obj.getScore(Bukkit
							.getOfflinePlayer(ChatColor.GOLD + "Residuos: "
									+ res + "%"));

				}
			}

			if (Oxygen.hasSuit(player)) {
				if (MySQL.getOxygen(player.getName()) > 70) {
					oxygen = obj.getScore(Bukkit
							.getOfflinePlayer(ChatColor.GREEN + "Oxígeno: "
									+ oxy + " L"));
				} else {
					if (MySQL.getOxygen(player.getName()) < 30) {
						oxygen = obj.getScore(Bukkit
								.getOfflinePlayer(ChatColor.RED + "Oxígeno: "
										+ oxy + " L"));
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

		}
		if (player.getInventory().contains(Material.NETHER_BRICK_ITEM)
				|| DeathSQL.isInLimbo(player.getName())) {
			player.setScoreboard(board);
		} else {
			player.setScoreboard(blank);
		}
	}

}
