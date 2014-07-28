package es.programahermes.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.programahermes.MySQL;

public class SetHability implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if (args.length == 2) {
			if (cmd.getName().equalsIgnoreCase("sethabilidad")) {
				if (player.hasPermission("hermescore.sethabilidad")
						|| player.isOp()) {
					Player target = Bukkit.getPlayer(args[0]);
					String habilidad = args[1];
					if (habilidad.equalsIgnoreCase("Geologia")
							|| habilidad.equalsIgnoreCase("Biologia")
							|| habilidad.equalsIgnoreCase("Tecnica")
							|| habilidad.equalsIgnoreCase("Estructural")
							|| habilidad.equalsIgnoreCase("Quimica")) {
						if (MySQL.dbContanisPlayer(target.getName())) {
							MySQL.setHability(target.getName(), habilidad);
							target.sendMessage(ChatColor.GREEN
									+ player.getName()
									+ " ha cambiado tu habilidad principal a "
									+ habilidad);
							player.sendMessage(ChatColor.GREEN
									+ "Has cambiado la habilidad principal de "
									+ target.getName() + " a " + habilidad);
							return true;
						} else {
							player.sendMessage(ChatColor.RED + target.getName()
									+ " no es un jugador válido");
						}

					} else {
						player.sendMessage(ChatColor.RED + habilidad
								+ " no es una habilidad válida");
						return true;
					}
				} else {
					return false;
				}

			}
		}
		return false;

	}
}
