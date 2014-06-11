package es.programahermes.Geologia;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import WGRegions.WGFlags;
import WGRegions.WGRegions;
import es.programahermes.MySQL;

public class Perforadora implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPerf(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (!event.isCancelled()) {
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
							if (drill.getBlock().getType()
									.equals(Material.SPONGE)) {
								if (air.getBlock().getType()
										.equals(Material.AIR)) {

									int maxDeep = drill.getBlockY() - 20;
									for (int y = drill.getBlockY(); y > maxDeep; y--) {

										Location loc3 = new Location(
												player.getWorld(),
												drill.getX(), y - 1,
												drill.getZ());

										loc3.getBlock().setType(Material.AIR);
									}

									MySQL.addPoints(player,
											8 / MySQL.getLevel(player));
									player.getWorld().playSound(drill,
											Sound.ANVIL_BREAK, 15F, 15F);
									//carbon
									player.sendMessage(ChatColor.DARK_GRAY
											+ "Este suelo es rico en carbón al "
											+ WGRegions.getOreConecentration(
													drill, WGFlags.coal));
									//aluminio
									player.sendMessage(ChatColor.GRAY
											+ "Este suelo es rico en aluminio al "
											+ WGRegions.getOreConecentration(
													drill, WGFlags.iron));
									//silicio
									player.sendMessage(ChatColor.DARK_GREEN
											+ "Este suelo es rico en silicio al "
											+ WGRegions.getOreConecentration(
													drill, WGFlags.redstone));
									//cobre
									player.sendMessage(ChatColor.GOLD
											+ "Este suelo es rico en mineral de cobre al "
											+ WGRegions.getOreConecentration(
													drill, WGFlags.gold));
									//titanio
									player.sendMessage(ChatColor.RED
											+ "Este suelo es rico en titanio al "
											+ WGRegions.getOreConecentration(
													drill, WGFlags.diamond));
									

								}
							}
						}
					}
				}
			}
		}
	}
}
