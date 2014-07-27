package es.programahermes.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.programahermes.MySQL;

public class PointsCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("puntos")) {
			if (args.length == 0) {
				double points = MySQL.getPoints(player);
				player.sendMessage(ChatColor.GREEN + "Ahora mismo tienes "
						+ points + " puntos");
				return true;

			} else {

				Player target = Bukkit.getPlayer(args[1]);
				double points = Double.parseDouble(args[2]);

				if (player.hasPermission("hermescore.editpoints")
						|| player.isOp()) {
					if (args[0].equalsIgnoreCase("dar")) {
						if (!MySQL.dbContanisPlayer(player.getName())) {
							player.sendMessage(ChatColor.RED
									+ "El jugador no existe");
							return true;
						}
						MySQL.addPoints(target, points);
						target.sendMessage(ChatColor.GREEN + "¡"
								+ player.getName() + " te ha dado " + points
								+ " puntos!");
						player.sendMessage(ChatColor.GREEN + "Enviados "
								+ points + " puntos a " + target.getName());
						return true;
					}
					if (args[0].equalsIgnoreCase("quitar")) {
						if (!MySQL.dbContanisPlayer(player.getName())) {
							player.sendMessage(ChatColor.RED
									+ "El jugador no existe");
							return true;
						}
						MySQL.removePoints(target, points);
						target.sendMessage(ChatColor.GREEN + "¡"
								+ player.getName() + " te ha quitado " + points
								+ " puntos!");
						player.sendMessage(ChatColor.GREEN + "Quitados "
								+ points + " puntos a " + target.getName());
						return true;
					}
					
					if(args[0].equalsIgnoreCase("set")){
						
						if (!MySQL.dbContanisPlayer(player.getName())) {
							player.sendMessage(ChatColor.RED
									+ "El jugador no existe");
							return true;
						}
						MySQL.setPoints(player, points);
						target.sendMessage(ChatColor.GREEN + "¡"
								+ player.getName() + " te ha seteado " + points
								+ " puntos!");
						player.sendMessage(ChatColor.GREEN + "Seteados "
								+ points + " puntos a " + target.getName());
						return true;
					}

				}
			}
			
		}
		return false;
	}
}
