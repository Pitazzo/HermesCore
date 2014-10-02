package es.programahermes.WGRegions;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import com.mewin.util.*;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class WGRegions {

	private static WorldGuardPlugin getWorldGuard() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}

		return (WorldGuardPlugin) plugin;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public static boolean hasFlag(Location loc, Flag flag){
		WorldGuardPlugin guard = getWorldGuard();
		if( Util.getFlagValue(getWorldGuard(), loc, flag) == null){
			return false;
		}else{
			return true;
		}
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getOreConecentration(Location loc, Flag flag) {
		if (hasFlag(loc, flag)) {
			WorldGuardPlugin guard = getWorldGuard();
			RegionManager manager = guard.getRegionManager(loc.getWorld());
			ApplicableRegionSet set = manager.getApplicableRegions(loc);
			for (ProtectedRegion each : set) {
				return each.getFlag(flag).toString()+"%";
			}
		} else {
			return "0.0%";
		}
		return "0.0%";

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static double getConecentration(Location loc, Flag flag) {
		if (hasFlag(loc, flag)) {
			WorldGuardPlugin guard = getWorldGuard();
			RegionManager manager = guard.getRegionManager(loc.getWorld());
			ApplicableRegionSet set = manager.getApplicableRegions(loc);
			for (ProtectedRegion each : set) {
				return Double.parseDouble(each.getFlag(flag).toString());
			}
		} else {
			return 0;
		}
		return 0;

	}
}
