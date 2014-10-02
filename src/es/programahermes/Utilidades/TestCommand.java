package es.programahermes.Utilidades;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
/*import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.player.SpoutPlayer;
*/
public class TestCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			//SpoutPlayer splayer = (SpoutPlayer) player;
			if (cmd.getName().equalsIgnoreCase("test")) {
				player.sendMessage(ChatColor.BLUE+"Comando de prueba ejecutado");
				/*SpoutManager.getSkyManager().setCloudHeight(splayer, 300);
				SpoutManager.getSkyManager().setSunVisible(splayer, false);
				SpoutManager.getSkyManager().setMoonTextureUrl(splayer, "http://i.imgur.com/VVNOTnw.png");
				SpoutManager.getSkyManager().setMoonSizePercent(splayer, 200);
				SpoutManager.getSkyManager().setSkyColor(splayer, new Color(247, 156, 10));
				SpoutManager.getSkyManager().setFogColor(splayer, new Color(247, 156, 10));
				SpoutManager.getSkyManager().setFogColor(splayer, new Color(247, 156, 10));*/
				
				return true;
			}
		}

		return false;
	}

}
