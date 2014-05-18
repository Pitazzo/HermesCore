package es.programahermes.Utilidades;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.inventory.ItemStack;

import es.programahermes.MySQL;

public class Miscelaneo implements Listener, CommandExecutor {

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		MySQL.removePoints(player, 40);
	}

	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		if (!MySQL.dbContanisPlayer(player)) {
			event.disallow(
					Result.KICK_OTHER,
					ChatColor.GREEN
							+ "¡Acceso denegado, no cuentas con un perfil de jugador! Si crees que es un error contacta con el staff!");
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("emergency")) {
			if(sender instanceof Player){
				if(sender.isOp()){
					Player player = (Player) sender;
					MySQL.setFatiga(player, 0);
					MySQL.setOxygen(player, 700);
					MySQL.setResidual(player, 0);
					MySQL.setSed(player, 100);
					player.setHealth(20);
					player.setFoodLevel(20);
					player.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));
					player.getInventory().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE, 1));
					player.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS, 1));
					player.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS, 1));
					player.sendMessage(ChatColor.GREEN+"Modo de emergencia activado. Gracias por confiar en industrias ACME.");
					return true;
					
				}else{
					sender.sendMessage(ChatColor.RED+"Permisos insuficientes");
					return false;
				}
			}else{
				return false;
			}
		}
		return false;
	}

}
