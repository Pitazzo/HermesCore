package es.programahermes.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.programahermes.MySQL;

public class VSC implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if(player instanceof Player){
			if(cmd.getName().equalsIgnoreCase("csp")){
				if(player.getInventory().contains(Material.ARROW)){
					int sed = (int) MySQL.getSed(player);
					int residual = (int) MySQL.getResidual(player);
					player.sendMessage(ChatColor.RED+"+-------+Consola de soporte vital+-------+");
					player.sendMessage(ChatColor.GOLD+"Oxígeno: "+ChatColor.GREEN+"58%");
					player.sendMessage(ChatColor.GOLD+"Nivel de hidratación: "+ChatColor.GREEN+sed+"%");
					player.sendMessage(ChatColor.GOLD+"Almacenaje de residuos: "+ChatColor.GREEN+residual+"%");
					player.sendMessage(ChatColor.RED+"+-------+Consola de soporte vital+-------+");
					return true;
				}else{
					player.sendMessage(ChatColor.RED+"No cuenta con una consola de soporte vital (CSP)");
					return true;
				}
			}
		}
		return false;
	}
}
