package es.programahermes.Health;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import es.programahermes.MySQL;

public class Anemia implements Listener {

	@EventHandler
	public void onConsume(PlayerItemConsumeEvent event){
		Player player = event.getPlayer();
		anemiaCheck();
		if(isDrug(event.getItem())){
			if(isVitaminas(event.getItem())){
				HealthSQL.addVPoints(player, 1);
			}
		}
	}

	public boolean isDrug(ItemStack item) {

		if (item != null) {
			if (item.getItemMeta() != null) {
				if (item.getItemMeta().getDisplayName() != null) {
					if (item.getItemMeta().getDisplayName()
							.equals("Frasco de medicinas")) {

						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}

		} else {
			return false;
		}

	}

	public boolean isVitaminas(ItemStack item) {

		if (item != null) {
			if (item.getItemMeta() != null) {
				if (item.getItemMeta().getDisplayName() != null) {
					if (item.getItemMeta().hasLore()) {
						if (item.getItemMeta().getLore().get(0)
								.equals("Complemento vitamínico")) {
							return true;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}

		} else {
			return false;
		}
		return false;

	}
	
	public void anemiaCheck(){
		for(Player player : MySQL.getNames()){
			Bukkit.getPlayer("Pitazzo").sendMessage(player.toString());
		}
	}
	
	public void setAnemia(Player player){
		player.setMaxHealth(player.getMaxHealth()-6);
	}
	
	public void healAnemia(Player player){
		player.setMaxHealth(20);
	}
}
