package es.programahermes.SoporteVital;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import es.programahermes.MySQL;
import es.programahermes.PHDS.DeathSQL;
import es.programahermes.Utilidades.Scoreboard;

public class Residual implements CommandExecutor{

	public static void residualUpdate(Plugin plugin) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player player : Bukkit.getOnlinePlayers()) {
					if(player.getGameMode().equals(GameMode.SURVIVAL)){
						if(!DeathSQL.isInLimbo(player.getName())){
							if(!(MySQL.getResidual(player.getName())>= 100)){
								MySQL.addResidual(player.getName(), 1.85);
							}
							
							Scoreboard.showScore(player);
							if (MySQL.getResidual(player.getName()) >= 80) {
								player.sendMessage(ChatColor.GREEN
										+ "[Soporte Vital]"
										+ ChatColor.RED
										+ "Deberías pensar en evacuar tus residuos...");
								player.playSound(player.getLocation(), Sound.BAT_DEATH,
										0.5F, 0.0F);
								if (MySQL.getResidual(player.getName()) >= 100) {
									player.addPotionEffect(new PotionEffect(
											PotionEffectType.CONFUSION, 2, 0), true);
									player.damage(2);
								}
							}
						}
						
					}

				}

			}
		}, 100L, 20 *60*5 );
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player =(Player)sender;
		if (cmd.getName().equalsIgnoreCase("evacuar")) {
			if(player instanceof Player){
				if(player.getInventory().getLeggings() == null){
					MySQL.setResidual(player.getName(), 0);
					ItemStack caca = new ItemStack(Material.INK_SACK, 1);
					caca.setDurability((short) 8);
					player.getWorld().dropItemNaturally(player.getLocation(), caca);
					player.playSound(player.getLocation(), Sound.DIG_SNOW, 0.5F, 0.0F);
					player.sendMessage(ChatColor.GREEN
									+ "[Soporte Vital]"
									+ ChatColor.RED
									+ "Has evacuado");
					return true;
				}else{
					player.sendMessage(ChatColor.GREEN
							+ "[Soporte Vital]"
							+ ChatColor.RED
							+ "No puedes evacuar con un traje presurizado puesto...");
					return true;
				}
				
			}
		}
		return false;
}
}