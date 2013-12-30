package es.programahermes.Habilidades;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldguard.protection.managers.RegionManager;

import es.programahermes.Main;
import es.programahermes.MySQL;

public class Geologia implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Material material = event.getBlock().getType();
		int level = MySQL.getLevel(player);
		if (MySQL.getHability(player).equals("Geologia")) {

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

	
	// celdas de energia y prospeccion
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Action action = event.getAction();
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		ItemStack tool = new ItemStack(Material.GOLD_SPADE, 1);
		ItemStack cell = new ItemStack(Material.COAL, 1);
		ItemStack escoria = new ItemStack(Material.STONE, 1);
		
		double random = Math.random() * 10;
		int level = MySQL.getLevel(player);
		if (MySQL.getHability(player).equals("Geologia")) {
			if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
				if (block.getType().equals(Material.COAL_ORE)) {
					if (player.getItemInHand().equals(tool)) {
						if (random >= 1.5) {
							Location location = block.getLocation();
							block.setType(Material.AIR);
							player.getWorld().dropItemNaturally(location, cell);
							player.getWorld().dropItemNaturally(location, escoria);
							player.getWorld().playSound(location, Sound.FIZZ,
									0.5F, 0.0F);
							player.sendMessage(ChatColor.GREEN
									+ "¡Has obtenido una celda de energía");
							int coal = 7;
							MySQL.addPoints(player, coal/level);
							tool.setDurability((short) -1);
						} else {
							
							player.getWorld().createExplosion(block.getLocation(), 2.0F);
							player.sendMessage(ChatColor.RED
									+ "Parece que algo ha fallado");
						}

					}
				}
			}
		}

	}
}