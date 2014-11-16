package es.programahermes.SoporteVital.Oxygen;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import es.programahermes.WGRegions.WGFlags;

public class Oxygen {

	public static boolean hasSuit(Player player) {
		if (player.getInventory().getBoots() != null
				&& player.getInventory().getHelmet() != null
				&& player.getInventory().getChestplate() != null
				&& player.getInventory().getLeggings() != null) {
			if (player.getInventory().getBoots().getType()
					.equals(Material.GOLD_BOOTS)
					&& player.getInventory().getHelmet().getType()
							.equals(Material.GOLD_HELMET)
					&& player.getInventory().getChestplate().getType()
							.equals(Material.GOLD_CHESTPLATE)
					&& player.getInventory().getLeggings().getType()
							.equals(Material.GOLD_LEGGINGS)) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean hasMask(Player player) {
		if (player.getInventory().getHelmet() != null) {
			if (player.getInventory().getHelmet().getType()
					.equals(Material.LEATHER_HELMET)) {
				return true;
			}
		}
		return false;
	}

	// World Guard
	private static WorldGuardPlugin getWorldGuard() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}

		return (WorldGuardPlugin) plugin;
	}

	public static boolean isPresurizada(Location loc) {
		WorldGuardPlugin guard = getWorldGuard();
		RegionManager manager = guard.getRegionManager(loc.getWorld());
		ApplicableRegionSet set = manager.getApplicableRegions(loc);
		for (ProtectedRegion each : set) {
			if(each.getFlag(WGFlags.presurizada) !=null){
				if (each.getFlag(WGFlags.presurizada).booleanValue()) {
					return true;
				}
			}
			
		}
		return false;
	}

	public static boolean hasOxygen(ItemStack item) {
		if (item != null && item.getItemMeta() != null) {
			if (item.getItemMeta().getLore() != null) {
				if (item.getItemMeta().getLore().get(0).contains("O2: ")) {
					return true;
				}
			}
		}

		return false;

	}

	public static int getOxygen(ItemStack item) {
		if (item.getItemMeta() != null) {
			if (item.getItemMeta().getLore() != null) {
				String o2 = item.getItemMeta().getLore().get(0);
				if (o2.contains("O2: ")) {
					String o3 = o2.replace("O2: ", "");
					String o4 = o3.replace("L", "");
					int oxygen = Integer.parseInt(o4);
					return oxygen;

				}

			}
		}

		return 0;

	}

	public static void setOxygen(ItemStack item, int oxygen) {
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("O2: " + oxygen + "L");
		meta.setLore(lore);
		item.setItemMeta(meta);
	}

	public static void addOxygen(ItemStack item, int oxygen) {
		int previous = getOxygen(item);
		setOxygen(item, previous + oxygen);
	}

	public static void removeOxygen(ItemStack item, int oxygen) {
		int previous = getOxygen(item);
		if (previous - oxygen > 0) {
			setOxygen(item, previous - oxygen);
		} else {
			setOxygen(item, 0);
		}

	}

	public static void kill(Player player){
		if(player.getWorld().getName().equals("Nave")){
			player.damage(8);
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 4));
			player.sendMessage(ChatColor.GRAY+"En el vacío no hay aire que transporte el sonido...");
		}else if(player.getWorld().getName().equals("Kepler")){
			player.damage(1);
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1));
			player.sendMessage(ChatColor.GOLD+"Mis... mis pulmones... ¡AIRE!");
		}
	}
	
}
