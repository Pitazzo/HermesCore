package es.programahermes.Health;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Desmayo implements Listener{

	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event){
		Player player = event.getPlayer();
		if(!player.getGameMode().equals(GameMode.CREATIVE)){
			
		}
	}
	
}
