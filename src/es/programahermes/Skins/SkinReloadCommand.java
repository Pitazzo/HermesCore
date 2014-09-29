package es.programahermes.Skins;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkinReloadCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("recargarskin")) {
			if(sender instanceof Player){
				Player player = (Player) sender;
				SkinManager.setSkin(player);
				return true;
			}
		}
		return false;
	}

}
