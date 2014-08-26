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
					if (args[0].equalsIgnoreCase("arabe") || args[0].equalsIgnoreCase("árabe")) {
						if (sender.hasPermission("hermescore.idiomas.arabe")) {
							HermesChat.idioma.put((Player) sender, "árabe");
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablarás en "+ChatColor.BLUE+"árabe");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quizás deberías aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("alemán") || args[0].equalsIgnoreCase("aleman")) {
						if (sender.hasPermission("hermescore.idiomas.aleman")) {
							HermesChat.idioma.put((Player) sender, "alemán");
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablarás en "+ChatColor.BLUE+"alemán");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quizás deberías aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("inglés") || args[0].equalsIgnoreCase("ingles")) {
						if (sender.hasPermission("hermescore.idiomas.ingles")) {
							HermesChat.idioma.put((Player) sender, "inglés");
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablarás en "+ChatColor.BLUE+"inglés");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quizás deberías aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("japonés") || args[0].equalsIgnoreCase("japones")) {
						if (sender.hasPermission("hermescore.idiomas.japones")) {
							HermesChat.idioma.put((Player) sender, "japonés");
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablarás en "+ChatColor.BLUE+"japonés");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quizás deberías aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("frances") || args[0].equalsIgnoreCase("francés")) {
						if (sender.hasPermission("hermescore.idiomas.frances")) {
							HermesChat.idioma.put((Player) sender, "francés");
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablarás en "+ChatColor.BLUE+"francés");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quizás deberías aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("italiano")) {
						if (sender.hasPermission("hermescore.idiomas."+args[0])) {
							HermesChat.idioma.put((Player) sender, "args[0]");
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablarás en "+ChatColor.BLUE+"italiano");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quizás deberías aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("chino")) {
						if (sender.hasPermission("hermescore.idiomas."+args[0])) {
							HermesChat.idioma.put((Player) sender, args[0]);
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablarás en "+ChatColor.BLUE+"chino");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quizás deberías aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("ruso")) {
						if (sender.hasPermission("hermescore.idiomas."+args[0])) {
							HermesChat.idioma.put((Player) sender, args[0]);
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablarás en "+ChatColor.BLUE+"ruso");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quizás deberías aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("español")) {
						if (sender.hasPermission("hermescore.idiomas.espanol")) {
							HermesChat.idioma.put((Player) sender, args[0]);
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablarás en "+ChatColor.BLUE+"español");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quizás deberías aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("sueco")) {
						if (sender.hasPermission("hermescore.idiomas."+args[0])) {
							HermesChat.idioma.put((Player) sender, args[0]);
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablarás en "+ChatColor.BLUE+"sueco");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quizás deberías aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("hindi")) {
						if (sender.hasPermission("hermescore.idiomas."+args[0])) {
							HermesChat.idioma.put((Player) sender, args[0]);
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablarás en "+ChatColor.BLUE+"hindi");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quizás deberías aprender...");
						}
					
					}else{
						sender.sendMessage(ChatColor.DARK_RED+"El "+args[0]+" no es un idioma válido");
					}
					
					
					return true;
				}
			}
		}
		return false;
	}

}
