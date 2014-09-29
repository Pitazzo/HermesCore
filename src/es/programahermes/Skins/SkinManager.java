package es.programahermes.Skins;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.player.SpoutPlayer;

import es.programahermes.Main;
import es.programahermes.MySQL;

public class SkinManager implements Listener {

	public static void setSkin(Player player) {
		SpoutPlayer spoutPlayer = (SpoutPlayer) player;
		try {
			spoutPlayer.setSkin("http://programahermes.es/"
					+ MySQL.getSkin(player.getName()));
			player.sendMessage(ChatColor.DARK_GREEN
					+ "Skin cargada de los servidores de Programa Hermes con éxito");
			
		} catch (Exception e) {
			spoutPlayer
					.setSkin("http://programahermes.es/assets/skinus/HUE.png");
			player.sendMessage(ChatColor.DARK_RED
					+ "Skin no encontrada. Asigna una inmediatamente.");
		}
	}

	public static void timer(final Plugin plugin, final Player player) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				setSkin(player);
			}
		}, 15 * 10L);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		timer(Main.plugin, event.getPlayer());
	}

}
