package es.programahermes.Utilidades;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("test")) {
				player.sendMessage(ChatColor.BLUE+"Comando de prueba ejecutado");
				// Desmayo.setPostDesmayo(player.getName());

				return true;
			}
		}

		return false;
	}

}
