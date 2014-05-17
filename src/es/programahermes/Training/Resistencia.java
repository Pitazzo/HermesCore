package es.programahermes.Training;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class Resistencia implements Listener {

	@EventHandler
	public void onSprint(PlayerToggleSprintEvent event) {
		Player player = event.getPlayer();

		if (player.getGameMode().equals(GameMode.SURVIVAL)) {
			if (!event.isCancelled()) {
				if (!player.isSprinting()) {
					TrainingSQL.addFTI(player, 0.001);
					double fti = TrainingSQL.getFTI(player);
					double modifier = 0;
						if(fti > 50){
							modifier = fti/250;
						}else{
							modifier = (fti-50)/250;
						}
					player.sendMessage("Modifier: "+modifier);
					player.setWalkSpeed((float) ((float) 0.26 + modifier));
				} else {
					player.setWalkSpeed((float) 0.2);
				}
			}
		}

	}
}
