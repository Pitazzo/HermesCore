package es.programahermes.Habilidades;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import es.programahermes.Main;
import es.programahermes.MySQL;

public class Biologia implements Listener {

	public Main plugin;

	public Biologia(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		Block block = event.getClickedBlock();
		ItemStack item = player.getItemInHand();
		String habilidad = MySQL.getHability(player);
		int level = MySQL.getLevel(player);
		if (habilidad.equals("Biologia")) {
			if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
				if (block.getType().equals(Material.SOIL)) {

					if (item.getType().equals(Material.SEEDS)) {
						double seeds = plugin.getConfig().getDouble("Puntos.Biologia.Semillas");
						MySQL.addPoints(player, seeds / level);
					}
					if (item.getType().equals(Material.SAPLING)) {
						double sapling = plugin.getConfig().getDouble("Puntos.Biologia.Sapling");
						MySQL.addPoints(player, sapling / level);
					}
				}

			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		Block block = event.getBlock();
		int level = MySQL.getLevel(player);
		if(MySQL.getHability(player).equals("Biologia")){
			if(block.getType().equals(Material.CROPS)){
				double crops = 5;
				MySQL.addPoints(player, crops/level);
			}
			
		}
		
	}
}
