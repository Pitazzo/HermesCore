package es.programahermes.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.programahermes.MySQL;

public class Stats implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("stats")) {

			Player player = (Player) sender;
			double points = MySQL.getPoints(player);
			int level = MySQL.getLevel(player);
			String habilidad = MySQL.getHability(player);
			player.sendMessage(ChatColor.GOLD + "[Hermes Core] "
					+ ChatColor.GREEN + "Ahora mismo estás a nivel " + level
					+ " de tu habilidad principal " + habilidad.toLowerCase()
					+ " y tienes " + points + " puntos.");

			if (args.length == 1) {

				Player target = Bukkit.getPlayer(args[1]);
				double points1 = MySQL.getPoints(target);
				int level1 = MySQL.getLevel(target);
				String habilidad1 = MySQL.getHability(target);
				if (player.hasPermission("hermescore.stats")||player.isOp()) {
					if (MySQL.dbContanisPlayer(target)) {
						player.sendMessage(ChatColor.GOLD + "[Hermes Core] "
								+ ChatColor.GREEN + " El jugador "
								+ target.getName() + " está a nivel " + level1
								+ " de su habilidad "
								+ habilidad1.toLowerCase() + " y tiene "
								+ points1 + " puntos.");
					} else {
						player.sendMessage(ChatColor.RED
								+ "Jugador no encontrado");
					}

				} else {
					player.sendMessage(ChatColor.RED
							+ "No tienes permisos suficientes para hacer eso");
				}
			}
		}
		return false;
	}
}