package es.programahermes.Chat;

import org.bukkit.entity.Player;

import es.programahermes.Main;


public class IdentityChat {


	public static boolean isInFile(Player player) {
		if (Main.JugadoresConfig.isSet(player.getName())) {
			return true;
		}
		return false;
	}
}
