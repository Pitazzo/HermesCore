package es.programahermes.Chat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("h")) {
			if (sender instanceof Player) {
				if (args.length == 0) {

					if (HermesChat.tono.get(sender) == 10) {
						sender.sendMessage("Ya te encuentras en modo hablar");
					} else {
						HermesChat.tono.put((Player) sender, 10);
						HermesChat.channel.put((Player) sender, "ic");
						sender.sendMessage("Has cambiado a modo hablar");
					}

					return true;
				} else {

					String msg = "";

					for (int i = 0; i < args.length; i++) {
						String arg = args[i] + " ";
						msg = msg + arg;
					}

					HermesChat.chat((Player) sender, msg, 10, "ic",
							HermesChat.idioma.get(sender));
					return true;

				}
			}
		}
		return false;
	}
	
}
