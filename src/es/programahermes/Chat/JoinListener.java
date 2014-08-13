package es.programahermes.Chat;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import es.programahermes.Main;

public class JoinListener implements Listener{

	@EventHandler
	public void onJoin(PlayerLoginEvent event){
		Player player = event.getPlayer();
		player.sendMessage("Test");
		HermesChat.channel.put(player, "ic");
		HermesChat.idioma.put(player, "inglés");
		HermesChat.tono.put(player, 16);
		
		//data
	
			
			List<String> conocidos = (List<String>) Main.JugadoresConfig.getList(player.getName());
			System.out.println(conocidos.toString());
			conocidos.add("Bastiv|agent X");
			Main.JugadoresConfig.set(player.getName(), conocidos);
		
	}
	
}
