package es.programahermes.Managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;

import es.programahermes.MySQL;
import es.programahermes.Health.HealthSQL;
import es.programahermes.PHDS.DeathSQL;

public class HealthManager {

	public int getPercentage(Player p) {
		if (p != null) {
			Damageable player = p;
			return (int) ((player.getHealth()*100)/MaxHealthManager.getMaxHealth(p.getName()));			
			}
		return 0;
	}

	public static double getHealth(Player player, int percentage) { 
		if (player != null) {
			player.sendMessage("salud: "+percentage*MaxHealthManager.getMaxHealth(player.getName())/100);
			return (percentage*MaxHealthManager.getMaxHealth(player.getName())/100);	
			}
		return 0;
	}
	
	public static void setMaxHealth(String player, int health) {
		Bukkit.getPlayer(player).sendMessage("Iniciado seteo de salud");
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
		if (DeathSQL.isPostDesmayado(player)) {
			percentage = 65;
		}
		if (HealthSQL.Anemia(player)) {
			percentage = 60;
		}
		if (HealthSQL.Septicemia(player)) {
			percentage = 45;
		}

		if (percentage >= health) {
			Bukkit.getPlayer(player).setMaxHealth(getHealth(Bukkit.getPlayer(player), percentage));
		}

	}

}
