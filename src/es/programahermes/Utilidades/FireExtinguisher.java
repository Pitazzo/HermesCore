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
import org.bukkit.event.player.PlayerInteractEvent;

public class FireExtinguisher implements Listener{

	@EventHandler
	public void onIntercat(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(player.getItemInHand().getType().equals(Material.GOLD_SPADE)){
				ArrayList<Block> blocks = new ArrayList<Block>();
				blocks.add(event.getClickedBlock().getRelative(BlockFace.EAST));
				blocks.add(event.getClickedBlock().getRelative(BlockFace.NORTH));
				blocks.add(event.getClickedBlock().getRelative(BlockFace.SOUTH));
				blocks.add(event.getClickedBlock().getRelative(BlockFace.WEST));
				blocks.add(event.getClickedBlock().getRelative(BlockFace.UP));
				blocks.add(event.getClickedBlock().getRelative(BlockFace.DOWN));
				blocks.add(event.getClickedBlock().getRelative(BlockFace.NORTH_WEST));
				blocks.add(event.getClickedBlock().getRelative(BlockFace.NORTH_WEST));
				blocks.add(event.getClickedBlock().getRelative(BlockFace.SOUTH_WEST));
				blocks.add(event.getClickedBlock().getRelative(BlockFace.SOUTH_WEST));
				for(Block block : blocks){
					if(block.getType().equals(Material.FIRE)){
						block.setType(Material.AIR);
					}
					player.playEffect(block.getLocation(), Effect.SMOKE, 10);
					player.playSound(block.getLocation(), Sound.FIZZ, 1.0F, 1.0F);
					}
				player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() - 10));
				player.setVelocity(player.getLocation().getDirection().multiply(-0.25));
			}
		}
	}
	
}
