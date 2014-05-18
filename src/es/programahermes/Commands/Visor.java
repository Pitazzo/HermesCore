package es.programahermes.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Visor implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("visor")) {
			Player player = (Player) sender;
			if(args.length == 0){
				if(player.getInventory().getHelmet().getType().equals(Material.GOLD_HELMET)){
					player.sendMessage(ChatColor.BLUE+"Has descativado el protector solar");
					player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
					return true;
				}else{
					if(player.getInventory().getHelmet().getType().equals(Material.IRON_HELMET)){
						player.sendMessage(ChatColor.BLUE+"Has activado el protector solar");
						player.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));
						return true;
					}else{
						player.sendMessage(ChatColor.BLUE+"Para activar o desactivar tu protector solar necesitas tener un visor");
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
