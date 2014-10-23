package es.programahermes.Utilidades;

import net.morematerials.MoreMaterials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.Material;

public class TestCommand implements CommandExecutor {

    public static MoreMaterials mm = (MoreMaterials)
    Bukkit.getServer().getPluginManager().getPlugin("MoreMaterials");
	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("test")) {
				player.sendMessage(ChatColor.BLUE+"Comando de prueba ejecutado");
				// Desmayo.setPostDesmayo(player.getName());
				/*player.setMaxHealth(30.0D);
				player.setHealthScale(60.0D);
				System.out.println("Max health: "+player.getMaxHealth());
				System.out.println("Health: "+player.getHealth());
				System.out.println("Scaled health: "+player.getHealthScale());
				return true;
				SpoutItemStack stack = new SpoutItemStack(player.getItemInHand());
				Material material = stack.getMaterial();
				System.out.println("Material: "+material);
				System.out.println("Material string: "+material.toString());
				System.out.println("Material name: "+material.getName());
				System.out.println("Material notchian name: "+material.getNotchianName());			
				*/
				
				player.sendMessage("Material: "+Ingredients.aguja_silicio.getName());
				player.sendMessage("Material: "+Ingredients.aguja_silicio.getRawId());
				SpoutItemStack stack = new SpoutItemStack(Ingredients.aguja_silicio, 1);
				player.sendMessage("ID: "+stack.getTypeId());
				return true;
			}
		}

		return false;
	}

}
