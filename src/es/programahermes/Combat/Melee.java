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

import es.programahermes.Training.TrainingSQL;

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
				sender.sendMessage("1");
				return false;
				
			}
		}
		sender.sendMessage("2");
		return false;

	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		Player attacker = (Player) event.getDamager();
		Player victim = (Player) event.getEntity();
		if((attacker.getGameMode().equals(GameMode.SURVIVAL)&&(victim.getGameMode().equals(GameMode.SURVIVAL)))){
			if (attacker instanceof Player) {
				//entrenamiento
				if(isTraining.contains(attacker)){
					if(attacker.getItemInHand().getType().equals(Material.AIR)){
						event.setDamage(event.getDamage()/50);
						if(Math.random()<0.02){
							event.setDamage(0.75);
							//MySQL
							attacker.sendMessage(ChatColor.RED+"¡Contrólate un poco!¡No pegues tan fuerte!");
						}
						victim.setVelocity(victim.getLocation().getDirection().multiply(-0.25));
					}else{
						attacker.sendMessage(ChatColor.RED+"Solo puedes practicar la lucha cuerpo a cuerpo con las manos vacías");
						attacker.sendMessage(ChatColor.RED+"Desactiva el entrenamiento con /cuerpoacuerpo");
						event.setCancelled(true);
					}
					
				}
				
				//modifiers normales
				double fts = TrainingSQL.getFTS(attacker);
				double modifier = 0;
				if(fts > 50){
					modifier = fts/50;
				}else{
					modifier = (fts -50)/50;
				}
				
				event.setDamage(event.getDamage() + modifier);
				victim.sendMessage("Modifier: "+modifier);
				//por la espalda
				int x1 = attacker.getLocation().getDirection().getBlockX();
				int z1 = attacker.getLocation().getDirection().getBlockZ();
				int x2 = victim.getLocation().getDirection().getBlockX();
				int z2 = victim.getLocation().getDirection().getBlockZ();

				if ((x1 == x2) && (z1 == z2)) {
					victim.damage(18);
					attacker.sendMessage(ChatColor.GOLD
							+ "Digno de las fuerzas especiales...");
				}
		}
	
		}
	}
}
