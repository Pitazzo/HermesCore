package es.programahermes.Utilidades;


import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Pernos implements Listener{

		
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlace(BlockPlaceEvent event){
		
		if(!event.isCancelled()){
			if(!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
				if(event.getBlock().getType().equals(Material.QUARTZ_BLOCK)){
					if (!event.getPlayer().getInventory().contains(Material.STICK, 4))
				      {
				        Player p = event.getPlayer();
				        event.setCancelled(true);
				        p.sendMessage(ChatColor.DARK_RED + "Necesitar�s algunos pernos para colocar ese bloque");
				      }
				      else {
				        if(!event.getPlayer().getInventory().contains(Material.GOLD_HOE)){
				        	Player p = event.getPlayer();
				        	event.setCancelled(true);
					        p.sendMessage(ChatColor.DARK_RED + "Necesitar�s un martillo para poder emplazar ese bloque");
				        }else{
				        	Inventory inv = event.getPlayer().getInventory();
					        inv.setItem(inv.first(Material.STICK), new ItemStack(Material.STICK, inv.getItem(inv.first(Material.STICK)).getAmount() - 4));
					        Player p = event.getPlayer();
					        p.updateInventory();
				        }
				      }
				    }
			}
		}
			
		}
	}

