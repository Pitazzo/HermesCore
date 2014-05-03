package es.programahermes.Training;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import es.programahermes.MySQL;
import es.programahermes.Utilidades.Scoreboard;

public class ETS implements Listener, CommandExecutor {

	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if (player.getItemInHand().getType().equals(Material.AIR)) {
				if (event.getClickedBlock().getType().equals(Material.WOOL)) {
					MySQL.addFatiga(player, 0.2);
					player.playSound(player.getLocation(), Sound.VILLAGER_HIT,
							3.0F, 3.0F);
					TrainingSQL.addFTS(player, 0.05);
					MySQL.removeSed(player, 0.1);
					double random = Math.random();
					if (random * 100 < 5) {
						player.damage(3.0);
						player.sendMessage(ChatColor.RED
								+ "¡Ouch! ¡No golpees con tanta fuerza o te lesionarás!");
					}
				}

			}
		}
	}

	HashMap<String, Long> cooldownP = new HashMap<String, Long>();

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("pesa")) {
			Player player = (Player) sender;
			if (args.length == 0) {
				if (player.getItemInHand().getType().equals(Material.INK_SACK)) {
					if (player.getItemInHand().getDurability() == 2) {
						if (player.getHealth() > 10) {
							if (MySQL.getFatiga(player) < 40) {
								if (cooldownP.containsKey(player.getName())) {
									long diff = (System.currentTimeMillis() - cooldownP
											.get(sender.getName())) / 1000;
									if (diff < 20) {
										double random = Math.random() * 100;
										if (random < 5) {
											player.sendMessage(ChatColor.DARK_RED
													+ "¡Ouch!");
											player.sendMessage(ChatColor.RED
													+ "¡Más despacio! Te acabas de hacer daño, podría haber sido grave...");
											player.damage(1);
											Scoreboard.showScore(player);
											return true;
										}
										MySQL.addFatiga(player, 0.8);
										int show = (int) (20-diff);
										player.sendMessage(ChatColor.GOLD
												+ "Mejor espera unos "
												+ show
												+ " segundos más, si entrenas tan rápido solo te cansarás más y puedes lesionarte");
										Scoreboard.showScore(player);
										return true;
									} else {
										MySQL.addFatiga(player, 0.6);
										TrainingSQL.addFTS(player, 0.2);
										player.sendMessage(ChatColor.GREEN
												+ "¡Así se hace! Sigue así :)");
										cooldownP.put(player.getName(),
												System.currentTimeMillis());
										Scoreboard.showScore(player);
										return true;
									}
								} else {
									MySQL.addFatiga(player, 0.6);
									TrainingSQL.addFTS(player, 0.2);
									player.sendMessage(ChatColor.GREEN
											+ "¡Así se hace! Sigue así :)");
									cooldownP.put(player.getName(),
											System.currentTimeMillis());
									Scoreboard.showScore(player);
									return true;
								}

							} else {
								player.sendMessage(ChatColor.RED
										+ "Mejor descansa un poco, estás demasiado cansado para entrenar");
								return true;
							}
						} else {
							player.sendMessage(ChatColor.RED
									+ "Estás demasiado débil para entrenar ahora");
							return true;
						}
					} else {
						player.sendMessage(ChatColor.RED
								+ "Lo suyo sería tener una pesa para poder entrenar...");
						return true;
					}
				} else {
					player.sendMessage(ChatColor.RED
							+ "Lo suyo sería tener una pesa para poder entrenar...");
					return true;
				}

			}
		} else {
			return false;
		}

		return false;
	}
}
