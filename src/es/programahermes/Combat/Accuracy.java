package es.programahermes.Combat;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.shampaggon.crackshot.events.WeaponHitBlockEvent;

public class Accuracy implements Listener, CommandExecutor {

	public static ArrayList<String> isTraining = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("entrenartiro")) {

			if (sender instanceof Player) {
				if (args.length == 0) {
					if(isTraining.contains(sender.getName())){
						isTraining.remove(sender.getName());
						sender.sendMessage(ChatColor.RED+"Ya no estás entrenando tus habilidades de tiro");
						return true;
					}else{
						isTraining.add(sender.getName());
						sender.sendMessage(ChatColor.GREEN+"Estás entrenando tus habilidades de tiro");
						sender.sendMessage(ChatColor.GREEN+"Recuerda que ahora fallar la diana te restará puntos");
						return true;
					}
				}
			}else{
				return false;
			}
		}
		return false;

	}

	@EventHandler
	public void onHit(WeaponHitBlockEvent event) {
		Player player = event.getPlayer();
		if(isTraining.contains(player.getName())){
			if(event.getBlock().getType().equals(Material.STAINED_CLAY)&&event.getBlock().getData()==13){
				int distance = (int) player.getLocation().distance(event.getBlock().getLocation());
				player.sendMessage(ChatColor.GREEN+"¡Buen tiro! Has ganado "+distance*0.25+" puntos. El objetivo estaba a "+distance+" metros.");
			}else{
				player.sendMessage(ChatColor.RED+"-2");
			}
		}else{
			player.sendMessage(isTraining.toString());
			if(event.getBlock().getType().equals(Material.STAINED_CLAY)&&event.getBlock().getData()==13){
				int distance = (int) player.getLocation().distance(event.getBlock().getLocation());
				player.sendMessage(ChatColor.GOLD+"Has hecho blanco, el objetivo estaba a "+distance+" metros");
			}
		}
	}
}
