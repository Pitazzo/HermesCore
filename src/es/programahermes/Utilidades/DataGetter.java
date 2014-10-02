package es.programahermes.Utilidades;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DataGetter implements CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("data")) {
			if (sender instanceof Player) {
				if (sender.isOp()) {
					
					
					
					
					ItemStack inHand = ((Player) sender).getItemInHand();
					sender.sendMessage(ChatColor.GOLD
							+ "============ Información del ítem ============");
					sender.sendMessage(ChatColor.GREEN + "Nombre: "
							+ ChatColor.BLUE + inHand.getType().toString());
					sender.sendMessage(ChatColor.GREEN + "Nombre ingame: "
							+ ChatColor.BLUE
							+ Miscelaneo.getName(inHand));
					sender.sendMessage(ChatColor.GREEN + "ID: "
							+ ChatColor.BLUE + inHand.getTypeId());
					sender.sendMessage(ChatColor.GREEN + "Data: "
							+ ChatColor.BLUE + inHand.getData().toString());
					sender.sendMessage(ChatColor.GREEN + "Metadata: "
							+ ChatColor.BLUE + inHand.getItemMeta().toString());
					sender.sendMessage(ChatColor.GREEN + "Durabilidad: "
							+ ChatColor.BLUE + inHand.getDurability());
					

					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}

}
