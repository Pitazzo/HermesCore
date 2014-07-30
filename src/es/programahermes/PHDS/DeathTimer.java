package es.programahermes.PHDS;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DeathTimer {

	public static void limbo(Plugin plugin) {
		Bukkit.getServer().getScheduler()
				.scheduleSyncRepeatingTask(plugin, new Runnable() {
					@Override
					public void run() {
						for(Player player : Bukkit.getOnlinePlayers()){
							if(DeathSQL.isInLimbo(player.getName())){
								DeathSQL.removeTimeLeft(player.getName(), 20);
							}
						}
					}
				}, 200L, 20 * 20);
	}
}
