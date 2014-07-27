package es.programahermes.Health;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import es.programahermes.Utilidades.Miscelaneo;

public class Anemia implements Listener {

	@EventHandler
	public void onConsume(PlayerItemConsumeEvent event) {
		Player player = event.getPlayer();
		anemiaCheck(player.getName());
		if (Miscelaneo.equalsName(event.getItem(), "Complemento vitamínico")) {
			
				HealthSQL.addVPoints(player.getName(), 1);
			
		}
	}


	public static void anemiaCheck(String player) {
			HealthSQL.removeVPoints(player, 65);
			if (HealthSQL.getVPoints(player) < 10) {
				if (!HealthSQL.Anemia(player)) {
					setAnemia(player);
					
				}
			} else {
				if (HealthSQL.getVPoints(player) > 45) {
					if (HealthSQL.Anemia(player)) {
						healAnemia(player);
						Bukkit.broadcastMessage("¡Enhorabuena, "+player+"se libra esta semana de la anemia ;)");
					}
				}
			}

		}
	
//historias de la max health
	public static void setAnemia(String player) {

		HealthSQL.setAnemia(player, true);
	}

	public static void healAnemia(String player) {

		HealthSQL.setAnemia(player, false);
	}
}
