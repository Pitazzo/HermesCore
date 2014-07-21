package es.programahermes.Health;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import es.programahermes.Utilidades.Miscelaneo;

public class Anemia implements Listener {

	@EventHandler
	public void onConsume(PlayerItemConsumeEvent event) {
		Player player = event.getPlayer();
		anemiaCheck();
		if (Miscelaneo.equalsName(event.getItem(), "Complemento vitamínico")) {
			
				HealthSQL.addVPoints(player, 1);
			
		}
	}


	public static void anemiaCheck() {
		for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
			Player player = Bukkit.getPlayer(offlinePlayer.getName());
			HealthSQL.removeVPoints(player, 65);
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
	}

	public static void setAnemia(Player player) {
		player.setMaxHealth(player.getMaxHealth() - 6);
		HealthSQL.setAnemia(player, true);
	}

	public static void healAnemia(Player player) {
		player.setMaxHealth(20);
		HealthSQL.setAnemia(player, false);
	}
}
