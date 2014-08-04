package es.programahermes.Managers;

import org.bukkit.entity.Player;

import es.programahermes.MySQL;
import es.programahermes.Health.HealthSQL;
import es.programahermes.PHDS.DeathSQL;

public class SpeedManager {

	public double getPercentage(Player player){
		if(player != null){
			double speed = player.getWalkSpeed();
			return (speed*100)/0.2;
		}
		return 0;
	}
	
	public static void setSpeed(String player, int speed) {
		int percentage = 100;
		if (HealthSQL.Diarrea(player)) {
			percentage = 80;
		}
        if (DeathSQL.isPostDesmayado(player)) {
			percentage = 70;
		}
		if (HealthSQL.Anemia(player)) {
				percentage = 60;
		}
		if (MySQL.getSed(player) < 15) {
			percentage = 60;
		}
		if (MySQL.getFatiga(player) > 90) {
			percentage = 50;
		}
		if (HealthSQL.Septicemia(player)) {
			percentage = 45;
		}
		if(HealthSQL.FracturaTS(player)){
			percentage = 10;
		}
		if (speed > percentage) {
			//Bukkit.getPlayer(player).setWalkSpeed()
		}else{
			//set mas cercano
		}
			

	}
	
}
