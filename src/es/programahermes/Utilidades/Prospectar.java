package es.programahermes.Utilidades;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
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
		ItemStack escoria1 = new ItemStack(Material.GHAST_TEAR, 1);
		ItemStack escoria6 = new ItemStack(Material.GHAST_TEAR, 6);
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

			if (event.getClickedBlock().getType().equals(Material.STONE)) {

				if (MySQL.getHability(player).equals("Geologia")) {

					if (player.getItemInHand().getType()
							.equals(Material.WOOD_PICKAXE)) {

						double prospectar = 2;
						int level = MySQL.getLevel(player);
						double random = Math.random() * 10;
						MySQL.addPoints(player, prospectar / level);
						Location location = event.getClickedBlock()
								.getLocation();

						// coal

						if (isWithinRegion(location, "Coal")) {

							if (random <= 0.3) {
								event.getClickedBlock().setType(
										Material.COAL_ORE);
								player.getWorld().dropItemNaturally(location,
										escoria1);
							} else {
								event.getClickedBlock().setType(Material.AIR);
								player.getWorld().dropItemNaturally(location,
										escoria6);
							}
						} else {
							event.getClickedBlock().getLocation();
							event.getClickedBlock().setType(Material.AIR);
							player.getWorld().dropItemNaturally(location,
									escoria6);

							// gold

							if (isWithinRegion(location, "Gold")) {

								if (random <= 1.2) {
									event.getClickedBlock().setType(
											Material.GOLD_ORE);
									player.getWorld().dropItemNaturally(
											location, escoria1);
								} else {
									event.getClickedBlock().setType(
											Material.AIR);
									player.getWorld().dropItemNaturally(
											location, escoria6);
								}
							} else {
								event.getClickedBlock().getLocation();
								event.getClickedBlock().setType(Material.AIR);
								player.getWorld().dropItemNaturally(location,
										escoria6);

								// iron
								if (isWithinRegion(location, "Iron")) {

									if (random <= 0.095) {
										event.getClickedBlock().setType(
												Material.IRON_ORE);
										player.getWorld().dropItemNaturally(
												location, escoria1);
									} else {
										event.getClickedBlock().setType(
												Material.AIR);
										player.getWorld().dropItemNaturally(
												location, escoria6);
									}
								} else {
									event.getClickedBlock().getLocation();
									event.getClickedBlock().setType(
											Material.AIR);
									player.getWorld().dropItemNaturally(
											location, escoria6);

									// redstone
									if (isWithinRegion(location, "Redstone")) {

										if (random <= 0.07) {
											event.getClickedBlock().setType(
													Material.REDSTONE_ORE);
											player.getWorld()
													.dropItemNaturally(
															location, escoria1);
										} else {
											event.getClickedBlock().setType(
													Material.AIR);
											player.getWorld()
													.dropItemNaturally(
															location, escoria6);
										}
									} else {
										event.getClickedBlock().getLocation();
										event.getClickedBlock().setType(
												Material.AIR);
										player.getWorld().dropItemNaturally(
												location, escoria6);

										// diamond

										if (isWithinRegion(location, "Diamond")) {

											if (random <= 0.07) {
												event.getClickedBlock()
														.setType(
																Material.DIAMOND_ORE);
												player.getWorld()
														.dropItemNaturally(
																location,
																escoria1);
											} else {
												event.getClickedBlock()
														.setType(Material.AIR);
												player.getWorld()
														.dropItemNaturally(
																location,
																escoria6);
											}
										} else {
											event.getClickedBlock()
													.getLocation();
											event.getClickedBlock().setType(
													Material.AIR);
											player.getWorld()
													.dropItemNaturally(
															location, escoria6);

											
										}
									}

								}
							}
						}
					}
				}
			}
		}

	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (event.getClickedBlock().getType().equals(Material.LEVER)) {
				if (MySQL.getHability(player).equals("Geologia")) {

					if (event.getClickedBlock().getType()
							.equals(Material.LEVER)) {
						Location clicked = event.getClickedBlock()
								.getLocation();
						Location drill = new Location(player.getWorld(),
								clicked.getX(), clicked.getY() - 1,
								clicked.getZ());
						Location air = new Location(player.getWorld(),
								clicked.getX(), drill.getY() - 1,
								clicked.getZ());
						if (drill.getBlock().getType().equals(Material.SPONGE)) {
							if (air.getBlock().getType().equals(Material.AIR)) {

								int maxDeep = drill.getBlockY() - 20;
								for (int y = drill.getBlockY(); y > maxDeep; y--) {

									Location loc3 = new Location(
											player.getWorld(), drill.getX(),
											y - 1, drill.getZ());

									loc3.getBlock().setType(Material.AIR);	
								}
								
								MySQL.addPoints(player, 8/MySQL.getLevel(player));
								player.getWorld().playSound(drill, Sound.ANVIL_BREAK, 15F, 15F);
								if(isWithinRegion(drill, "Coal")){
									player.sendMessage(ChatColor.BLACK+"La perforadora ha dado positivo en carbón");
								}else{
									player.sendMessage(ChatColor.RED+"Aparentemente no hay carbón en esta zona");
								}
								
								if(isWithinRegion(drill, "Gold")){
									player.sendMessage(ChatColor.GOLD+"La perforadora ha dado positivo en cobre");
								}else{
									player.sendMessage(ChatColor.RED+"Aparentemente no hay cobre en esta zona");
								}
								
								if(isWithinRegion(drill, "Iron")){
									player.sendMessage(ChatColor.GRAY+"La perforadora ha dado positivo en aluminio");
								}else{
									player.sendMessage(ChatColor.RED+"Aparentemente no hay aluminio en esta zona");
								}
								
								if(isWithinRegion(drill, "Redstone")){
									player.sendMessage(ChatColor.GREEN+"La perforadora ha dado positivo en silicio");
								}else{
									player.sendMessage(ChatColor.RED+"Aparentemente no hay silicio en esta zona");
								}
								
								if(isWithinRegion(drill, "Diamond")){
									player.sendMessage(ChatColor.DARK_GRAY+"La perforadora ha dado positivo en titanio");
								}else{
									player.sendMessage(ChatColor.RED+"Aparentemente no hay titanio en esta zona");
								}
							} else {
								player.sendMessage(ChatColor.BLUE
										+ "¡Perforadora mal colocada!");
							}

						}
					}
				}
			}
		}
	}
}
