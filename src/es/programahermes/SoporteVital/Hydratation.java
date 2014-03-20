package es.programahermes.SoporteVital;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import es.programahermes.MySQL;
import es.programahermes.Utilidades.Scoreboard;

public class Hydratation implements Listener {

	public static void thirstUpdate(Plugin plugin) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player player : Bukkit.getOnlinePlayers()) {
					MySQL.removeSed(player, 1);
					Scoreboard.showScore(player);
					if(MySQL.getSed(player)<=20){
						player.setWalkSpeed((float) 0.1);
					}else{
						player.setWalkSpeed((float) 0.2);
					}
					
					
					if (MySQL.getSed(player) <= 20) {
						player.sendMessage(ChatColor.GREEN
								+ "[Soporte Vital]"
								+ ChatColor.RED
								+ "¡Bebe algo pronto, tu nivel de hidratación es muy bajo!");
						player.playSound(player.getLocation(), Sound.BAT_DEATH,
								0.5F, 0.0F);
						if (MySQL.getSed(player) <= 0) {
							player.damage(100);

						}
					}

				}

			}
		}, 100L, 20 * 60);
	}



	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)
				|| event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (player.getItemInHand().getType().equals(Material.POTION)) {

				if (!(MySQL.getSed(player) >= 100)) {
					MySQL.addSed(player, 10);
					player.getInventory().removeItem(
							new ItemStack(Material.POTION, 1));
					player.getInventory().addItem(
							new ItemStack(Material.GLASS_BOTTLE, 1));
					player.updateInventory();
					player.sendMessage(ChatColor.GREEN + "[Soporte Vital]"
							+ ChatColor.RED + "¡Te has rehidratado!");
				} else {
					player.sendMessage(ChatColor.GREEN + "[Soporte Vital]"
							+ ChatColor.RED + "¡Ya has bebido suficiente!");
				}
				Scoreboard.showScore(player);
			}
			if (player.getItemInHand().getType().equals(Material.MELON)) {

				if (!(MySQL.getSed(player) >= 100)) {
					MySQL.addSed(player, 10);
					player.getInventory().removeItem(
							new ItemStack(Material.MELON, 1));
					player.updateInventory();
					player.sendMessage(ChatColor.GREEN + "[Soporte Vital]"
							+ ChatColor.RED + "¡Te has rehidratado!");
				} else {
					player.sendMessage(ChatColor.GREEN + "[Soporte Vital]"
							+ ChatColor.RED + "¡Ya has bebido suficiente!");
				}
				Scoreboard.showScore(player);
			}
			if (player.getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) {

				if (!(MySQL.getSed(player) >= 100)) {
					MySQL.addSed(player, 10);
					player.getInventory().removeItem(
							new ItemStack(Material.MUSHROOM_SOUP, 1));
					player.getInventory().addItem(
							new ItemStack(Material.BOWL, 1));
					player.updateInventory();
					player.sendMessage(ChatColor.GREEN + "[Soporte Vital]"
							+ ChatColor.RED + "¡Te has rehidratado!");
				} else {
					player.sendMessage(ChatColor.GREEN + "[Soporte Vital]"
							+ ChatColor.RED + "¡Ya has bebido suficiente!");
				}
				Scoreboard.showScore(player);
			}
		}
	}
}