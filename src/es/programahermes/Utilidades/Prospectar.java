package es.programahermes.Utilidades;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.ApplicableRegionSet;

import es.programahermes.MySQL;

public class Prospectar implements Listener {

	private WorldGuardPlugin getWorldGuard() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}

		return (WorldGuardPlugin) plugin;
	}

	public boolean isWithinRegion(Location loc, String region) {
		WorldGuardPlugin guard = getWorldGuard();
		RegionManager manager = guard.getRegionManager(loc.getWorld());
		ApplicableRegionSet set = manager.getApplicableRegions(loc);
		for (ProtectedRegion each : set) {
			if (each.getId().equalsIgnoreCase(region)) {
				return true;
			}
		}
		return false;
	}

	@EventHandler
	public void onBreak(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

			if (event.getClickedBlock().getType().equals(Material.STONE)) {

				if (MySQL.getHability(player).equals("Geologia")) {

					if (player.getItemInHand().getType()
							.equals(Material.WOOD_PICKAXE)) {

						Location location = event.getClickedBlock()
								.getLocation();
						if (isWithinRegion(location, "Aluminio")) {

							double random = Math.random() * 10;
							if (random <= 0.8) {

								event.getClickedBlock().setType(
										Material.IRON_ORE);
							} else {

								event.getClickedBlock().setType(
										Material.COBBLESTONE);
							}
						} else {
							event.getClickedBlock().setType(
									Material.COBBLESTONE);
						}if(isWithinRegion(location, "Cobre")){
							double random = Math.random() * 10;
							if (random <= 0.8) {

								event.getClickedBlock().setType(
										Material.IRON_ORE);
							} else {

								event.getClickedBlock().setType(
										Material.COBBLESTONE);
							}
						}
					}
				}
			}
		}
	}
}
