package es.programahermes.Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import es.programahermes.MySQL;
import es.programahermes.Health.Anemia;
import es.programahermes.Training.TrainingSQL;

public class PointsAdjust {

	public static void pointsAdjust(Plugin plugin) {
		Bukkit.getServer().getScheduler()
				.scheduleSyncRepeatingTask(plugin, new Runnable() {
					@Override
					public void run() {
						for (OfflinePlayer offlinePlayer : Bukkit
								.getOfflinePlayers()) {
							if (MySQL.dbContanisPlayer(offlinePlayer.getName())) {
								Anemia.anemiaCheck(offlinePlayer.getName());
								TrainingSQL.removeFTI(offlinePlayer.getName(), 0.06);
								TrainingSQL.removeFTS(offlinePlayer.getName(), 0.06);
							}
						}
					}

				}, 200L, 20 * 60 * 60);
	}

}
