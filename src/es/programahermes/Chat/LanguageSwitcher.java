package es.programahermes.Chat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LanguageSwitcher implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("idioma")) {
			if (sender instanceof Player) {
				if (args.length == 1) {
					if (args[0] == "arabe" || args[0] == "árabe") {
						if (sender.hasPermission("hermescore.idiomas.arabe")) {
							HermesChat.idioma.put((Player) sender, "árabe");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas ese idioma");
						}
					}else
					if (args[0] == "alemán" || args[0] == "aleman") {
						if (sender.hasPermission("hermescore.idiomas.aleman")) {
							HermesChat.idioma.put((Player) sender, "álemán");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas ese idioma");
						}
					}else
					if (args[0] == "francés" || args[0] == "frances") {
						if (sender.hasPermission("hermescore.idiomas.frances")) {
							HermesChat.idioma.put((Player) sender, "francés");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas ese idioma");
						}
					}else
					if (args[0] == "inglés" || args[0] == "inglés") {
						if (sender.hasPermission("hermescore.idiomas.ingles")) {
							HermesChat.idioma.put((Player) sender, "inglés");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas ese idioma");
						}
					}else
					if (args[0] == "italiano") {
						if (sender.hasPermission("hermescore.idiomas.italiano")) {
							HermesChat.idioma.put((Player) sender, "italiano");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas ese idioma");
						}
					}else
					if (args[0] == "español") {
						if (sender.hasPermission("hermescore.idiomas.español")) {
							HermesChat.idioma.put((Player) sender, "espanol");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas ese idioma");
						}
					}else
					if (args[0] == "ruso") {
						if (sender.hasPermission("hermescore.idiomas.ruso")) {
							HermesChat.idioma.put((Player) sender, "ruso");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas ese idioma");
						}
					}else
					if (args[0] == "nordico") {
						if (sender.hasPermission("hermescore.idiomas.nordico")) {
							HermesChat.idioma.put((Player) sender, "nordico");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas ese idioma");
						}
					}else
					if (args[0] == "japones" || args[0] == "japonés") {
						if (sender.hasPermission("hermescore.idiomas.japones")) {
							HermesChat.idioma.put((Player) sender, "japonés");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas ese idioma");
						}
					}else{
						sender.sendMessage(ChatColor.DARK_RED+args[0]+" no es un idioma válido");
					}
					
				}
			}
		}
		return false;
	}

}
