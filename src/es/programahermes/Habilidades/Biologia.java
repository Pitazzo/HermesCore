package es.programahermes.Habilidades;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import es.programahermes.Main;
import es.programahermes.MySQL;

public class Biologia implements Listener {

	public Main plugin;

	public Biologia(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		Block block = event.getClickedBlock();
		ItemStack item = player.getItemInHand();
		String habilidad = MySQL.getHability(player);
		int level = MySQL.getLevel(player);

		if (habilidad.equals("Biologia")) {

			if (action.equals(Action.RIGHT_CLICK_BLOCK)) {

				if (block.getType().equals(Material.DIRT)
						|| block.getType().equals(Material.GRASS)) {
					if (item.getType().equals(Material.WOOD_HOE)
							|| item.getType().equals(Material.STONE_HOE)
							|| item.getType().equals(Material.IRON_HOE)
							|| item.getType().equals(Material.DIAMOND_HOE)) {
						double labrar = 2;
						MySQL.addPoints(player, labrar/level);

					}

				}
				if (block.getType().equals(Material.SOIL)) {

					if (item.getType().equals(Material.SEEDS)) {
						int seeds = 2;
						MySQL.addPoints(player, seeds / level);
					}
					if (item.getType().equals(Material.CARROT_ITEM)) {
						int carrot = 3;
						MySQL.addPoints(player, carrot / level);
					}
					if (item.getType().equals(Material.POTATO_ITEM)) {
						int potato = 3;
						MySQL.addPoints(player, potato / level);
					}
				}

			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		int level = MySQL.getLevel(player);
		if (MySQL.getHability(player).equals("Biologia")) {
			if (block.getType().equals(Material.CROPS)) {
				double crops = 5;
				MySQL.addPoints(player, crops / level);
			}

		}

	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityInteract(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		ItemStack wheat = new ItemStack(Material.WHEAT);
		if (MySQL.getHability(player).equals("Biologia")) {
			EntityType entity = event.getRightClicked().getType();
			if (entity == EntityType.COW || entity == EntityType.CHICKEN
					|| entity == EntityType.PIG || entity == EntityType.SHEEP) {
				if (player.getItemInHand().equals(wheat)) {
					int feed = 4;
					int level = MySQL.getLevel(player);
					MySQL.addPoints(player, feed / level);
				}
			}
		}

	}
}
