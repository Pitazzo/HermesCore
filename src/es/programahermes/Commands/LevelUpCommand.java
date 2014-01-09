package es.programahermes.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import es.programahermes.MySQL;

public class LevelUpCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("subirnivel")) {
			Player player = (Player) sender;
			double points = MySQL.getPoints(player);
			double pointsLeft = 1000 - points;
			String habilidad1 = MySQL.getHability(player);
			if (points >= 1000) {

				String habilidad = MySQL.getHability(player);
				if (habilidad.equals("Geologia")) {
					PermissionUser user = PermissionsEx.getUser(player);

					switch (MySQL.getLevel(player)) {
					case 1:
						user.addGroup("Geologia2");
						player.sendMessage(ChatColor.GREEN
								+ "¡Has subido de nivel tu habilidad principal "
								+ habilidad1.toLowerCase() + "!");
						MySQL.levelUp(player);
						MySQL.removePoints(player, 1000);

						break;
					case 2:
						user.addGroup("Geologia3");
						player.sendMessage(ChatColor.GREEN
								+ "¡Has subido de nivel tu habilidad principal "
								+ habilidad1.toLowerCase() + "!");
						MySQL.levelUp(player);
						MySQL.removePoints(player, 1000);

						break;
					case 3:
						user.addGroup("Geologia4");
						player.sendMessage(ChatColor.GREEN
								+ "¡Has subido de nivel tu habilidad principal "
								+ habilidad1.toLowerCase() + "!");
						MySQL.levelUp(player);
						MySQL.removePoints(player, 1000);

						break;
					case 4:
						user.addGroup("Geologia5");
						player.sendMessage(ChatColor.GREEN
								+ "¡Has subido de nivel tu habilidad principal "
								+ habilidad1.toLowerCase() + "!");
						MySQL.levelUp(player);
						MySQL.removePoints(player, 1000);

						break;
					case 5:
						player.sendMessage(ChatColor.BLUE
								+ "¡Ya has alcanzado el nivel máximo de tu habilidad!");
						break;
					default:
						player.sendMessage(ChatColor.RED
								+ "Bug en Hermes Core, reporta inmediatamente a la administración");
						break;
					}

					if (habilidad.equals("Biologia")) {
						switch (MySQL.getLevel(player)) {
						case 1:
							user.addGroup("Biologia2");
							player.sendMessage(ChatColor.GREEN
									+ "¡Has subido de nivel tu habilidad principal "
									+ habilidad1.toLowerCase() + "!");
							MySQL.levelUp(player);
							MySQL.removePoints(player, 1000);
							break;
						case 2:
							user.addGroup("Biologia3");
							player.sendMessage(ChatColor.GREEN
									+ "¡Has subido de nivel tu habilidad principal "
									+ habilidad1.toLowerCase() + "!");
							MySQL.levelUp(player);
							MySQL.removePoints(player, 1000);
							break;
						case 3:
							user.addGroup("Biologia4");
							player.sendMessage(ChatColor.GREEN
									+ "¡Has subido de nivel tu habilidad principal "
									+ habilidad1.toLowerCase() + "!");
							MySQL.levelUp(player);
							MySQL.removePoints(player, 1000);
							break;
						case 4:
							user.addGroup("Biologia5");
							player.sendMessage(ChatColor.GREEN
									+ "¡Has subido de nivel tu habilidad principal "
									+ habilidad1.toLowerCase() + "!");
							MySQL.levelUp(player);
							MySQL.removePoints(player, 1000);
							break;
						case 5:
							player.sendMessage(ChatColor.BLUE
									+ "¡Ya has alcanzado el nivel máximo de tu habilidad!");
							break;
						default:
							player.sendMessage(ChatColor.RED
									+ "Bug en Hermes Core, reporta inmediatamente a la administración");
							break;
						}

					}
					if (habilidad.equals("Quimica")) {
						switch (MySQL.getLevel(player)) {
						case 1:
							user.addGroup("Quimica2");
							player.sendMessage(ChatColor.GREEN
									+ "¡Has subido de nivel tu habilidad principal "
									+ habilidad1.toLowerCase() + "!");
							MySQL.levelUp(player);
							MySQL.removePoints(player, 1000);
							break;
						case 2:
							user.addGroup("Quimica3");
							player.sendMessage(ChatColor.GREEN
									+ "¡Has subido de nivel tu habilidad principal "
									+ habilidad1.toLowerCase() + "!");
							MySQL.levelUp(player);
							MySQL.removePoints(player, 1000);
							break;
						case 3:
							user.addGroup("Quimica4");
							player.sendMessage(ChatColor.GREEN
									+ "¡Has subido de nivel tu habilidad principal "
									+ habilidad1.toLowerCase() + "!");
							MySQL.levelUp(player);
							MySQL.removePoints(player, 1000);
							break;
						case 4:
							user.addGroup("Quimica5");
							player.sendMessage(ChatColor.GREEN
									+ "¡Has subido de nivel tu habilidad principal "
									+ habilidad1.toLowerCase() + "!");
							MySQL.levelUp(player);
							MySQL.removePoints(player, 1000);
							break;
						case 5:
							player.sendMessage(ChatColor.BLUE
									+ "¡Ya has alcanzado el nivel máximo de tu habilidad!");
							break;
						default:
							player.sendMessage(ChatColor.RED
									+ "Bug en Hermes Core, reporta inmediatamente a la administración");
							break;
						}

					}
					if (habilidad.equals("Estructural")) {
						switch (MySQL.getLevel(player)) {
						case 1:
							user.addGroup("Estructural2");
							player.sendMessage(ChatColor.GREEN
									+ "¡Has subido de nivel tu habilidad principal "
									+ habilidad1.toLowerCase() + "!");
							MySQL.levelUp(player);
							MySQL.removePoints(player, 1000);
							break;
						case 2:
							user.addGroup("Estructural3");
							player.sendMessage(ChatColor.GREEN
									+ "¡Has subido de nivel tu habilidad principal "
									+ habilidad1.toLowerCase() + "!");
							MySQL.levelUp(player);
							MySQL.removePoints(player, 1000);
							break;
						case 3:
							user.addGroup("Estructural4");
							player.sendMessage(ChatColor.GREEN
									+ "¡Has subido de nivel tu habilidad principal "
									+ habilidad1.toLowerCase() + "!");
							MySQL.levelUp(player);
							MySQL.removePoints(player, 1000);
							break;
						case 4:
							user.addGroup("Estructural5");
							player.sendMessage(ChatColor.GREEN
									+ "¡Has subido de nivel tu habilidad principal "
									+ habilidad1.toLowerCase() + "!");
							MySQL.levelUp(player);
							MySQL.removePoints(player, 1000);
							break;
						case 5:
							player.sendMessage(ChatColor.BLUE
									+ "¡Ya has alcanzado el nivel máximo de tu habilidad!");
							break;
						default:
							player.sendMessage(ChatColor.RED
									+ "Bug en Hermes Core, reporta inmediatamente a la administración");
							break;
						}

					}
					if (habilidad.equals("Tecnico")) {
						switch (MySQL.getLevel(player)) {
						case 1:
							user.addGroup("Tecnico");
							player.sendMessage(ChatColor.GREEN
									+ "¡Has subido de nivel tu habilidad principal "
									+ habilidad1.toLowerCase() + "!");
							MySQL.levelUp(player);
							MySQL.removePoints(player, 1000);
							break;
						case 2:
							user.addGroup("Tecnico3");
							player.sendMessage(ChatColor.GREEN
									+ "¡Has subido de nivel tu habilidad principal "
									+ habilidad1.toLowerCase() + "!");
							MySQL.levelUp(player);
							MySQL.removePoints(player, 1000);
							break;
						case 3:
							user.addGroup("Tecnico4");
							player.sendMessage(ChatColor.GREEN
									+ "¡Has subido de nivel tu habilidad principal "
									+ habilidad1.toLowerCase() + "!");
							MySQL.levelUp(player);
							MySQL.removePoints(player, 1000);
							break;
						case 4:
							user.addGroup("Tecnico5");
							player.sendMessage(ChatColor.GREEN
									+ "¡Has subido de nivel tu habilidad principal "
									+ habilidad1.toLowerCase() + "!");
							MySQL.levelUp(player);
							MySQL.removePoints(player, 1000);
							break;
						case 5:
							player.sendMessage(ChatColor.BLUE
									+ "¡Ya has alcanzado el nivel máximo de tu habilidad!");
							break;
						default:
							player.sendMessage(ChatColor.RED
									+ "Bug en Hermes Core, reporta inmediatamente a la administración");
							break;
						}

					}
					return true;
				} 
				}else {
					player.sendMessage(ChatColor.GREEN
							+ "No tienes suficientes puntos para subir de nivel, tienes "
							+ points + " puntos, luego te faltan " + pointsLeft
							+ " más.");
			}

			return false;
		}
		return false;

	}
}
