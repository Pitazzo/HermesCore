package es.programahermes.Utilidades;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import es.programahermes.MySQL;

public class Miscelaneo implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		MySQL.removePoints(player, 40);
	}
}
