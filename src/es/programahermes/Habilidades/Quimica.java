package es.programahermes.Habilidades;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import es.programahermes.MySQL;

public class Quimica implements Listener {

	@EventHandler
	public void onCraftEvent(CraftItemEvent event) {
		if(!event.isCancelled()){
			Player player = (Player) event.getWhoClicked();
			String result = event.getRecipe().getResult().getType().toString();
			int amount = event.getRecipe().getResult().getAmount();
			if (MySQL.getHability(player.getName()).equals("Quimico")) {
				MySQL.addEarnedPoints(player.getName(), "craft", result, amount);
			}
		}
		
	}

}
