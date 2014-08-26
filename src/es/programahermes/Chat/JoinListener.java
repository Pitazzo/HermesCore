package es.programahermes.Chat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import es.programahermes.Main;

public class JoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		HermesChat.channel.put(player, "ic");
		HermesChat.idioma.put(player, "inglés");
		HermesChat.tono.put(player, 16);

		// data
		if (!Main.JugadoresConfig.isSet(player.getName())) {
			List<String> conocidos = new ArrayList<String>();
			conocidos.add("Notch@Markus Persson");
			Main.JugadoresConfig.set(player.getName(), conocidos);
			File file = new File(Main.plugin.getDataFolder(), "jugadores.yml");
			try {
				Main.JugadoresConfig.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			};
			
		}else{
			List<String> conocidos = (List<String>) Main.JugadoresConfig.getList(player.getName());
			for (String row : conocidos){
				System.out.println("Row: "+row);
				String[] parts = row.split("@");
				System.out.println("Nombre: "+parts[2]);
			}
		}

	}

}
