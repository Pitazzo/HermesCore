package es.programahermes.Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.programahermes.Health.Anemia;
import es.programahermes.Training.TrainingSQL;

public class PointsAdjust implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("calcularpuntos")) {
			if (args.length == 0) {
				if(sender.isOp()){
					Anemia.anemiaCheck();
					for(OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()){
						Player player = Bukkit.getPlayer(offlinePlayer.getName());
						TrainingSQL.removeFTI(player, 5);
						TrainingSQL.removeFTS(player, 5);
					}
				}
				return true;
			}
			return false;
		}
		return false;
	}

}
