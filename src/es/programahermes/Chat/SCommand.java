package es.programahermes.Chat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("s")) {
			if (sender instanceof Player) {
				if (args.length == 0) {
					HermesChat.tono.put((Player) sender, 3);
					sender.sendMessage(ChatColor.GRAY
							+ "Has cambiado a modo susurrar");
					return true;
				} else {
					String msg = "";  

					for(int i = 0; i < args.length; i++){ 
					    String arg = args[i] + " "; 
					    msg = msg + arg; 
					}
					
					HermesChat.chat((Player) sender, msg, 2, "IC",
							HermesChat.idioma.get(sender));
					return true;

				}
			}
		}
		return false;
	}

}