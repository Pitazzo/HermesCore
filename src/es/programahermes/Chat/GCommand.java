package es.programahermes.Chat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.programahermes.Main;

public class GCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("g")) {
			if (sender instanceof Player) {
				if (args.length == 0) {

					if (HermesChat.tono.get(sender) == 20) {
						sender.sendMessage(ChatColor.DARK_RED
								+ "Ya te encuentras en modo gritar");
					} else {
						HermesChat.tono.put((Player) sender, 20);
						HermesChat.channel.put((Player) sender, "ic");
						sender.sendMessage(ChatColor.DARK_RED
								+ "Has cambiado a modo gritar");
						Main.JugadoresConfig.set(sender.getName(), "Test");
					}

					return true;
				} else {

					String msg = "";

					for (int i = 0; i < args.length; i++) {
						String arg = args[i] + " ";
						msg = msg + arg;
					}

					HermesChat.chat((Player) sender, msg, 20, "ic",
							HermesChat.idioma.get(sender));
					return true;

				}
			}
		}
		return false;
	}
}
