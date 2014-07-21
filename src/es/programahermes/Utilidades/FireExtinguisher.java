package es.programahermes.Utilidades;

import java.util.ArrayList;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class FireExtinguisher implements Listener{

	@EventHandler
	public void onIntercat(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(player.getItemInHand().getType().equals(Material.GOLD_SPADE)){
				ArrayList<Block> blocks = new ArrayList<Block>();
				Block block = event.getClickedBlock().getRelative(BlockFace.UP);
				blocks.add(block.getRelative(BlockFace.EAST));
				blocks.add(block.getRelative(BlockFace.NORTH));
				blocks.add(block.getRelative(BlockFace.SOUTH));
				blocks.add(block.getRelative(BlockFace.WEST));
				blocks.add(block);
				blocks.add(block.getRelative(BlockFace.DOWN));
				blocks.add(block.getRelative(BlockFace.NORTH_WEST));
				blocks.add(block.getRelative(BlockFace.SOUTH_WEST));
				
				for(Block fire : blocks){
					if(fire.getType().equals(Material.FIRE)){
						fire.setType(Material.AIR);
					}
					player.playEffect(fire.getLocation(), Effect.SMOKE, 10);
					player.playSound(fire.getLocation(), Sound.FIZZ, 1.0F, 1.0F);
					}
				player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() - 10));
				player.setVelocity(player.getLocation().getDirection().multiply(-0.25));
			}
		}
	}
	
}
