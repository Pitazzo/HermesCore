package es.programahermes.Geologia;

import java.util.ArrayList;

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

	public ArrayList<Material> ores = new ArrayList<Material>();

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (MySQL.getHability(event.getPlayer().getName()).equals("Geologia")) {
			if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if (event.getClickedBlock().getType().equals(Material.STONE)) {
					if (event.getPlayer().getItemInHand().getType()
							.equals(Material.WOOD_PICKAXE)) {
						Location loc = event.getClickedBlock().getLocation();
						double ran = (Math.random() * 100) - MySQL.getLevel(event.getPlayer().getName());
						if (ran > WGRegions
								.getConecentration(loc, WGFlags.iron)) {
							ores.add(Material.IRON_ORE);
						}
						if (ran <= WGRegions.getConecentration(loc,
								WGFlags.gold)) {
							ores.add(Material.GOLD_ORE);
						}
						if (ran <= WGRegions.getConecentration(loc,
								WGFlags.coal)) {
							ores.add(Material.COAL_ORE);
						}
						
						for(Material material : ores){
							if(ran < 70){
								event.getClickedBlock().setType(material);
								break;
							}else{
								event.getClickedBlock().setType(Material.ENDER_STONE);
							}
						}

					}
				}
			}
		}
	}
}
