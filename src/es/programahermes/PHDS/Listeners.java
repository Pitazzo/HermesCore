package es.programahermes.PHDS;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Listeners implements Listener {

	public static Location spawn = new Location(Bukkit.getWorld("Limbo"), 0, 70, 0);
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event){
		Player player = event.getEntity();
		
		if(!player.getWorld().getName().equals("Limbo")){
			player.sendMessage("Muerto");
			DeathSQL.setTimeLeft(player.getName(), 300);
			DeathSQL.setDeathLoc(player.getName(), player.getLocation());
		}else{
			DeathSQL.addTimeLeft(player.getName(), 300);
		}
	}
	
}
