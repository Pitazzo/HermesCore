package es.programahermes.Managers;

import org.bukkit.entity.Player;

import es.programahermes.Health.HealthSQL;

public class SpeedManager {

	public double getPercentage(Player player){
		if(player != null){
			double speed = player.getWalkSpeed();
			return (speed*100)/0.2;
		}
		return 0;
	}
	
	public void setSpeed(Player player, double percentage){
		double speed = (0.2*percentage)/100;
		double original = getPercentage(player);
		if(!HealthSQL.FracturaTI(player.getName())){
			if(!HealthSQL.Anemia(player.getName())){
				
			}
		}
	}
	
}
