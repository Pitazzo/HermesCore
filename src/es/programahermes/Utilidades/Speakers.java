package es.programahermes.Utilidades;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import es.programahermes.Main;

public class Speakers implements Listener, CommandExecutor {

	public Main plugin;
	
	public Speakers(Main plugin){
		this.plugin = plugin;
		}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if (player.getItemInHand().getType().equals(Material.BONE)) {

			for (Player online : Bukkit.getOnlinePlayers()) {
				if (online.getLocation().distance(player.getLocation()) < 20) {
					online.sendMessage(ChatColor.GREEN + "[Megáfono] "
							+ ChatColor.GOLD + event.getMessage());
				}

			}
		}
	}

	public static String convertLocationToString(Location loc) {
        return loc.getWorld().getName() + "_" + loc.getX() + "_" + loc.getY() + "_" + loc.getZ() + "_" + loc.getPitch() + "_" + loc.getYaw();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("location")) {
			Player player = (Player) sender;
			if(args.length==1){
				String loc = convertLocationToString(player.getLocation());
				plugin.getConfig().set("Locations", args[0]);
				plugin.saveConfig();
				return true;
						}else{
				return false;
			}
		}
		return false;
	}
}
