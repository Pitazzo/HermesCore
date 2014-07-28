package es.programahermes.Training;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;

import es.programahermes.Utilidades.Miscelaneo;
import es.programahermes.Utilidades.ModiferConverter;

public class Resistencia implements Listener {

	@EventHandler
	public void onSprint(PlayerToggleSprintEvent event) {
		Player player = event.getPlayer();

		if (player.getGameMode().equals(GameMode.SURVIVAL)) {
			if (!event.isCancelled()) {
				if (!player.isSprinting()) {
					TrainingSQL.addFTI(player.getName(), 0.001);
					double fti = TrainingSQL.getFTI(player.getName());
					double modifier = ModiferConverter.Scala(fti);
					Miscelaneo.setWalkSpeed(player, (float) ((float) 0.2 +0.1*modifier));
				} else {
					Miscelaneo.setWalkSpeed(player, 0.2);
				}
			}
		}

	}
}
