package es.programahermes.Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import es.programahermes.MySQL;
import es.programahermes.Health.Anemia;

public class PointsAdjust implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("calcularpuntos")) {
			if (args.length == 0) {
				if(sender.isOp()){
					
					for(OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()){
						if(MySQL.dbContanisPlayer(offlinePlayer.getName())){
							Anemia.anemiaCheck(offlinePlayer.getName());
						}
							}
				}
				return true;
			}
			return false;
		}
		return false;
	}

}
