package es.programahermes.Utilidades;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GPS implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("gps")){
			if(args.length==0){
				if(player.getItemInHand().getType().equals(Material.COMPASS)){
					int latitud = (int) player.getLocation().getZ();
					int longitud = (int) player.getLocation().getX();
					player.sendMessage(ChatColor.RED+"+-----GPS-----+");
					if(latitud > 0){
						player.sendMessage(ChatColor.GREEN+"Latitud: "+ChatColor.GOLD+latitud+"E");
					}else{
						player.sendMessage(ChatColor.GREEN+"Latitud: "+ChatColor.GOLD+latitud*(-1)+"O");
					}
					
					player.sendMessage(ChatColor.RED+"+-----GPS-----+");
					return true;
				}
			}else{
				return false;
			}
		}
		
				return false;
	}
	
	
}
