package es.programahermes.PHDS;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DeathTimer {

	public static void limbo(Plugin plugin) {
		Bukkit.getServer().getScheduler()
				.scheduleSyncRepeatingTask(plugin, new Runnable() {
					@Override
					public void run() {
						for (Player player : Bukkit.getOnlinePlayers()) {
							if (DeathSQL.isInLimbo(player.getName())) {
								if (DeathSQL.getTimeLeft(player.getName()) > 0) {
									DeathSQL.removeTimeLeft(player.getName(),
											20);
								} else {
									player.sendMessage(ChatColor.DARK_GREEN
											+ "Poco a poco, recuperas la noción de la realidad; "
											+ "tienes un fuerte dolor de cabeza y estás débil");
									player.teleport(DeathSQL.getDeathLoc(player
											.getName()));
									DeathSQL.setInLimbo(player.getName(), false);
									//añadir efectos post mareo
								}

							}
						}
					}
				}, 200L, 20 * 20);
	}
}
