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
					if (args[0] == "arabe" || args[0] == "�rabe") {
						if (sender.hasPermission("hermescore.idiomas.arabe")) {
							HermesChat.idioma.put((Player) sender, "�rabe");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas ese idioma");
						}
					}else
					if (args[0] == "alem�n" || args[0] == "aleman") {
						if (sender.hasPermission("hermescore.idiomas.aleman")) {
							HermesChat.idioma.put((Player) sender, "�lem�n");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas ese idioma");
						}
					}else
					if (args[0] == "franc�s" || args[0] == "frances") {
						if (sender.hasPermission("hermescore.idiomas.frances")) {
							HermesChat.idioma.put((Player) sender, "franc�s");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas ese idioma");
						}
					}else
					if (args[0] == "ingl�s" || args[0] == "ingl�s") {
						if (sender.hasPermission("hermescore.idiomas.ingles")) {
							HermesChat.idioma.put((Player) sender, "ingl�s");
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
					if (args[0] == "espa�ol") {
						if (sender.hasPermission("hermescore.idiomas.espa�ol")) {
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
					if (args[0] == "japones" || args[0] == "japon�s") {
						if (sender.hasPermission("hermescore.idiomas.japones")) {
							HermesChat.idioma.put((Player) sender, "japon�s");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas ese idioma");
						}
					}else{
						sender.sendMessage(ChatColor.DARK_RED+args[0]+" no es un idioma v�lido");
					}
					
				}
			}
		}
		return false;
	}

}
