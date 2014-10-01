package es.programahermes.Skins;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkinReloadCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("recargarskin")) {
			if (args.length == 0) {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					SkinManager.setSkin(player);
					return true;
				}
			} else if (args.length == 1) {
				if (sender.isOp()) {
					Player player = null;

					try {
						player = Bukkit.getPlayer(args[0]);
					} catch (Exception e){}

					if (player != null) {
						SkinManager.setSkin(player);
						System.out.println("[ADM DE SKINS] La skin de "
								+ args[0] + " ha sido actualizada");
						player = null;
						return true;
					} else {
						System.out.println("[ADM DE SKINS] El jugador "
								+ args[0] + " no se encuetra online");
						player = null;
						return true;
					}
					
				}
			}

		}
		return false;
	}

}
