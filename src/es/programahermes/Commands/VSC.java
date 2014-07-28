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
			if(cmd.getName().equalsIgnoreCase("csv")){
				if(player.getInventory().contains(Material.NETHER_BRICK_ITEM)){
					int sed = (int) MySQL.getSed(player.getName());
					int fatiga = (int) MySQL.getFatiga(player.getName());
					int residual = (int) MySQL.getResidual(player.getName());
					int food = (100 * player.getFoodLevel() / 20);
					player.sendMessage(ChatColor.RED+"+-------+Consola de soporte vital+-------+");
					player.sendMessage(ChatColor.GOLD+"Oxígeno: "+ChatColor.GREEN+MySQL.getOxygen(player.getName())+"L");
					player.sendMessage(ChatColor.GOLD+"Nivel de hidratación: "+ChatColor.GREEN+sed+"%");
					player.sendMessage(ChatColor.GOLD+"Fatiga: "+ChatColor.GREEN+fatiga+"%");
					player.sendMessage(ChatColor.GOLD+"Hambre: "+ChatColor.GREEN+food+"%");
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
