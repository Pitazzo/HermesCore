package es.programahermes.SoporteVital;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class Fatiga implements Listener {

	public static HashMap<Player, Long> walked = new HashMap<Player, Long>();
	public static HashMap<Player, Long> sleeped = new HashMap<Player, Long>();
	
	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent event){
		Player player = event.getPlayer();
	
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		int fromX = (int) event.getFrom().getX();
		int fromY = (int) event.getFrom().getY();
		int fromZ = (int) event.getFrom().getZ();

		int toX = (int) event.getTo().getX();
		int toY = (int) event.getTo().getY();
		int toZ = (int) event.getTo().getZ();

		if (fromX != toX || fromY != toY || fromZ != toZ) {
			if (walked.containsValue(player)) {
				walked.remove(player);
				walked.put(player, System.currentTimeMillis());
			} else {
				walked.put(player, System.currentTimeMillis());
			}
		}
	}

	public static void waitFatigaCheck(Plugin plugin) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {

				for (Player player : Bukkit.getOnlinePlayers()) {

					if (walked.containsKey(player)) {
						if (System.currentTimeMillis() - walked.get(player) >= 120 * 1000) {

							player.sendMessage(ChatColor.RED
									+ "[Soporte Vital]" + ChatColor.GREEN
									+ "Has recuperado un 5% de tu resistencia");

						}
					}
				}

			}
		}, 100L, 20 * 10);
	}

}
