package es.programahermes.Utilidades;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.InventoryView;

import es.programahermes.MySQL;

public class Furnaces implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		InventoryView view = event.getView();
		InventoryAction action = event.getAction();
		if (view.getType().equals(InventoryType.FURNACE)) {
			InventoryAction action1 = event.getAction();
			if (event.getSlotType().equals(SlotType.CRAFTING)) {
				if (action1.equals(InventoryAction.PLACE_ALL)
						|| action1.equals(InventoryAction.PLACE_ONE)
						|| action1
								.equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)
						|| action1.equals(InventoryAction.PLACE_SOME)) {
					int material = event.getCursor().getType().getId();
					if (player.hasPermission("hermescore.cook." + material)
							|| player.isOp()) {

					} else

						player.sendMessage(ChatColor.GOLD
								+ "¡No sabes como manejar este horno! Alejate, es peligroso sin los conocimeintos adecuados");
					event.setCancelled(true);
				}
			}

		} else {
			if (event.getSlotType().equals(SlotType.RESULT)) {
				if (action.equals(InventoryAction.PICKUP_ALL)
						|| action.equals(InventoryAction.PICKUP_ONE)
						|| action.equals(InventoryAction.PICKUP_SOME)) {
					int material2 = event.getCursor().getType().getId();
				
					player.sendMessage(ChatColor.GOLD
							+ "¡No sabes como manejar este horno! Alejate, es peligroso sin los conocimeintos adecuados");
				event.setCancelled(true);
				}
			}
		}
	}
}
