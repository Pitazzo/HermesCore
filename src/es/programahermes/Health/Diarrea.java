package es.programahermes.Health;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import es.programahermes.MySQL;
import es.programahermes.PHDS.DeathSQL;
import es.programahermes.Utilidades.Miscelaneo;

public class Diarrea implements Listener {

	@EventHandler
	public void onConsume(PlayerItemConsumeEvent event) {
		Player player = event.getPlayer();
		if (HealthSQL.Diarrea(player.getName())) {
			if (!event.getItem().getItemMeta().getDisplayName().contains("Frasco de ")) {
				if (!event.getItem().getType().equals(Material.POTION)) {
					Miscelaneo.setWalkSpeed(player, 0.2);
					MySQL.setResidual(player.getName(), 0);
					MySQL.removeSed(player.getName(), 8);
					ItemStack caca = new ItemStack(Material.INK_SACK, 1);
					caca.setDurability((short) 8);
					player.getWorld().dropItemNaturally(player.getLocation(),
							caca);
					player.playSound(player.getLocation(), Sound.DIG_SNOW,
							0.5F, 0.0F);
					player.sendMessage(ChatColor.GREEN + "[Soporte Vital]"
							+ ChatColor.RED + "Has evacuado");
				}
			}else{
				if(Miscelaneo.getName(event.getItem()).equals("Antibi�tico")){
					if(Math.random()*100<25){
						HealthSQL.setDiarrea(player.getName(), false);
					}
				}
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK
				|| event.getAction() == Action.RIGHT_CLICK_AIR) {
			@SuppressWarnings("deprecation")
			List<Block> los = event.getPlayer().getLineOfSight(null, 5);
			if (event.getPlayer().isSneaking()) {
				for (Block b : los) {
					if (b.getType() == Material.STATIONARY_WATER) {
						if (MySQL.getSed(event.getPlayer().getName()) < 95) {
							MySQL.addSed(event.getPlayer().getName(), 5);
							for (Player others : Bukkit.getOnlinePlayers()) {
								if (others.getWorld().equals(
										event.getPlayer().getWorld())) {
									if (event.getPlayer().getLocation()
											.distance(others.getLocation()) < 10) {
										others.sendMessage("*"
												+ event.getPlayer()
														.getDisplayName()
												+ " se agacha y bebe agua");
									}
								}
							}
							double rdm = Math.random() * 100;
							if (rdm < 25) {
								HealthSQL.setDiarrea(event.getPlayer().getName(), true);
							}
						} else {
							event.getPlayer()
									.sendMessage(
											ChatColor.GOLD
													+ "Ya has bebido suficiente");
						}

						break;
					}
				}
			}
		}

	}

	public static void diarreaUpdate(Plugin plugin) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player player : Bukkit.getOnlinePlayers())
					if (HealthSQL.Diarrea(player.getName())) {
						if(!DeathSQL.isInLimbo(player.getName())){
							MySQL.removeSed(player.getName(), 8);
							player.sendMessage("Oh no... otra vez no...");
							for (Player others : Bukkit.getOnlinePlayers()) {
								if (others.getWorld().equals(player.getWorld())) {
									if (player.getLocation().distance(
											others.getLocation()) < 10) {
										others.sendMessage("*"
												+ player.getDisplayName()
												+ " ha vomitado");
										ItemStack caca = new ItemStack(
												Material.INK_SACK, 1);
										caca.setDurability((short) 8);
										player.getWorld().dropItemNaturally(
												player.getLocation(), caca);
									}
								}
							}
						}
					}
			}
		}, 100L, 20 * 60 * 4);
	}

}
