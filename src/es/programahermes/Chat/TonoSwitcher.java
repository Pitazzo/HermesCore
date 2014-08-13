package es.programahermes.Chat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TonoSwitcher implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("tono")) {
			if (sender instanceof Player) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("hablar")) {
						HermesChat.tono.put((Player) sender, 10);
						sender.sendMessage("Has cambiado al tono hablar");
					} else if (args[0].equalsIgnoreCase("susurrar")) {
						HermesChat.tono.put((Player) sender, 2);
						sender.sendMessage(ChatColor.GRAY+"Has cambiado al modo susurrar");
					} else if(args[0].equalsIgnoreCase("gritar")) {
						HermesChat.tono.put((Player) sender, 20);
						sender.sendMessage(ChatColor.GRAY+"Has cambiado al modo gritar");
					}else
						sender.sendMessage(ChatColor.DARK_RED + args[0]
								+ " no es un tono válido");
					}
					return true;
				}
			}
		
		return false;
	}
	
}
