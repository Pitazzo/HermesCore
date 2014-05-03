package es.programahermes.Training;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class Resistencia implements Listener {

	@EventHandler
	public void onMove(PlayerToggleSprintEvent event) {
		Player player = event.getPlayer();
		
		if(player.getGameMode().equals(GameMode.SURVIVAL)){
			if(!event.isCancelled()){
				TrainingSQL.addFTI(player, 0.001);
			}
		}
			
			
	}

}
