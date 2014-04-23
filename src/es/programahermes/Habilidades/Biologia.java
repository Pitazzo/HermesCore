package es.programahermes.Habilidades;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import es.programahermes.MySQL;

public class Biologia implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (!event.isCancelled()) {
			Player player = event.getPlayer();
			Action action = event.getAction();
			Block block = event.getClickedBlock();
			ItemStack item = player.getItemInHand();
			String habilidad = MySQL.getHability(player);
			if (habilidad.equals("Biologia")) {

				if (action.equals(Action.RIGHT_CLICK_BLOCK)) {

					if (block.getType().equals(Material.DIRT)
							|| block.getType().equals(Material.GRASS)) {
						if (item.getType().equals(Material.WOOD_HOE)
								|| item.getType().equals(Material.STONE_HOE)
								|| item.getType().equals(Material.IRON_HOE)
								|| item.getType().equals(Material.DIAMOND_HOE)) {
							MySQL.addEarnedPoints(player, "special", "labrar",
									1);

						}

					}

					if (block.getType().equals(Material.SOIL)) {
						MySQL.addEarnedPoints(player, "interact", block
								.getType().toString(), 1);

					}

				}
			}
		}
	}

	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent event) {
		if (!event.isCancelled()) {
			Player player = event.getPlayer();

			if (MySQL.getHability(player).equals("Biologia")) {
				String entity = event.getRightClicked().getType().toString();
				MySQL.addEarnedPoints(player, "entity", entity, 1);
			}
		}

	}

	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		LivingEntity killer = event.getEntity().getKiller();
		String entity = event.getEntity().getType().toString();
		if (killer instanceof Player) {
			Player player = (Player) killer;
			if (MySQL.getHability(player).equals("Biologia")) {
				MySQL.addEarnedPoints(player, "entity", entity, 1);
			}
		}
	}

	@EventHandler
	public void onCraftEvent(CraftItemEvent event) {
		if(!event.isCancelled()){
			Player player = (Player) event.getWhoClicked();
			String result = event.getRecipe().getResult().getType().toString();
			int amount = event.getRecipe().getResult().getAmount();
			if (MySQL.getHability(player).equals("Biologia")) {
				MySQL.addEarnedPoints(player, "craft", result, amount);

			}
		}
	}

}
