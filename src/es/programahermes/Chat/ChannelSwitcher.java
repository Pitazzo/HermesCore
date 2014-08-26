package es.programahermes.Chat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChannelSwitcher implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("canal")) {
			if (sender instanceof Player) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("IC")) {
						HermesChat.channel.put((Player) sender, "ic");
						HermesChat.tono.put((Player) sender, 10); 
						sender.sendMessage(ChatColor.GOLD+"Has cambiado al canal IC");
					} else if (args[0].equalsIgnoreCase("OOC")) {
						HermesChat.channel.put((Player) sender, "ooc");
						HermesChat.tono.put((Player) sender, 25);
						sender.sendMessage(ChatColor.BLUE+"Has cambiado al canal OOC");
					} else {
						sender.sendMessage(ChatColor.DARK_RED + args[0]
								+ " no es un canal válido");
					}
					return true;
				}
			}
		}
		return false;
	}

}
