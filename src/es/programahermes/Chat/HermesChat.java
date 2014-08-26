package es.programahermes.Chat;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import es.programahermes.MySQL;

public class HermesChat {

	public static HashMap<Player, String> channel = new HashMap<Player, String>();
	public static HashMap<Player, String> idioma = new HashMap<Player, String>();
	public static HashMap<Player, Integer> tono = new HashMap<Player, Integer>();

	public static void chat(Player sender, String msg, int radius,
			String channel, String language) {
		for (Player player : sender.getWorld().getPlayers()) {
			if (sender.getLocation().distance(player.getLocation()) < radius) {				
				String name = "";		
				String cargo = "";
				String ch = "";
				String lang = "";
				// cargos
				if (sender.hasPermission("hermescore.vip")) {
					cargo = ChatColor.GOLD + "[VIP] ";
				}
				if (sender.hasPermission("hermescore.mod")) {
					cargo = ChatColor.BLUE + "[MOD] ";
				}
				if (sender.hasPermission("hermescore.admin")) {
					cargo = ChatColor.DARK_RED + "[ADM] ";
				}

				// name
				
				// canales
				if (channel.equalsIgnoreCase("ic")) {
					ch = ChatColor.GOLD + "[IC] ";
					name = IdentityChat.getName(player, sender);
					lang = ChatColor.DARK_GREEN+"["+ idioma.get(sender).substring(0, 1).toUpperCase() + idioma.get(sender).substring(1)+"] ";
				} else if (channel.equalsIgnoreCase("ooc")) {
					ch = ChatColor.BLUE + "[OOC] ";
					name = player.getName();
				}
				// radio
				if (radius < 3) {
					msg = ChatColor.GRAY + msg;
				} else if (radius > 19 && channel.equalsIgnoreCase("ic")) {
					msg = ChatColor.DARK_RED + msg;
				}

				if (sender == player) {
					player.sendMessage(ch + cargo + lang +ChatColor.DARK_PURPLE +MySQL.getICName(player) +
							 ChatColor.WHITE+": " + msg);
				} else {
					if (sender.getLocation().distance(player.getLocation()) < radius) {
						if (player.hasPermission("hermescore.idiomas."
								+ language)) {
							player.sendMessage(ch + cargo +lang+ ChatColor.DARK_PURPLE
									+ name + ChatColor.WHITE+ ": " + msg);
						} else {
							if (!IdentityChat.knowsPlayer(player, sender)) {
								if (MySQL.getGenero(sender.getName())) {

									name = "esa "
											+ IdentityChat.getName(sender,
													player);
								} else {
									name = "ese "
											+ IdentityChat.getName(sender,
													player);
								}
							}
							player.sendMessage("Humm... Parece que " + name
									+ " está hablando en " + ChatColor.DARK_RED
									+ language + ChatColor.WHITE
									+ "... no entiendo nada");
						}
					}
				}

			}
		}
	}
}
