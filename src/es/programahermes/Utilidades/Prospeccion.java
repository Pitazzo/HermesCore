package es.programahermes.Utilidades;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import es.programahermes.MySQL;

public class Prospeccion implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack wood = new ItemStack(Material.WOOD_PICKAXE);
		ItemStack stone = new ItemStack(Material.STONE_PICKAXE);
		ItemStack iron = new ItemStack(Material.IRON_PICKAXE);
		ItemStack diamond = new ItemStack(Material.DIAMOND_PICKAXE);

		if (MySQL.getHability(player).equals("Geologia")) {
			if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				Block block = event.getClickedBlock();
				if (block.getType().equals(Material.STONE)) {
					if (player.getItemInHand().equals(wood)
							|| player.getItemInHand().equals(stone)
							|| player.getItemInHand().equals(iron)
							|| player.getItemInHand().equals(diamond)) {
						if (block != null) {

							double random = Math.random() * 10;
							if (random >= 6.0) {

								block.setType(Material.COBBLESTONE);

							} else {
								double random2 = Math.random() * 10;
								Location loc = player.getLocation();
								int y = loc.getBlockY();

								// diamond & redstone & esmerald
								if (y <= 16 && y >= 10) {

									if (random2 > 9.5) {
										block.setType(Material.DIAMOND_ORE);
									}
									if (random2 > 8.5 && random2 < 9.5) {
										block.setType(Material.REDSTONE_ORE);
									}

								}
								// gold
								if (y <= 32 && y >= 17) {

								}
								// iron
								if (y <= 64 && y >= 33) {

								}
								// coal
								if (y <= 200 && y >= 65) {

								}
							}
						}
					}
				}
			}
		}
	}
}
