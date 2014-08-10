package es.programahermes.Chat;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import es.programahermes.Main;
import es.programahermes.MySQL;

public class IdentityChat {

	public static boolean knowsPlayer(Player sender, Player target) {
		List<String> conocidos = new ArrayList<String>();
		conocidos = (List<String>) Main.plugin.getConfig().getList(
				"Jugadores." + sender.getName());
		for (String rows : conocidos) {
			if (rows.contains(target.getName())) {
				return true;
			}
		}

		return false;

	}

	public static String getName(Player sender, Player target) {
		if (knowsPlayer(sender, target)) {
			List<String> conocidos = new ArrayList<String>();
			conocidos = (List<String>) Main.plugin.getConfig().getList(
					"Jugadores." + sender.getName());
			for (String rows : conocidos) {
				if (rows.contains(target.getName())) {
					String[] parts = rows.split("|");
					return String.valueOf(parts[1]);

				}
			}
		} else {
			return MySQL.getDescripcion(sender.getName());
		}

		return "ERROR EN CHAT - REPORTAR";
	}

	public static void meetPlayer(Player sender, Player target, String nombreIC) {
		if (!knowsPlayer(sender, target)) {
			List<String> conocidos = new ArrayList<String>();
			conocidos = (List<String>) Main.plugin.getConfig().getList(
					"Jugadores." + target.getName());
			conocidos.add(nombreIC); // viejo codigo por si acaso:
										// add(sender.getName() + "|" +
										// nombreIC);
		}
	}

	public void showRealName(Player sender, Player target) {
		if (knowsPlayer(sender, target)) {
			List<String> conocidos = new ArrayList<String>();
			conocidos = (List<String>) Main.plugin.getConfig().getList(
					"Jugadores." + sender.getName());
			conocidos.remove(conocidos.indexOf(getName(sender, target)));
			// conocidos.add(sender.getName()+"|"+MySQL.getICName);

		}
	}
}
