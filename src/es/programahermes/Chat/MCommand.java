package es.programahermes.Chat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.programahermes.MySQL;

public class MCommand implements CommandExecutor {

	public boolean onCommand(CommandSender commnadSender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("me")) {
			if (commnadSender instanceof Player) {
				Player sender = (Player) commnadSender;
				if (args.length > 0) {
					String msg = "";

					for (int i = 0; i < args.length; i++) {
						String arg = args[i] + " ";
						msg = msg + arg;
					}

					String name;
					
					
					for (Player player : ((Player) sender).getWorld().getPlayers()) {
						if(((Player) sender).getLocation().distance(player.getLocation())<25){

							if(sender != player){
								if (!IdentityChat.knowsPlayer(player, sender)) {
									if (MySQL.getGenero(sender.getName())) {

										name = "esa "
												+ IdentityChat.getName(sender,
														player);
									} else {
										name = "ese "
												+ IdentityChat.getName(sender,
														player);
									}
								}else{
									name = IdentityChat.getName(player, sender);
								}
							}else{
								name = MySQL.getICName(sender);
							}
							
						player.sendMessage(ChatColor.BLUE+"*"+name+": "+msg);
						}
					}
					return true;
				}

			}
		}

		return false;
	}
}
