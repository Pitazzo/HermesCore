package es.programahermes.Chat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.entity.Player;

import es.programahermes.Main;
import es.programahermes.MySQL;

public class IdentityChat {

	public static boolean knowsPlayer(Player asker, Player player) {
		@SuppressWarnings("unchecked")
		List<String> conocidos = (List<String>) Main.JugadoresConfig
				.getList(asker.getName());
		if (conocidos != null) {
			for (String row : conocidos) {
				if (row.contains(player.getName())) {
					return true;
				}
			}
		}

		return false;
	}

	public static String getName(Player asker, Player sender) {
		if (knowsPlayer(asker, sender)) {

			@SuppressWarnings("unchecked")
			List<String> conocidos = (List<String>) Main.JugadoresConfig
					.getList(asker.getName());
			for (String row : conocidos) {
				if (row.contains(sender.getName())) {
					String[] parts = row.split("@");
					return parts[1];
				}
			}

		} else {
			return MySQL.getDescripcion(asker.getName());
		}

		return "ERROR EN CHAT - REPORTAR";
	}

	public static void meetPlayer(Player meeter, Player player, String name) {
		@SuppressWarnings("unchecked")
		List<String> conocidos = (List<String>) Main.JugadoresConfig
				.getList(meeter.getName());

		conocidos.add(player.getName() + "@" + name);
		Main.JugadoresConfig.set(meeter.getName(), conocidos);
		File file = new File(Main.plugin.getDataFolder(), "jugadores.yml");
		try {
			Main.JugadoresConfig.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
