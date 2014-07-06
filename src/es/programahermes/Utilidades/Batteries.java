package es.programahermes.Utilidades;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import es.programahermes.MySQL;

public class Batteries implements Listener {

	public static HashMap<Location, Long> panels = new HashMap<Location, Long>();

	public boolean day(World world) {

		long time = world.getTime();

		return time < 12300 || time > 23850;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (event.getClickedBlock().getType()
					.equals(Material.DAYLIGHT_DETECTOR)) {
				if (isEmpty(player.getItemInHand())) {
					if (MySQL.getHability(player).equals("Tecnico")) {
						if (!(event.getClickedBlock().getWorld().hasStorm())) {
							if (day(event.getClickedBlock().getWorld())) {
								if (event.getClickedBlock().getLightLevel() > 4) {
									// todo ok
									if (panels.containsKey(event
											.getClickedBlock().getLocation())) {
										if (panels.get(event.getClickedBlock()
												.getLocation())
												- System.currentTimeMillis() < -480000) {
											panels.put(event.getClickedBlock()
													.getLocation(), System
													.currentTimeMillis());
											player.sendMessage("WIN!");
										} else {
											int percent = (int) ((100 * (panels
													.get(event
															.getClickedBlock()
															.getLocation()) - System
													.currentTimeMillis())) / -480000);
											player.sendMessage(ChatColor.RED
													+ "El panel fotovoltaico aún no está cargado ("
													+ percent + "%)");
										}
									} else {
										panels.put(event.getClickedBlock()
												.getLocation(), System
												.currentTimeMillis());
										player.sendMessage("WIN!");
									}
								} else {
									player.sendMessage(ChatColor.RED
											+ "No hay luz solar suficientes para cargar las baterias del panel fotovoltaico");
								}
							} else {
								player.sendMessage(ChatColor.RED
										+ "Es de noche, la batería del panel fotovotaico no está cargado");
							}
						} else {
							player.sendMessage(ChatColor.RED
									+ "La batería interna del panel fotovoltaico no está cargada por las condiciones meteorológicas");
						}
					} else {
						player.sendMessage(ChatColor.RED
								+ "La recarga de baterías solo puede ser realizada por personal cualificado");
					}
				} else {
					player.sendMessage(ChatColor.RED + "No es una batería");
				}
			}

		}

	}

	public boolean isEmpty(ItemStack item) {

		if (item.getItemMeta().getDisplayName().equals("Batería descargada")) {

			return true;
		}else{
			return false;
		}

		

	}

	@EventHandler
	public void onSmelt(FurnaceSmeltEvent event) {
		Furnace furnace = (Furnace) event.getBlock().getState();
		FurnaceInventory inv = furnace.getInventory();
		ItemStack eBatt = new ItemStack(Material.COAL, 1);
		ItemMeta im = eBatt.getItemMeta();
		im.setDisplayName("Batería descargada");
		eBatt.setItemMeta(im);
		if (inv.getFuel() == null) {
			inv.setFuel(eBatt);
		}
	}

}
