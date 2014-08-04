package es.programahermes.Managers;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;

import es.programahermes.MySQL;
import es.programahermes.Health.HealthSQL;
import es.programahermes.PHDS.DeathSQL;

public class HealthManager {

	public double getPercentage(Player player) {
		if (player != null) {
			return ((Damageable) player).getMaxHealth();

		}
		return 0;
	}

	public static void setMaxHealth(String player, int health) {
		int percentage = 100;
		if (MySQL.getFatiga(player) > 90) {
			percentage = 90;
		}
		if (HealthSQL.Diarrea(player)) {
			percentage = 80;
		}
		if (MySQL.getSed(player) < 15) {
			percentage = 75;
		}
		// desmayo
		if (DeathSQL.isPostDesmayado(player)) {
			percentage = 65;
		}
		if (HealthSQL.Anemia(player)) {
			percentage = 60;
		}
		if (HealthSQL.Septicemia(player)) {
			percentage = 45;
		}

		if (health > percentage) {

		}else{
			//set mas cercana
		}

	}

}
