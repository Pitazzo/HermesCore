package es.programahermes.SoporteVital.Oxygen;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MarsOxygen implements CommandExecutor {

	public static HashMap<Player, ItemStack> tanques = new HashMap<Player, ItemStack>();

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("equipartanque")) {
				if (OxygenCommand.hasOxygen(player.getItemInHand())) {
					if (OxygenCommand.getOxygen(player.getItemInHand()) > 1) {

						if (OxygenCommand.getOxygen(player.getItemInHand()) < 50) {
							player.sendMessage(ChatColor.DARK_GREEN
									+ "El tanque que has seleccionado no te va a poder proporcionar oxígeno demasiado tiempo, no vayas muy lejos");
						}

						tanques.put(player, player.getItemInHand());
						player.sendMessage(ChatColor.BLUE
								+ "Tanque de oxígeno seleccionado");
						// setskin con tanque

					} else {
						player.sendMessage(ChatColor.DARK_GREEN
								+ "El tanque que has seleccionado no tiene suficiente oxígeno");
					}
				} else {
					player.sendMessage(ChatColor.DARK_GREEN
							+ "No has seleccionad ningún tanque de oxígeno");
				}

			}

		}
		return false;
	}
	
	public static void checkOutside(Player player){
		if(!Oxygen.isPresurizada(player.getLocation())){
			if(tanques.get(player) != null){
				if(OxygenCommand.getOxygen(tanques.get(player)) > 3){
					OxygenCommand.removeOxygen(tanques.get(player), 10);
				}else{
					player.sendMessage("Se acabó el O2");
				}
			}
		}
		
	}
}
