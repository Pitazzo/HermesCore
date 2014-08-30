package es.programahermes.Energy;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import es.programahermes.Utilidades.Items;
import es.programahermes.Utilidades.Miscelaneo;

public class EnergyListeners implements Listener{

	@EventHandler
	public void onCraft(CraftItemEvent event) {
		for (ItemStack item : event.getInventory().getMatrix()) {
			if (Miscelaneo.getName(item).contains("Batería cargada")) {
				event.getWhoClicked()
						.getLocation()
						.getWorld()
						.dropItem(
								event.getWhoClicked().getLocation(),
								Items.SemiCustom(Material.COAL, 1,
										"Batería descargada",
										item.getItemMeta().getLore().get(0), ""));
			}
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (CustomTool.isATool(event.getPlayer().getItemInHand())) {
			CustomTool item = new CustomTool(event.getPlayer().getItemInHand(),
					event.getPlayer().getItemInHand().getItemMeta()
							.getDisplayName());
			if(item.getUsos()>0){
				item.setUsos(item, item.getUsos()-1);
			}else{
				event.getPlayer().sendMessage(ChatColor.DARK_RED+"A tu herramienta no le queda energía suficiente");
				event.setCancelled(true);
			}
		}
	}
	
}
