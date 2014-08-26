package es.programahermes.Geologia;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

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

	public HashMap<Material, Double> ores = new HashMap<Material, Double>();

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (MySQL.getHability(event.getPlayer().getName()).equals("Geologia")) {
			if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if (event.getClickedBlock().getType().equals(Material.STONE)) {
					if (event.getPlayer().getItemInHand().getType()
							.equals(Material.WOOD_PICKAXE)) {
						Location loc = event.getClickedBlock().getLocation();
						double ran = (Math.random() * 100)
								- MySQL.getLevel(event.getPlayer().getName());
						if (ran <= WGRegions.getConecentration(loc,
								WGFlags.iron)) {
							ores.put(Material.IRON_ORE, Math.random());
						}
						if (ran <= WGRegions.getConecentration(loc,
								WGFlags.gold)) {
							ores.put(Material.GOLD_ORE, Math.random());
						}
						if (ran <= WGRegions.getConecentration(loc,
								WGFlags.coal)) {
							ores.put(Material.COAL_ORE, Math.random());
						}

						if (!ores.isEmpty()) {
							double max = Collections.max(ores.values());
							for (Entry<Material, Double> entry : ores
									.entrySet()) {
								if (max == entry.getValue()) {
									event.getClickedBlock().setType(
											entry.getKey());
									ores.clear();
									break;
								}
							}
						} else {
							event.getClickedBlock().setType(
									Material.ENDER_STONE);
							ores.clear();
						}
					}
				}
			}
		}
	}
}
