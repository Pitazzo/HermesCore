package es.programahermes.PHDS;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import es.programahermes.Managers.HealthManager;
import es.programahermes.Managers.SpeedManager;


public class Desmayo{

	public void setPostDesmayo(String player){
		HealthManager.setMaxHealth(player, 65);
		SpeedManager.setSpeed(player, 70);
		DeathSQL.setInPost(player, true);
		DeathSQL.addTimePostLeft(player, 1200);
	}
	
	public static void clearPost(String player){
		HealthManager.setMaxHealth(player, 100);
		SpeedManager.setSpeed(player, 100);
		DeathSQL.setInPost(player, false);
	}
	public static void desmayo(Plugin plugin) {
		Bukkit.getServer().getScheduler()
				.scheduleSyncRepeatingTask(plugin, new Runnable() {
					@Override
					public void run() {
						for (Player player : Bukkit.getOnlinePlayers()) {
							if (DeathSQL.isPostDesmayado(player.getName())) {
								if(DeathSQL.getTimePostLeft(player.getName())>0){
									DeathSQL.removeTimePostLeft(player.getName(), 20);
								}else{
									clearPost(player.getName());
								}

							}
						}
					}
				}, 200L, 20 * 20);
	}
	
}
