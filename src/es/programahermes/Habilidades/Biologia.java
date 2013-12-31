package es.programahermes.Habilidades;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
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
						MySQL.addPoints(player, labrar / level);

					}

				}
				if (block.getType().equals(Material.SOIL)) {
					System.out.println("Hey1");
					if (item.getType().equals(Material.SEEDS)) {
						double seeds = 2;
						System.out.println("Hey2");
						MySQL.addPoints(player, seeds / level);
					}
					if (item.getType().equals(Material.CARROT_ITEM)) {
						double carrot = 3;
						System.out.println("Hey3");
						MySQL.addPoints(player, carrot / level);
					}
					if (item.getType().equals(Material.POTATO_ITEM)) {
						double potato = 3;
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
					double feed = 4;
					double level = MySQL.getLevel(player);
					MySQL.addPoints(player, feed / level);
				}
			}
		}

	}

	@EventHandler
	public void onCraftEvent(CraftItemEvent event) {
		Player player = (Player) event.getWhoClicked();
		Material result = event.getRecipe().getResult().getType();
		int amount = event.getRecipe().getResult().getAmount();
		int level = MySQL.getLevel(player);
		if (MySQL.getHability(player).equals("Biologia")) {
			if (result.equals(Material.BREAD)) {

				double bread = 3*amount;
				MySQL.addPoints(player, bread / level);

			}
		}

	}

}
