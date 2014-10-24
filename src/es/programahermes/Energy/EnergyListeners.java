package es.programahermes.Energy;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import programahermes.es.Crafteos.Items;

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
										"Batería descargada"));
			}
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if(CustomTools.isATool(event.getPlayer().getItemInHand())){
			if(CustomTools.getCarga(event.getPlayer().getItemInHand())>0){
				CustomTools.useTool(event.getPlayer().getItemInHand());
			}else{
				event.getPlayer().sendMessage(ChatColor.DARK_RED+"La carga de esta herramienta no es suficiente");
				event.setCancelled(true);
			}
		}
	}
	
}
