package es.programahermes.Utilidades;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.player.RenderDistance;
import org.getspout.spoutapi.player.SpoutPlayer;


public class TestCommand implements CommandExecutor, Listener {

	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("test")) {
				player.sendMessage(ChatColor.BLUE+"Comando de prueba ejecutado");
				SpoutPlayer splayer = (SpoutPlayer) player;
				splayer.setRenderDistance(RenderDistance.TINY);
				return true;
			}
		}

		return false;
	}


	
}
