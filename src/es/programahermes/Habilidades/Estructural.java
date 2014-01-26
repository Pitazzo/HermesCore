package es.programahermes.Habilidades;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import es.programahermes.MySQL;

public class Estructural implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		String material = event.getBlock().getType().toString();
		if (MySQL.getHability(player).equals("Estructural")) {
			MySQL.addEarnedPoints(player, "break", material, 1);
		}
	}

	@EventHandler
	public void onPlayerPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		String material = event.getBlock().getType().toString();
		if (MySQL.getHability(player).equals("Estructural")) {
			MySQL.addEarnedPoints(player, "place", material, 1);

		}
	}

}
