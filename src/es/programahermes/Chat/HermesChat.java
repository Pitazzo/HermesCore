package es.programahermes.Chat;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import es.programahermes.MySQL;

public class HermesChat {

	public static HashMap<Player, String> channel = new HashMap<Player, String>();
	public static HashMap<Player, String> idioma = new HashMap<Player, String>();

	public static void chat(Player sender, String msg, int radius,
			String channel, String language) {
		for (Player player : sender.getWorld().getPlayers()) {
			if (sender.getLocation().distance(player.getLocation()) < radius) {

				String name = IdentityChat.getName(sender, player);
				String genero;
				String cargo = "";
				if (sender.hasPermission("hermescore.admin")) {
					cargo = ChatColor.DARK_RED + "[ADM]";
				}
				if (sender.hasPermission("hermescore.mod")) {
					cargo = ChatColor.BLUE + "[MOD]";
				}

				if (MySQL.getGenero(sender.getName())) {
					genero = "ese";
				} else {
					genero = "esa";
				}

				if (channel.equals("ic")) {
					if (player.hasPermission("hermescore.idiomas." + language)) {
						// todo OK
						player.sendMessage(ChatColor.GOLD + "[IC] " + cargo
								+ ChatColor.WHITE + name + ": " + msg);
					} else {
						if (radius < 3) {
							if (IdentityChat.knowsPlayer(sender, player)) {
								player.sendMessage(ChatColor.GOLD + "[IC] "
										+ ChatColor.WHITE
										+ "Humm... Parece ser que " + name
										+ " está susurrando en " + language);
							} else {
								player.sendMessage(ChatColor.GOLD + "[IC] "
										+ ChatColor.WHITE
										+ "Humm... parece ser que " + genero
										+ " " + name.toString()
										+ " está susurrando en " + language);
							}
						} else if (radius > 16) {
							if (IdentityChat.knowsPlayer(sender, player)) {
								player.sendMessage(ChatColor.GOLD + "[IC] "
										+ ChatColor.WHITE
										+ "Humm... Parece ser que " + name
										+ " está gritando en " + language);
							} else {
								player.sendMessage(ChatColor.GOLD + "[IC] "
										+ ChatColor.WHITE
										+ "Humm... parece ser que " + genero
										+ " " + name.toString()
										+ " está gritando en " + language);
							}
						} else {
							if (IdentityChat.knowsPlayer(sender, player)) {
								player.sendMessage(ChatColor.GOLD + "[IC] "
										+ ChatColor.WHITE
										+ "Humm... Parece ser que " + name
										+ " está hablando " + language);
							} else {
								player.sendMessage(ChatColor.GOLD + "[IC] "
										+ ChatColor.WHITE
										+ "Humm... parece ser que " + genero
										+ " " + name.toString()
										+ " está hablando " + language);
							}
						}
					}
				} else if (channel.equals("ooc")) {
					player.sendMessage(ChatColor.DARK_BLUE + "[OOC] " + cargo
							+ sender.getName() + ": " + msg);
				}
			}
		}
	}
}
