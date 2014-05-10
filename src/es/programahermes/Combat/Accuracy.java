package es.programahermes.Combat;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.shampaggon.crackshot.events.WeaponHitBlockEvent;

public class Accuracy implements Listener {

	@EventHandler
	public void onHit(WeaponHitBlockEvent event) {
		Player player = event.getPlayer();
		if (event.getBlock().getType().equals(Material.STAINED_CLAY)) {
			if (event.getBlock().getData() == 13) {
				player.sendMessage(ChatColor.GREEN
						+ "Buen tiro, estabas a una distancia de "
						+ event.getBlock().getLocation()
								.distance(player.getLocation()) + " metros");
			}
		}
	}
}
