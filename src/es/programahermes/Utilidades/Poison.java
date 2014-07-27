package es.programahermes.Utilidades;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class Poison implements Listener {

	@EventHandler
	public void onConsume(PlayerItemConsumeEvent event) {
		Player player = event.getPlayer();
		if (event.getItem().getItemMeta().getDisplayName() != null) {
			String last = event
					.getItem()
					.getItemMeta()
					.getDisplayName()
					.substring(
							event.getItem().getItemMeta().getDisplayName()
									.length() - 1);
			if (last.equals(" ")) {
				player.damage(50);
			}
		}

	}

}
