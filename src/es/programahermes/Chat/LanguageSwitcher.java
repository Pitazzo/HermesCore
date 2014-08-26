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
					if (args[0].equalsIgnoreCase("arabe") || args[0].equalsIgnoreCase("�rabe")) {
						if (sender.hasPermission("hermescore.idiomas.arabe")) {
							HermesChat.idioma.put((Player) sender, "�rabe");
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablar�s en "+ChatColor.BLUE+"�rabe");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quiz�s deber�as aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("alem�n") || args[0].equalsIgnoreCase("aleman")) {
						if (sender.hasPermission("hermescore.idiomas.aleman")) {
							HermesChat.idioma.put((Player) sender, "alem�n");
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablar�s en "+ChatColor.BLUE+"alem�n");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quiz�s deber�as aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("ingl�s") || args[0].equalsIgnoreCase("ingles")) {
						if (sender.hasPermission("hermescore.idiomas.ingles")) {
							HermesChat.idioma.put((Player) sender, "ingl�s");
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablar�s en "+ChatColor.BLUE+"ingl�s");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quiz�s deber�as aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("japon�s") || args[0].equalsIgnoreCase("japones")) {
						if (sender.hasPermission("hermescore.idiomas.japones")) {
							HermesChat.idioma.put((Player) sender, "japon�s");
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablar�s en "+ChatColor.BLUE+"japon�s");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quiz�s deber�as aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("frances") || args[0].equalsIgnoreCase("franc�s")) {
						if (sender.hasPermission("hermescore.idiomas.frances")) {
							HermesChat.idioma.put((Player) sender, "franc�s");
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablar�s en "+ChatColor.BLUE+"franc�s");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quiz�s deber�as aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("italiano")) {
						if (sender.hasPermission("hermescore.idiomas."+args[0])) {
							HermesChat.idioma.put((Player) sender, "args[0]");
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablar�s en "+ChatColor.BLUE+"italiano");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quiz�s deber�as aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("chino")) {
						if (sender.hasPermission("hermescore.idiomas."+args[0])) {
							HermesChat.idioma.put((Player) sender, args[0]);
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablar�s en "+ChatColor.BLUE+"chino");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quiz�s deber�as aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("ruso")) {
						if (sender.hasPermission("hermescore.idiomas."+args[0])) {
							HermesChat.idioma.put((Player) sender, args[0]);
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablar�s en "+ChatColor.BLUE+"ruso");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quiz�s deber�as aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("espa�ol")) {
						if (sender.hasPermission("hermescore.idiomas.espanol")) {
							HermesChat.idioma.put((Player) sender, args[0]);
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablar�s en "+ChatColor.BLUE+"espa�ol");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quiz�s deber�as aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("sueco")) {
						if (sender.hasPermission("hermescore.idiomas."+args[0])) {
							HermesChat.idioma.put((Player) sender, args[0]);
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablar�s en "+ChatColor.BLUE+"sueco");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quiz�s deber�as aprender...");
						}
					
					}else if (args[0].equalsIgnoreCase("hindi")) {
						if (sender.hasPermission("hermescore.idiomas."+args[0])) {
							HermesChat.idioma.put((Player) sender, args[0]);
							sender.sendMessage(ChatColor.GREEN+"De ahora en adelante hablar�s en "+ChatColor.BLUE+"hindi");
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No dominas el "+args[0]+", quiz�s deber�as aprender...");
						}
					
					}else{
						sender.sendMessage(ChatColor.DARK_RED+"El "+args[0]+" no es un idioma v�lido");
					}
					
					
					return true;
				}
			}
		}
		return false;
	}

}
