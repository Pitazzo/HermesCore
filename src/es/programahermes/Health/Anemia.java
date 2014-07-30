package es.programahermes.Health;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import es.programahermes.Utilidades.Miscelaneo;

public class Anemia implements Listener {

	@EventHandler
	public void onConsume(PlayerItemConsumeEvent event) {
		Player player = event.getPlayer();
		player.setMaxHealth(50);
		if (Miscelaneo.getName(event.getItem()).equals("Complemento vitamínico")) {
				HealthSQL.addVPoints(player.getName(), 1);
			
		}
	}


	public static void anemiaCheck(String player) {
			HealthSQL.removeVPoints(player, 0.5);
			if (HealthSQL.getVPoints(player) < 10) {
				if (!HealthSQL.Anemia(player)) {
					setAnemia(player);
				}
			} else {
				if (HealthSQL.getVPoints(player) > 45) {
					if (HealthSQL.Anemia(player)) {
						healAnemia(player);
					}
				}
			}

		}
	
	public static void setAnemia(String player) {
		HealthSQL.setAnemia(player, true);
	}

	public static void healAnemia(String player) {
		HealthSQL.setAnemia(player, false);
	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		if(player.getMaxHealth() > 12){
			if(HealthSQL.Anemia(player.getName())){
				player.setMaxHealth(12);
			}
		}else{
			if(!HealthSQL.Anemia(player.getName())){
				player.setMaxHealth(20);
			}
		}
	}
}
