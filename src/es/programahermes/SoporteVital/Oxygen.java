package es.programahermes.SoporteVital;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import es.programahermes.MySQL;

import com.mewin.WGCustomFlags.WGCustomFlagsPlugin;

public class Oxygen {

	public static boolean hasSuit(Player player) {

		if (player.getInventory().getBoots() != null
				&& player.getInventory().getHelmet() != null
				&& player.getInventory().getChestplate() != null
				&& player.getInventory().getLeggings() != null) {
			return true;
		} else {
			return false;
		}
	}

	// WGCF

	private WGCustomFlagsPlugin getWGCustomFlags() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("WGCustomFlags");

		if (plugin == null || !(plugin instanceof WGCustomFlagsPlugin)) {
			return null;
		}

		return (WGCustomFlagsPlugin) plugin;
		
	
	}

	// World Guard
	private static WorldGuardPlugin getWorldGuard() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}

		return (WorldGuardPlugin) plugin;
	}

	

	
	public static boolean isWithinRegion(Location loc, String region) {
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

	public static void oxyenUpdate(Plugin plugin) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {

				for (Player player : Bukkit.getOnlinePlayers()) {
					// consumo normal exterior
					if (hasSuit(player)) {
						if (MySQL.getOxygen(player) > 1) {
							if(MySQL.getOxygen(player)>0){
								MySQL.removeOxygen(player, 1);
							}
							
						} else {
							player.sendMessage(ChatColor.GREEN
									+ "[Soporte Vital]"
									+ ChatColor.RED
									+ "¡Tu traje no está presurizado!¡Presurizalo!");
							player.damage(5);
							player.playSound(player.getLocation(),
									Sound.BAT_DEATH, 1F, 1F);
							player.addPotionEffect(new PotionEffect(
									PotionEffectType.CONFUSION, 10, 2), true);
						}
					} else {
						MySQL.setOxygen(player, 0);
						// no tiene traje
						if (isWithinRegion(player.getLocation(), "presurizada")) {
							// está en casa
							return;
						} else {
							// no está en casa
							player.sendMessage(ChatColor.GREEN
									+ "[Soporte Vital]"
									+ ChatColor.RED
									+ "¡No salgas al exterior sin un traje!¡Regresa inmediatamente!");
							player.damage(4);
							player.playSound(player.getLocation(),
									Sound.BAT_DEATH, 1F, 1F);
							player.addPotionEffect(new PotionEffect(
									PotionEffectType.CONFUSION, 10, 2), true);
						}

					}

				}
			}
		}, 100L, 20 * 5);
	}

}
