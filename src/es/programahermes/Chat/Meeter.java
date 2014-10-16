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

	public static HashMap<Player, String> names = new HashMap<Player, String>();
	public static HashMap<Player, String> players = new HashMap<Player, String>();
	static int taskID1;

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("presentarse")) {
			if (sender instanceof Player) {
				if (!args[0].equalsIgnoreCase("confirmar")
						&& !args[0].equalsIgnoreCase("cancelar")) {
					names.put((Player) sender, "");
					for (int i = 0; i < args.length; i++) {

						String arg = args[i] + " ";
						if (arg != null) {
							names.put((Player) sender, names.get(sender) + arg);
						}

					}

					sender.sendMessage(ChatColor.DARK_GREEN
							+ "Vas a presentarte como " + ChatColor.DARK_RED
							+ names.get(sender));
					sender.sendMessage(ChatColor.DARK_GREEN
							+ "Recuerda que nadie te obliga a presentarte con"
							+ ChatColor.DARK_RED + " tu nombe real");
					sender.sendMessage(ChatColor.DARK_GREEN + "Confirma con "
							+ ChatColor.DARK_BLUE + "/presentarse confirmar "
							+ ChatColor.DARK_GREEN + "o cancela con "
							+ ChatColor.DARK_BLUE + "/presentarse cancelar");
					return true;

				} else if (args[0].equalsIgnoreCase("confirmar")) {
					if (names.get(sender) != null) {
						players.put((Player) sender, names.get(sender));
						sender.sendMessage(ChatColor.BLUE
								+ "Has confirmado tu nombre como " + names.get(sender));
						sender.sendMessage(ChatColor.BLUE
								+ "Haz click en los próximos 10 segundos en el personaje al que quieras presentarte");
						timer(Main.plugin, (Player) sender);
					} else {
						sender.sendMessage(ChatColor.DARK_RED
								+ "No hay nada que confirmar");
					}

					return true;
				} else if (args[0].equalsIgnoreCase("cancelar")) {
					if (players.containsKey(sender)) {
						sender.sendMessage(ChatColor.DARK_RED
								+ "Se cancela la presentación");
						players.remove(sender);
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
				if (!IdentityChat.knowsPlayer(player, target)) {
					player.sendMessage(ChatColor.BLUE
							+ "¡Te has presentado como " + players.get(player)
							+ "!");
					target.sendMessage(ChatColor.BLUE + "¡"
							+ players.get(player) + " se ha presentado!");
					IdentityChat.meetPlayer(player, target, players.get(player));
					players.remove(player);

				} else {
					player.sendMessage(ChatColor.DARK_RED
							+ "Ya te has presentado anteriormente a este jugador");
					players.remove(player);
				}

			}

		}
	}

}
