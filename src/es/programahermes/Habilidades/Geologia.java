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
		Material material = event.getBlock().getType();
		int level = MySQL.getLevel(player);
		if (MySQL.getHability(player).equals("Geologia")) {

			if (material.equals(Material.SANDSTONE)) {
				double sandstone = 2.5;
				MySQL.addPoints(player, sandstone / level);
			}
			if (material.equals(Material.SAND)) {
				double sand = 0.5;
				MySQL.addPoints(player, sand / level);
			}
			if (material.equals(Material.GRAVEL)) {
				double gravel = 0.5;
				MySQL.addPoints(player, gravel / level);
			}
			if (material.equals(Material.STONE)) {

				double stone = 2;
				MySQL.addPoints(player, stone / level);
			}
			if (material.equals(Material.IRON_ORE)) {
				double iron = 7;
				MySQL.addPoints(player, iron / level);
			}
			if (material.equals(Material.GRAVEL)) {
				double gravel = 1;
				MySQL.addPoints(player, gravel / level);
			}
			if (material.equals(Material.GOLD_ORE)) {
				double gold = 10;
				MySQL.addPoints(player, gold / level);
			}

			if (material.equals(Material.REDSTONE_ORE)) {
				double redstone = 15;
				MySQL.addPoints(player, redstone / level);
			}

			if (material.equals(Material.COAL_ORE)) {
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