package es.programahermes.Geologia;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import es.programahermes.MySQL;

public class Refuerzos implements Listener {

	@EventHandler
	public void onRefor(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (!event.isCancelled()) {
			if (MySQL.getHability(player).equals("Geologia")) {
				if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					if (player.getItemInHand().getType()
							.equals(Material.INK_SACK)) {
						if (player.getItemInHand().getDurability() == 0) {
							if (event.getClickedBlock().getType()
									.equals(Material.STONE)
									|| event.getClickedBlock().getType()
											.equals(Material.ENDER_STONE)) {
								event.getClickedBlock().setType(
										Material.STAINED_CLAY);
								event.getClickedBlock().setData((byte) 14);
								event.getClickedBlock().setType(
										Material.STAINED_CLAY);
								player.getInventory()
										.setItem(
												player.getInventory().first(
														Material.INK_SACK),
												new ItemStack(
														Material.INK_SACK,
														player.getInventory()
																.getItem(
																		player.getInventory()
																				.first(Material.INK_SACK))
																.getAmount() - 1));
								player.updateInventory();
							} else {
								player.sendMessage(ChatColor.GOLD
										+ "Ese bloque no es reforzable");
							}
						}
					}
				}
			}
		}

	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (event.getBlock().getType().equals(Material.STAINED_CLAY)) {
			if (event.getBlock().getData() == 14) {
				event.getBlock().getDrops();
				event.getBlock()
						.getWorld()
						.dropItem(event.getBlock().getLocation(),
								new ItemStack(Material.STONE, 1));
				event.getBlock()
						.getWorld()
						.dropItem(event.getBlock().getLocation(),
								new ItemStack(Material.INK_SACK, 1));
				event.getPlayer().sendMessage(
						ChatColor.GOLD
								+ "Cuidado, has roto un bloque reforzado");
			}
		}
	}

}
