package es.programahermes.Chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class JoinListener implements Listener{

	@EventHandler
	public void onJoin(PlayerLoginEvent event){
		Player player = event.getPlayer();
		player.sendMessage("Test");
		HermesChat.channel.put(player, "IC");
		HermesChat.channel.put(player, "inglés");
		HermesChat.tono.put(player, 16);
	}
	
}
