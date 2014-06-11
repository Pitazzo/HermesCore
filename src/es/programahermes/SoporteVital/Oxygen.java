package es.programahermes.SoporteVital;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import WGRegions.WGFlags;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import es.programahermes.MySQL;

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
			} else {
				return false;
			}

		} else {

			return false;
		}

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
			if (each.getFlag(WGFlags.presurizada).booleanValue()) {
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
					//Argo
					if (player.getWorld().getName().equals("Nave")) {
						if (player.getGameMode().equals(GameMode.SURVIVAL)) {
							if (hasSuit(player)) {
								if (MySQL.getOxygen(player) > 1) {
									MySQL.removeOxygen(player, 1);
								} else {
									player.sendMessage(ChatColor.GREEN
											+ "[Soporte Vital]"
											+ ChatColor.RED
											+ "¡Tu traje no está presurizado!¡Presurizalo!");
									player.damage(3);
									player.playSound(player.getLocation(),
											Sound.BAT_DEATH, 1F, 1F);
									player.addPotionEffect(new PotionEffect(
											PotionEffectType.CONFUSION, 10, 2),
											true);
								}
							} else {
								MySQL.setOxygen(player, 0);
								// no tiene traje
								if (isPresurizada(player.getLocation())) {
									// está en casa
									return;
								} else {
									// no está en casa
									player.sendMessage(ChatColor.GREEN
											+ "[Soporte Vital]"
											+ ChatColor.RED
											+ "¡No salgas al exterior sin un traje!¡Regresa inmediatamente!");
									player.damage(3);
									player.playSound(player.getLocation(),
											Sound.BAT_DEATH, 1F, 1F);
									player.addPotionEffect(new PotionEffect(
											PotionEffectType.CONFUSION, 10, 2),
											true);
								}
							}
						}
					} else {
						if (player.getWorld().getName()
								.equalsIgnoreCase("Kepler")) {
							//kepler
							if (player.getGameMode().equals(GameMode.SURVIVAL)) {
								if (hasMask(player)) {
									return;
								} else {
									player.damage(2);
									player.playSound(player.getLocation(),
											Sound.BAT_DEATH, 1F, 1F);
									player.addPotionEffect(new PotionEffect(
											PotionEffectType.CONFUSION, 5, 2));
									player.sendMessage(ChatColor.GREEN
											+ "[Soporte Vital]"
											+ ChatColor.RED
											+ "¡La atmósfera no es respirable!¡Colocate una mascarilla inmediatamente o regresa a la base!");
								}
							}
						}
					}

				}
			}

		}, 100L, 20 * 5);

	}
}
