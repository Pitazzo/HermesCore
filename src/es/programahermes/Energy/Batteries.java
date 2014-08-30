package es.programahermes.Energy;

import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import es.programahermes.MySQL;
import es.programahermes.Utilidades.Miscelaneo;

public class Batteries implements Listener {

	public static HashMap<Location, Long> panels = new HashMap<Location, Long>();
	public static HashMap<Location, Long> bench = new HashMap<Location, Long>();

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
					if (MySQL.getHability(player.getName()).equals("Tecnico")) {
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
											ItemMeta im = player
													.getItemInHand()
													.getItemMeta();
											im.setDisplayName("Bater�a cargada");
											player.getItemInHand().setItemMeta(
													im);
											player.sendMessage(ChatColor.GREEN
													+ "�Has recargado la bater�a!");
										} else {
											int percent = (int) ((100 * (panels
													.get(event
															.getClickedBlock()
															.getLocation()) - System
													.currentTimeMillis())) / -480000);
											player.sendMessage(ChatColor.RED
													+ "El panel fotovoltaico a�n no est� cargado ("
													+ percent + "%)");
										}
									} else {
										panels.put(event.getClickedBlock()
												.getLocation(), System
												.currentTimeMillis());

										ItemMeta im = player.getItemInHand()
												.getItemMeta();
										im.setDisplayName("Bater�a cargada");
										player.getItemInHand().setItemMeta(im);
										player.sendMessage(ChatColor.GREEN
												+ "�Has recargado la bater�a!");
									}
								} else {
									player.sendMessage(ChatColor.RED
											+ "No hay luz solar suficientes para cargar las baterias del panel fotovoltaico");
								}
							} else {
								player.sendMessage(ChatColor.RED
										+ "Es de noche, la bater�a del panel fotovotaico no est� cargado");
							}
						} else {
							player.sendMessage(ChatColor.RED
									+ "La bater�a interna del panel fotovoltaico no est� cargada por las condiciones meteorol�gicas");
						}
					} else {
						player.sendMessage(ChatColor.RED
								+ "La recarga de bater�as solo puede ser realizada por personal cualificado");
					}
				} else {
					player.sendMessage(ChatColor.RED + "Ese objeto no es recargable");
				}
			}

		}

	}

	public static boolean isEmpty(ItemStack item) {

		if(Miscelaneo.getName(item).equals("Bater�a descargada")){
			return true;
		}else{
			return false;
		}

	}

	public static boolean isCharged(ItemStack item) {

		if(Miscelaneo.getName(item).equals("Bater�a cargada")){
			return true;
		}else{
			return false;
		}
	
		}

	

	@EventHandler
	public void onBurn(FurnaceBurnEvent event) {

		if (isCharged(event.getFuel())) {
			event.setBurnTime(60 * 20);
			ItemStack eBatt = new ItemStack(Material.COAL, 1);
			ItemMeta im = eBatt.getItemMeta();
			im.setDisplayName("Bater�a descargada");
			eBatt.setItemMeta(im);
			event.getBlock().getWorld()
					.dropItem(event.getBlock().getLocation(), eBatt);
		}

	}

	@EventHandler
	public void onInteract1(PlayerInteractEvent event) {
		if (!event.isCancelled()) {
			Player player = event.getPlayer();
			if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if (event.getClickedBlock().getType()
						.equals(Material.WORKBENCH)) {
					if (!isCharged(player.getItemInHand())) {
						if (bench.containsKey(event.getClickedBlock()
								.getLocation())) {
							if (System.currentTimeMillis()
									- bench.get(event.getClickedBlock()
											.getLocation()) < 900000) {
								int left = (int) ((900000 - (System
										.currentTimeMillis() - bench.get(event
										.getClickedBlock().getLocation()))) / (60 * 1000));
								player.sendMessage(ChatColor.GREEN
										+ "A esta mesa de trabajo le queda una autonom�a de "
										+ left + " minutos");
							} else {
								player.sendMessage(ChatColor.RED
										+ "�sta mesa de trabajo no tiene suficiente energ�a para funcionar");
								event.setCancelled(true);
							}
						} else {
							player.sendMessage(ChatColor.RED
									+ "�sta mesa de trabajo no tiene suficiente energ�a para funcionar");
							event.setCancelled(true);
						}
					} else {
						if (bench.containsKey(event.getClickedBlock()
								.getLocation())) {
							bench.remove(event.getClickedBlock().getLocation());
							bench.put(event.getClickedBlock().getLocation(),
									System.currentTimeMillis());
							player.sendMessage(ChatColor.GREEN
									+ "Has recargado la bater�a interna de la mesa de trabajo. Cuenta con una autonom�a de 15 minutos");
							ItemMeta im = event.getPlayer().getItemInHand()
									.getItemMeta();
							im.setDisplayName("Bater�a descargada");
							player.getItemInHand().setItemMeta(im);
						} else {
							bench.put(event.getClickedBlock().getLocation(),
									System.currentTimeMillis());
							player.sendMessage(ChatColor.GREEN
									+ "Has recargado la bater�a interna de la mesa de trabajo. Cuenta con una autonom�a de 15 minutos");
							ItemMeta im = event.getPlayer().getItemInHand()
									.getItemMeta();
							im.setDisplayName("Bater�a descargada");
							player.getItemInHand().setItemMeta(im);
						}
					}
				}
			}
		}
	}

}
