package es.programahermes.Chat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("o")) {
			if (sender instanceof Player) {
				if (args.length == 0) {
					HermesChat.tono.put((Player) sender, 3);
					sender.sendMessage(ChatColor.DARK_BLUE
							+ "Has cambiado al canal OOC");
					return true;
				} else {
					String msg = "";

					for (int i = 0; i < args.length; i++) {
						String arg = args[i] + " ";
						msg = msg + arg;
					}

					HermesChat.chat((Player) sender, msg, 25, "OOC",
							HermesChat.idioma.get(sender));
					return true;

				}
			}
		}
		return false;
	}

}