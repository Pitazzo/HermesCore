package es.programahermes.Habilidades;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import es.programahermes.MySQL;

public class Estructural implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Material material = event.getBlock().getType();
		if (MySQL.getHability(player).equals("Estructural")) {
			if (material.equals(Material.SAND)
					|| material.equals(Material.DIRT)
					|| material.equals(Material.GRAVEL)
					|| material.equals(Material.SANDSTONE)) {
				MySQL.addPoints(player, 1.5/MySQL.getLevel(player));
			}
		}
	}

	@EventHandler
	public void onPlayerPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Material material = event.getBlock().getType();
		int level = MySQL.getLevel(player);
		if (MySQL.getHability(player).equals("Estructural")) {

			if (material.equals(Material.COBBLESTONE)) {
				int cobble = 3;
				MySQL.addPoints(player, cobble / level);
			}
			if (material.equals(Material.COBBLESTONE_STAIRS)) {
				int cobblestairs = 4;
				MySQL.addPoints(player, cobblestairs / level);
			}
			

		}
	}

}
