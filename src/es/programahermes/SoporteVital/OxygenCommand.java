package es.programahermes.SoporteVital;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import es.programahermes.MySQL;

public class OxygenCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("presurizar")) {
			if(args.length == 1){
				
				if(args[0].equalsIgnoreCase("tanque")){
					
					if(Oxygen.hasSuit(player)){
						int oxygen = (int) MySQL.getOxygen(player);
						if(player.getItemInHand().equals(new ItemStack(Material.NETHER_BRICK_ITEM, 1))){
							int previo = Integer.parseInt(player.getItemInHand().getItemMeta().getLore().get(1));
							player.sendMessage("previo: "+previo);
						}else{
							player.sendMessage(ChatColor.GREEN+"[Sistema presurización]"+ChatColor.RED+"Selecciona un tanque de oxígeno para presurizarlo");
							player.sendMessage(ChatColor.GREEN+"[Sistema presurización]"+ChatColor.RED+"Si ya lo tienes seleccionado, asegurate de selecciona solo uno");
							return true;
						}
						
					}else{
						player.sendMessage(ChatColor.GREEN+"[Sistema presurización]"+ChatColor.RED+"Para presurizar tu tanque necesitas llevar un traje presurizado...");
						return true;
					}
					
				}
				
				if(args[0].equalsIgnoreCase("traje")){
					
					if(Oxygen.hasSuit(player)){
						
					}else{
						player.sendMessage(ChatColor.GREEN+"[Sistema presurización]"+ChatColor.RED+"Para presurizar tu traje necesitas llevar uno...");
						return true;
					}
					
					
				}
				
			}else{
				return false;
			}
		}
		return false;
	}

}
