package es.programahermes.Habilidades;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import es.programahermes.MySQL;

public class Geologia implements Listener {
	

	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Material material = event.getBlock().getType();
		double level = MySQL.getLevel(player);
		if (MySQL.getHability(player).equals("Geologia")) {
			
			if (material.equals(Material.STONE)) {
				double stone = 2;
				MySQL.addPoints(player, stone / level);
			}
			if (material.equals(Material.IRON_ORE)) {
				double iron = 7;
				MySQL.addPoints(player, iron / level);
			}
			if (material.equals(Material.GRAVEL)) {
				double gravel = 1;
				MySQL.addPoints(player, gravel / level);
			}
			if (material.equals(Material.GOLD_ORE)) {
				double gold = 10;
				MySQL.addPoints(player, gold / level);
			}

			if (material.equals(Material.REDSTONE_ORE)) {
				double redstone = 15;
				MySQL.addPoints(player, redstone/level);
			}

		}
	}
}