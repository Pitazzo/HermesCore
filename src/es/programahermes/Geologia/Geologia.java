package es.programahermes.Geologia;

import net.morematerials.materials.MMCustomBlock;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import es.programahermes.MySQL;


public class Geologia implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		String material = event.getBlock().getType().toString();
		if(!event.isCancelled()){
			if (MySQL.getHability(player.getName()).equals("Geologia")) {
				if(player.getItemInHand().getType().equals(Material.WOOD_PICKAXE)){
					MySQL.addEarnedPoints(player.getName(), "break", material, 0.8);
				}
				if(player.getItemInHand().getType().equals(Material.STONE_PICKAXE)){
					MySQL.addEarnedPoints(player.getName(), "break", material, 0.9);
				}
				if(player.getItemInHand().getType().equals(Material.DIAMOND_PICKAXE)){
					MySQL.addEarnedPoints(player.getName(), "break", material, 1.15);
				}
				if(player.getItemInHand().getType().equals(Material.AIR)){
					MySQL.addEarnedPoints(player.getName(), "break", material, 0.65);
	
				}


			}
		}
		
	}

	@EventHandler
	public void onBuild(BlockPlaceEvent event) {
		if(!event.isCancelled()){
			Player player = event.getPlayer();
			String material = event.getBlock().getType().toString();			
			if (MySQL.getHability(player.getName()).equals("Geologia")) {
				MySQL.addEarnedPoints(player.getName(), "place", material, 1);
			}
		}
	}
}
