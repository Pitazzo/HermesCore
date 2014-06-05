package es.programahermes.Combat;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import es.programahermes.MySQL;
import es.programahermes.Training.TrainingSQL;
import es.programahermes.Utilidades.ModiferConverter;

public class Melee implements Listener, CommandExecutor {

	public static ArrayList<String> isTraining = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("cuerpoacuerpo")) {

			if (sender instanceof Player) {
				if (args.length == 0) {
					if (isTraining.contains(sender.getName())) {
						isTraining.remove(sender.getName());
						sender.sendMessage(ChatColor.RED
								+ "Ya no estás entrenando tus habilidades de cuerpo a cuerpo");
						return true;
					} else {
						isTraining.add(sender.getName());
						sender.sendMessage(ChatColor.GREEN
								+ "Estás entrenando tus habilidades de cuerpo a cuerpo");
						return true;
					}
				}

			} else {
				return false;

			}
		}
		return false;

	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player && event.getEntity() instanceof Player){
		 Player attacker = (Player) event.getDamager();
		Player victim = (Player) event.getEntity();
		if ((attacker.getGameMode().equals(GameMode.SURVIVAL) && (victim
				.getGameMode().equals(GameMode.SURVIVAL)))) {
			if (attacker instanceof Player) {
				// entrenamiento
				if (isTraining.contains(attacker.getName())) {

					if (attacker.getItemInHand().getType().equals(Material.AIR)) {
						event.setDamage(0);
						victim.setVelocity(victim.getLocation().getDirection()
								.multiply(-0.5));
						TrainingSQL.addFTS(attacker, 0.03);
						MySQL.addFatiga(attacker, 0.2 * ModiferConverter
								.SacalaReverse(TrainingSQL.getFTS(attacker)));
						MySQL.addFatiga(victim, 0.3 * ModiferConverter
								.SacalaReverse(TrainingSQL.getFTS(victim)));
						CombatSQL.addWP(attacker, 0.01);
						if (Math.random() < 0.01) {
							event.setDamage(0.5);
							attacker.sendMessage(ChatColor.RED
									+ "¡Contrólate un poco!¡No pegues tan fuerte!");
						}

					} else {
						attacker.sendMessage(ChatColor.RED
								+ "Solo puedes practicar la lucha cuerpo a cuerpo con las manos vacías");
						attacker.sendMessage(ChatColor.RED
								+ "Desactiva el entrenamiento con /cuerpoacuerpo");
						event.setCancelled(true);
					}

				}

				// modifiers normales
				double fts = TrainingSQL.getFTS(attacker);
				double modifier = ModiferConverter.Scala(fts);
				event.setDamage(event.getDamage() * modifier);
				TrainingSQL.addFTS(attacker, 0.03);
				CombatSQL.addWP(attacker, 0.01);
				MySQL.addFatiga(attacker, 0.2 * ModiferConverter
						.SacalaReverse(TrainingSQL.getFTS(attacker)));
				MySQL.addFatiga(victim, 0.3 * ModiferConverter
						.SacalaReverse(TrainingSQL.getFTS(victim)));

				// por la espalda
				int x1 = attacker.getLocation().getDirection().getBlockX();
				int z1 = attacker.getLocation().getDirection().getBlockZ();
				int x2 = victim.getLocation().getDirection().getBlockX();
				int z2 = victim.getLocation().getDirection().getBlockZ();

				if ((x1 == x2) && (z1 == z2)) {
					if (attacker.isSneaking()) {
						if (isTraining.contains(victim.getName())) {
							victim.sendMessage(ChatColor.GOLD
									+ "Después de ese golpe de tu contrincante, ya estarías muerto");
							attacker.sendMessage(ChatColor.GOLD
									+ "¡Buen golpe! Digno de las fuerzas especiales...");
							CombatSQL.addWP(attacker, 4);
						} else {
							victim.damage(18);
							attacker.sendMessage(ChatColor.GOLD
									+ "Digno de las fuerzas especiales...");
							CombatSQL.addWP(attacker, 6);
						}

					} else {
						return;
					}
				}
				}
			}

		}
	}
}
