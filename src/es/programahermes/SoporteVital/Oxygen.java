package es.programahermes.SoporteVital;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import es.programahermes.MySQL;
import es.programahermes.Utilidades.Scoreboard;


public class Oxygen {

	public static boolean hasSuit(Player player){
		
		
		if(player.getInventory().getBoots() !=null && player.getInventory().getHelmet() !=null && player.getInventory().getChestplate() !=null && player.getInventory().getLeggings() !=null){
			return true;
		}else{
			return false;	
		}
	}
	
	
	//World Guard
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
				
					for(Player player : Bukkit.getOnlinePlayers()){
						if(isWithinRegion(player.getLocation(), "presurizada")){
							return;
						}else{
							Scoreboard.showScore(player);
							if(hasSuit(player)){
								if(MySQL.getOxygen(player)<=0){
									player.sendMessage(ChatColor.RED+"[Soporte Vital]"+ChatColor.GREEN+"¡Vuelve inmediatamente a la base o presurizalo!");
									player.playSound(player.getLocation(), Sound.BAT_DEATH, 0.5F, 0.0F);
									player.damage(1);
									player.addPotionEffect(new PotionEffect(
											PotionEffectType.CONFUSION, 200, 0), true);
								}else{
									MySQL.removeOxygen(player, 1);
								}
								
								
							}else{
								MySQL.setOxygen(player, 0);
								player.sendMessage(ChatColor.RED+"[Soporte Vital]"+ChatColor.GREEN+"¡No salgas al exterior sin un traje presurizado!");
								player.playSound(player.getLocation(), Sound.BAT_DEATH, 0.5F, 0.0F);
								player.damage(4);
								player.addPotionEffect(new PotionEffect(
										PotionEffectType.CONFUSION, 200, 0), true);
							}
						}
						
						
					}
					

			}
		}, 100L, 20 * 5);
	}
	
	
	public int getOxygen(ItemStack item){
		List<String> lore = new ArrayList();
		lore = item.getItemMeta().getLore();
		if(lore != null){
			if(lore.get(0).equals("Oxígeno:")){
				
			}
		}
		
		return 0;
		
	}
	
	
	
	
	
}
