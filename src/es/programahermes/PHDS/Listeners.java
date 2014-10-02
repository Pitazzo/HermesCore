package es.programahermes.PHDS;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Listeners implements Listener {

	public static Location spawn = new Location(Bukkit.getWorld("Limbo"), 64,
			10, -500);

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		player.getLocation().getBlock().setType(Material.BEDROCK);
		player.getLocation().getBlock().getRelative(BlockFace.NORTH).setType(Material.BEDROCK);
		
		if (!DeathSQL.isInLimbo(player.getName())) {
			DeathSQL.setInLimbo(player.getName(), true);
			DeathSQL.setTimeLeft(player.getName(), 300);
			DeathSQL.setDeathLoc(player.getName(), player.getLocation());

		} else {
			DeathSQL.addTimeLeft(player.getName(), 300);
			player.teleport(spawn);
		}
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		if (DeathSQL.isInLimbo(event.getPlayer().getName())) {
			event.setRespawnLocation(spawn);
		}

	}
}
