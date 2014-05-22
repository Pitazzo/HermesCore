package es.programahermes.Training;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import es.programahermes.MySQL;
import es.programahermes.Utilidades.ModiferConverter;
import es.programahermes.Utilidades.Scoreboard;

public class ETS implements Listener {

	HashMap<String, Long> cooldownP = new HashMap<String, Long>();

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if (player.getItemInHand().getType().equals(Material.AIR)) {
				if (event.getClickedBlock().getType().equals(Material.WOOL)) {
					MySQL.addFatiga(player, 0.2 * ModiferConverter
							.SacalaReverse(TrainingSQL.getFTS(player)));
					player.playSound(player.getLocation(), Sound.VILLAGER_HIT,
							3.0F, 3.0F);
					TrainingSQL.addFTS(player, 0.05);
					MySQL.removeSed(player, 0.1);
					double random = Math.random();
					if (random * 100 < 1.5) {
						player.damage(3.0);
						player.sendMessage(ChatColor.RED
								+ "¡Ouch! ¡No golpees con tanta fuerza o te lesionarás!");
					}
				}

			}

		}
	}

	@EventHandler
	public void onInteract2(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction().equals(Action.LEFT_CLICK_AIR)
				|| event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if (player.getItemInHand().getType().equals(Material.INK_SACK)) {
				if (player.getItemInHand().getDurability() == 2) {
					if (player.getHealth() > 10) {
						if (MySQL.getFatiga(player) < 70) {
							if (cooldownP.containsKey(player.getName())) {
								long diff = (System.currentTimeMillis() - cooldownP
										.get(player.getName())) / 1000;
								if (diff < 20) {
									double random = Math.random() * 100;
									if (random < 5) {
										player.sendMessage(ChatColor.DARK_RED
												+ "¡Ouch!");
										player.sendMessage(ChatColor.RED
												+ "¡Más despacio! Te acabas de hacer daño, podría haber sido grave...");
										player.damage(1);
										Scoreboard.showScore(player);

									}
									MySQL.addFatiga(player,
											0.5 * ModiferConverter
													.SacalaReverse(TrainingSQL
															.getFTS(player)));
									int show = (int) (20 - diff);
									player.sendMessage(ChatColor.GOLD
											+ "Mejor espera unos "
											+ show
											+ " segundos más, si entrenas tan rápido solo te cansarás más y puedes lesionarte");
									Scoreboard.showScore(player);

								} else {
									MySQL.addFatiga(player,
											0.6 * ModiferConverter
													.SacalaReverse(TrainingSQL
															.getFTS(player)));
									TrainingSQL.addFTS(player, 0.2);
									player.sendMessage(ChatColor.GREEN
											+ "¡Así se hace! Sigue así :)");
									cooldownP.put(player.getName(),
											System.currentTimeMillis());
									Scoreboard.showScore(player);

								}
							} else {
								MySQL.addFatiga(player, 0.6);
								TrainingSQL.addFTS(player, 0.2);
								player.sendMessage(ChatColor.GREEN
										+ "¡Así se hace! Sigue así :)");
								cooldownP.put(player.getName(),
										System.currentTimeMillis());
								Scoreboard.showScore(player);

							}

						} else {
							player.sendMessage(ChatColor.RED
									+ "Mejor descansa un poco, estás demasiado cansado para entrenar");

						}
					} else {
						player.sendMessage(ChatColor.RED
								+ "Estás demasiado débil para entrenar ahora");

					}
				}
			}
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (!event.isCancelled()) {
			if (event.getBlock().getType().equals(Material.LOG)
					|| event.getBlock().getType().equals(Material.LOG_2)
					|| event.getBlock().getType().equals(Material.GRASS)
					|| event.getBlock().getType().equals(Material.DIRT)
					|| event.getBlock().getType().equals(Material.SAND)
					|| event.getBlock().getType().equals(Material.GRAVEL)) {
				if (TrainingSQL.getFTS(player) < 65) {
					event.setCancelled(true);
					player.sendMessage(ChatColor.RED
							+ "No tienes fuerza suficiente para romper ese bloque");
				}
			}
		}
	}
}
