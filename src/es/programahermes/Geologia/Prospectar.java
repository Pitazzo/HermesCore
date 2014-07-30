package es.programahermes.Geologia;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import es.programahermes.MySQL;
import es.programahermes.WGRegions.WGFlags;
import es.programahermes.WGRegions.WGRegions;

public class Prospectar implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (MySQL.getHability(event.getPlayer().getName()).equals("Geologia")) {
			if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if (event.getClickedBlock().getType().equals(Material.STONE)) {
					if (event.getPlayer().getItemInHand().getType()
							.equals(Material.WOOD_PICKAXE)) {
						Location loc = event.getClickedBlock().getLocation();
						double ran = Math.random() * 100;
						if (ran > WGRegions.getConecentration(loc,
									WGFlags.iron)) {
								event.getClickedBlock().setType(
										Material.IRON_ORE);
							} else {
								event.getClickedBlock().setType(
										Material.ENDER_STONE);
							}
							if (ran <= WGRegions.getConecentration(loc,
									WGFlags.gold)) {
								event.getClickedBlock().setType(
										Material.GOLD_ORE);
							} else {
								event.getClickedBlock().setType(
										Material.ENDER_STONE);
							}
							if (ran <= WGRegions.getConecentration(loc,
									WGFlags.coal)) {
								event.getClickedBlock().setType(
										Material.COAL_ORE);
							} else {
								event.getClickedBlock().setType(
										Material.ENDER_STONE);
							}
						
					} else {
						// tool
						return;
					}
				} else {
					// stone
					return;
				}
			} else {
				// action
				return;
			}
		} else {
			// geo
			return;
		}
	}

}
