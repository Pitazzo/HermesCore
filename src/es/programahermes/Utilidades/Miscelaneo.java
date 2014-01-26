package es.programahermes.Utilidades;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import es.programahermes.MySQL;

public class Miscelaneo implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		MySQL.removePoints(player, 40);
	}

	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		if (!MySQL.dbContanisPlayer(player)) {
			event.disallow(
					Result.KICK_OTHER,
					ChatColor.GREEN
							+ "¡Acceso denegado, no cuentas con un perfil de jugador! Si crees que es un error contacta con el staff");
		}
	}
}
