package es.programahermes.Utilidades;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;

public class Pernos implements Listener{

	@EventHandler
	public void onPlace(BlockPlaceEvent event){
		Player player = event.getPlayer();
		Inventory inv = player.getInventory();
		if(event.getBlock().getType().equals(Material.QUARTZ_BLOCK)){
			
		}
	}
}
