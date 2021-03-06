package es.programahermes.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.programahermes.MySQL;

public class Stats implements CommandExecutor {

	public double getDecimal(int numeroDecimales, double decimal) {
		decimal = decimal * (java.lang.Math.pow(10, numeroDecimales));
		decimal = java.lang.Math.round(decimal);
		decimal = decimal / java.lang.Math.pow(10, numeroDecimales);

		return decimal;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("stats")) {

			Player player = (Player) sender;
			int points = (int) MySQL.getPoints(player.getName());
			int level = MySQL.getLevel(player.getName());
			String habilidad = MySQL.getHability(player.getName());
			if (args.length == 0) {

				player.sendMessage(ChatColor.GOLD + "[Hermes Core] "
						+ ChatColor.GREEN + "Ahora mismo est�s a nivel "
						+ level + " de tu habilidad principal "
						+ habilidad.toLowerCase() + " y tienes " + points
						+ " puntos.");
				return true;
			}

			if (args.length == 1) {

				Player target = Bukkit.getPlayer(args[1]);
				double points1 = MySQL.getPoints(target.getName());
				int level1 = MySQL.getLevel(target.getName());
				String habilidad1 = MySQL.getHability(target.getName());
				if (player.hasPermission("hermescore.stats") || player.isOp()) {
					if (MySQL.dbContanisPlayer(target.getName())) {
						player.sendMessage(ChatColor.GOLD + "[Hermes Core] "
								+ ChatColor.GREEN + " El jugador "
								+ target.getName() + " est� a nivel " + level1
								+ " de su habilidad "
								+ habilidad1.toLowerCase() + " y tiene "
								+ getDecimal(2, points1) + " puntos.");
						return true;
					} else {
						player.sendMessage(ChatColor.RED
								+ "Jugador no encontrado");
						return true;
					}

				} else {
					player.sendMessage(ChatColor.RED
							+ "No tienes permisos suficientes para hacer eso");
					return true;
				}
			}
		}
		return false;
	}
}