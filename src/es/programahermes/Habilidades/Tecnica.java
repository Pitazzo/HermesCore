package es.programahermes.Habilidades;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.CraftItemEvent;

import es.programahermes.MySQL;

public class Tecnica {
	
	
	@EventHandler
	public void onCraftEvent(CraftItemEvent event) {
		Player player = (Player) event.getWhoClicked();
		Material result = event.getRecipe().getResult().getType();
		int amount = event.getRecipe().getResult().getAmount();
		int level = MySQL.getLevel(player);
		if (MySQL.getHability(player).equals("Biologia")) {
			if (result.equals(Material.LEVER)) {

				double lever = 1 * amount;
				MySQL.addPoints(player, lever / level);

			}
			if (result.equals(Material.LEVER)) {

				double lever = 1 * amount;
				MySQL.addPoints(player, lever / level);

			}
			if (result.equals(Material.LEVER)) {

				double lever = 1 * amount;
				MySQL.addPoints(player, lever / level);

			}
		}

	}
}
