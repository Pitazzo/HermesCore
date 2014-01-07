package es.programahermes.Habilidades;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import es.programahermes.MySQL;

public class Geologia implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		String material = event.getBlock().getType().toString();
		if (MySQL.getHability(player).equals("Geologia")) {

			
			MySQL.addEarnedPoints(player, "break", material, 1);

			if (event.getBlock().getType().equals(Material.COAL_ORE)) {
				ItemStack drop = new ItemStack(Material.STONE, 1);
				event.getBlock().getDrops(drop);
				player.sendMessage(ChatColor.RED
						+ "¡Has malgastado un mineral del que extraer energía!");
			}

		}
	}

	@EventHandler
	public void onBuild(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (MySQL.getHability(player).equals("Geologoa")) {
			if (event.getBlock().getType().equals(Material.FENCE)) {
				double fence = 4;
				int level = MySQL.getLevel(player);
				MySQL.addPoints(player, fence / level);
			}
		}
	}

}