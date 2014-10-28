package es.programahermes.SoporteVital.Oxygen;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.player.SpoutPlayer;

import es.programahermes.MySQL;

public class MarsOxygen implements Listener {

	public static void oxygenUpdate(Plugin plugin) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player player : Bukkit.getOnlinePlayers()) {

					if (!Oxygen.isPresurizada(player.getLocation())
							&& !player.getGameMode().equals(GameMode.CREATIVE)) {

						if (player.getWorld().getName().equals("Nave")) {
							// ARGO
							if (Oxygen.hasSuit(player) && checkTanque(player)) {
								checkOutside(player);
							} else {
								Oxygen.kill(player);
							}

						} else if (player.getWorld().getName().equals("Kepler")) {
							// KEPLER
							if (Oxygen.hasMask(player) && checkTanque(player)) {
								checkOutside(player);
							} else {
								Oxygen.kill(player);
							}
						}
					}

				}

			}
		}, 20*10, 20*7L);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (event.getInventory().getType().equals(InventoryType.PLAYER)) {
			if (event.getSlot() == 35) {
				Player player = (Player) event.getWhoClicked();
				SpoutPlayer splayer = (SpoutPlayer) player;

				if (Oxygen.hasOxygen(event.getCursor())) {
					// poner
					if (!MySQL.getSkin(player.getName()).contains("-b")) {
						String url = MySQL.getSkin(player.getName());
						String suburl = url.replace(".png", "");
						String sub2 = suburl + "-b.png";
						splayer.setSkin("http://178.32.219.57/" + sub2);
						player.sendMessage(ChatColor.GREEN
								+ "Has colocado un nuevo tanque de O2");
						player.playSound(player.getLocation(), Sound.DOOR_OPEN,
								1.0F, 1.0F);
					}

				} else if (!Oxygen.hasOxygen(event.getCursor())) {
					// quitar
					splayer.setSkin("http://178.32.219.57/"
							+ MySQL.getSkin(player.getName()));
					player.sendMessage(ChatColor.DARK_RED
							+ "Te has quitado un tanque de O2");
					player.playSound(player.getLocation(), Sound.DOOR_OPEN,
							1.0F, 1.0F);
				}

			}
		}

	}

	public static void checkOutside(Player player) {

		if (checkTanque(player)
				&& Oxygen.getOxygen(player.getInventory().getItem(35)) > 1) {

			if(Oxygen.hasSuit(player)){
				Oxygen.removeOxygen(player.getInventory().getItem(35), 3);
			}else if(Oxygen.hasSuit(player)){
				Oxygen.removeOxygen(player.getInventory().getItem(35), 1);
			}
			

		} else {
			Oxygen.kill(player);
		}

	}

	public static boolean checkTanque(Player player) {
		if (Oxygen.hasOxygen(player.getInventory().getItem(35))) {
			return true;
		}

		return false;
	}
}
