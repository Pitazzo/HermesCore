package es.programahermes.Utilidades;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class TestCommand implements CommandExecutor, Listener {

	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("test")) {
				int entidades = 0;
				for(Entity entity : player.getWorld().getEntities()){
					entidades++;
					entity.remove();
				}
				player.sendMessage("Se han eliminado: "+entidades+" entidades");
				System.out.println("Se han eliminado: "+entidades+" entidades");
				return true;
			}
		}

		return false;
	}


	
}
