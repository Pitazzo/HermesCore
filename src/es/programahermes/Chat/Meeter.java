package es.programahermes.Chat;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.Plugin;

import es.programahermes.Main;

public class Meeter implements CommandExecutor, Listener {

	public static HashMap<Player, String> players = new HashMap<Player, String>();
	public static String name = "";
	static int taskID1;

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("presentarse")) {
			if (sender instanceof Player) {
					if (!args[0].equalsIgnoreCase("confirmar")
							&& !args[0].equalsIgnoreCase("cancelar")) {
						
						for (int i = 0; i < args.length; i++) {
							String arg = args[i] + " ";
							name = name + arg;
						}
						
						sender.sendMessage(ChatColor.DARK_GREEN
								+ "Vas a presentarte como " + name);
						sender.sendMessage(ChatColor.DARK_GREEN
								+ "Recuerda que nadie te obliga a presentarte con"
								+ ChatColor.DARK_RED + " tu nombe real");
						sender.sendMessage(ChatColor.DARK_GREEN
								+ "Confirma con /presentarse confirmar o cancela con /presentarse cancelar");
						return true;
						
					} else if (args[0].equalsIgnoreCase("confirmar")) {
						if(name != null){
							players.put((Player) sender, name);
							sender.sendMessage(ChatColor.BLUE
									+ "Has confirmado tu nombre como " + name);
							sender.sendMessage(ChatColor.BLUE
									+ "Haz click en los próximos 10 segundos en el personaje al que quieras presentarte");
							timer(Main.plugin, (Player) sender);
						}else{
							sender.sendMessage(ChatColor.DARK_RED+"No hay nada que confirmar");
						}
						
						return true;
					} else if (args[0].equalsIgnoreCase("cancelar")) {
						if (players.containsKey(sender)) {
							sender.sendMessage(ChatColor.DARK_RED
									+ "Se cancela la presentación");
							players.remove(sender);
							name = null;
							return true;
						} else {
							sender.sendMessage(ChatColor.DARK_RED
									+ "No hay nada que cancelar");
							return true;
						}
					}

				}
			}
		
		return false;
	}

	public static void timer(final Plugin plugin, final Player player) {
		taskID1 = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,
				new Runnable() {
					@Override
					public void run() {
						if (players.containsKey(player)) {
							players.remove(player);
							name = null;
							player.sendMessage(ChatColor.DARK_RED
									+ "Tu presentación ha expirado");
						}
					}
				}, 20 * 10L);
	}

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		if (event.getRightClicked() instanceof Player) {
			Player target = (Player) event.getRightClicked();
			if (players.containsKey(player)) {
	if (IdentityChat.knowsPlayer(player, target)) {
					player.sendMessage(ChatColor.BLUE
							+ "¡Te has presentado como " + players.get(player)
							+ "!");
					target.sendMessage(ChatColor.BLUE + "¡"
							+ players.get(player) + " se ha presentado!");
					IdentityChat.meetPlayer(player, target, player.getName());
					players.remove(player);
					name = null;
				} else {
					player.sendMessage(ChatColor.DARK_RED
							+ "Ya te has presentado anteriormente a este jugador");
					players.remove(player);
				}
			}

		}
	}

}
