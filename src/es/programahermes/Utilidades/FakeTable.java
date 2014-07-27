package es.programahermes.Utilidades;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.shininet.bukkit.itemrenamer.api.RenamerAPI;

public class FakeTable implements Listener {

	public static List<Player> players = new ArrayList<Player>();

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (event.getClickedBlock().getType()
					.equals(Material.BREWING_STAND)) {
				event.setCancelled(true);
				player.openInventory(Bukkit.getServer().createInventory(player,
						InventoryType.WORKBENCH));
				players.add(player);
				player.sendMessage("IN");
				player.sendMessage(players.toString());

			}
		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent event) {
		Player player = (Player) event.getPlayer();
		if (players.contains(event.getPlayer())
				&& event.getInventory().getType()
						.equals(InventoryType.WORKBENCH)) {
			players.remove(event.getPlayer());
			player.sendMessage("OUT");
			player.sendMessage("Players: " + players.toString());
		}
	}

	@EventHandler
	public void onCraft(PrepareItemCraftEvent event) {
		if (event.getRecipe().getResult().getType().equals(Material.BED)) {

			HumanEntity human = event.getView().getPlayer();
			if (human instanceof Player) {
				Player player = (Player) human;
				if (!players.contains(player)) {
					event.getInventory().setResult(null);
					
				}else{
					player.sendMessage("Sí");
				}
			}

		}
	}

}
