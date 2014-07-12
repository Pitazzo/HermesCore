package es.programahermes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MySQL {

	public static Connection connection;

	public static synchronized void openConnection() {
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://"
							+ JavaPlugin.getPlugin(Main.class).getConfig()
									.getString("MySQL.Host")
							+ ":"
							+ JavaPlugin.getPlugin(Main.class).getConfig()
									.getString("MySQL.Port")
							+ "/"
							+ JavaPlugin.getPlugin(Main.class).getConfig()
									.getString("MySQL.DB"),
					JavaPlugin.getPlugin(Main.class).getConfig()
							.getString("MySQL.User"),
					JavaPlugin.getPlugin(Main.class).getConfig()
							.getString("MySQL.Password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void closeConnection() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized boolean dbContanisPlayer(Player player) {

		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`user_data` WHERE name=?;");
			ps.setString(1, player.getName());
			ResultSet resultSet = ps.executeQuery();
			boolean containsPlayer = resultSet.next();

			ps.close();
			resultSet.close();

			return containsPlayer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static synchronized double getPoints(Player player) {

		try {
			PreparedStatement sql = connection
					.prepareStatement("SELECT points FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player.getName());
			ResultSet result = sql.executeQuery();
			result.next();
			double points = result.getDouble("points");
			sql.close();
			result.close();

			return points;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static synchronized int getLevel(Player player) {

		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT nivel1 FROM `bukkit`.`user_data` WHERE name=?");
			ps.setString(1, player.getName());
			ResultSet result = ps.executeQuery();
			result.next();
			int nivel = result.getInt("nivel1");
			ps.close();

			return nivel;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static synchronized void addPoints(Player player, double newpoints) {

		try {
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT points FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player.getName());
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double previouspoints = result1.getDouble("points");

			PreparedStatement ps2 = connection
					.prepareStatement("UPDATE `user_data` SET `points`=? WHERE name=?");
			ps2.setString(2, player.getName());
			ps2.setDouble(1, newpoints + previouspoints);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void removePoints(Player player,
			double pointsToRemove) {

		try {
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT points FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player.getName());
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double previouspoints = result1.getDouble("points");

			PreparedStatement ps2 = connection
					.prepareStatement("UPDATE `user_data` SET `points`=? WHERE name=?");
			ps2.setString(2, player.getName());
			ps2.setDouble(1, previouspoints - pointsToRemove);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setPoints(Player player, double newPoints) {

		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `points`=? WHERE name=?");
			ps.setString(2, player.getName());
			ps.setDouble(1, newPoints);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setHability(Player player, String hability) {

		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `habilidad1`=? WHERE name=?");
			ps.setString(2, player.getName());
			ps.setString(1, hability);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized String getHability(Player player) {

		try {
			PreparedStatement sql = connection
					.prepareStatement("SELECT habilidad1 FROM `bukkit`.`user_data` WHERE name=? ");
			sql.setString(1, player.getName());
			ResultSet result = sql.executeQuery();
			result.next();
			String habilidad = result.getString("habilidad1");
			sql.close();

			return habilidad;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static synchronized void setLevel(Player player, int newLevel) {

		try {

			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `nivel1`=? WHERE name=?");
			ps.setString(2, player.getName());
			ps.setInt(1, newLevel);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static synchronized void levelUp(Player player) {

		try {
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT nivel1 FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player.getName());
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			int previousLevel = result1.getInt("nivel1");

			if (!(previousLevel + 1 <= 5)) {
				PreparedStatement ps2 = connection
						.prepareStatement("UPDATE `user_data` SET `nivel1`=? WHERE name=?");
				ps2.setString(2, player.getName());
				ps2.setInt(1, 1 + previousLevel);
				ps2.executeUpdate();
				ps1.close();
				result1.close();
				ps2.close();
			} else {
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void addEarnedPoints(Player player, String type,
			String material, double amount) {

		int level = getLevel(player);

		switch (type) {
		case "break":
			try {

				if (dbContanisBreak(material)) {
					PreparedStatement ps1 = connection
							.prepareStatement("SELECT points FROM `break_data` WHERE material=?");
					ps1.setString(1, material);
					ResultSet result1 = ps1.executeQuery();
					result1.next();
					double points = result1.getDouble("points");
					addPoints(player, (points * amount) / level);
					ps1.close();
					result1.close();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "entity":
			try {

				if (dbContanisEntity(material)) {
					PreparedStatement ps1 = connection
							.prepareStatement("SELECT points FROM `entity_data` WHERE entity=?");
					ps1.setString(1, material);

					ResultSet result1 = ps1.executeQuery();
					result1.next();
					double points = result1.getDouble("points");
					addPoints(player, points / level);
					ps1.close();
					result1.close();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "craft":
			try {
				if (dbContanisCraft(material)) {
					PreparedStatement ps1 = connection
							.prepareStatement("SELECT points FROM `craft_data` WHERE material=?");
					ps1.setString(1, material);
					ResultSet result1 = ps1.executeQuery();
					result1.next();
					double points = result1.getDouble("points");
					addPoints(player, (points / level) * amount);
					ps1.close();
					result1.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "smelt":
			try {
				if (dbContanisSmelt(material)) {
					PreparedStatement ps1 = connection
							.prepareStatement("SELECT points FROM `smelt` WHERE material=?");
					ps1.setString(1, material);
					ResultSet result1 = ps1.executeQuery();
					result1.next();
					double points = result1.getDouble("points");
					addPoints(player, (points / level) * amount);
					ps1.close();
					result1.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "interact":
			try {
				if (dbContanisInteract(material)) {
					PreparedStatement ps1 = connection
							.prepareStatement("SELECT points FROM `interact_data` WHERE material=?");
					ps1.setString(1, material);
					ResultSet result1 = ps1.executeQuery();
					result1.next();
					double points = result1.getDouble("points");
					addPoints(player, points / level);
					ps1.close();
					result1.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "special":
			try {
				if (dbContanisSpecial(material)) {
					PreparedStatement ps1 = connection
							.prepareStatement("SELECT points FROM `special_data` WHERE special=?");
					ps1.setString(1, material);
					ResultSet result1 = ps1.executeQuery();
					result1.next();
					double points = result1.getDouble("points");
					addPoints(player, points / level);
					ps1.close();
					result1.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "place":
			try {
				if (dbContanisPlace(material)) {
					PreparedStatement ps1 = connection
							.prepareStatement("SELECT points FROM `place_data` WHERE material=?");
					ps1.setString(1, material);
					ResultSet result1 = ps1.executeQuery();
					result1.next();
					double points = result1.getDouble("points");
					addPoints(player, points / level);
					ps1.close();
					result1.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

	public static synchronized boolean dbContanisBreak(String material) {
		try {

			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`break_data` WHERE material=?;");
			ps.setString(1, material);
			ResultSet resultSet = ps.executeQuery();
			boolean containsPlayer = resultSet.next();

			ps.close();
			resultSet.close();

			return containsPlayer;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static synchronized boolean dbContanisCraft(String material) {
		try {

			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`craft_data` WHERE material=?;");
			ps.setString(1, material);
			ResultSet resultSet = ps.executeQuery();
			boolean containsPlayer = resultSet.next();

			ps.close();
			resultSet.close();

			return containsPlayer;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static synchronized boolean dbContanisEntity(String entity) {
		try {

			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`entity_data` WHERE entity=?;");
			ps.setString(1, entity);
			ResultSet resultSet = ps.executeQuery();
			boolean containsPlayer = resultSet.next();

			ps.close();
			resultSet.close();

			return containsPlayer;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static synchronized boolean dbContanisInteract(String material) {
		try {

			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`interact_data` WHERE material=?;");
			ps.setString(1, material);
			ResultSet resultSet = ps.executeQuery();
			boolean containsPlayer = resultSet.next();

			ps.close();
			resultSet.close();

			return containsPlayer;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static synchronized boolean dbContanisSpecial(String special) {
		try {

			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`special_data` WHERE special=?;");
			ps.setString(1, special);
			ResultSet resultSet = ps.executeQuery();
			boolean containsPlayer = resultSet.next();

			ps.close();
			resultSet.close();

			return containsPlayer;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static synchronized boolean dbContanisPlace(String material) {
		try {

			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`place_data` WHERE material=?;");
			ps.setString(1, material);
			ResultSet resultSet = ps.executeQuery();
			boolean containsPlayer = resultSet.next();

			ps.close();
			resultSet.close();

			return containsPlayer;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static synchronized boolean dbContanisSmelt(String material) {
		try {

			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`smelt_data` WHERE material=?;");
			ps.setString(1, material);
			ResultSet resultSet = ps.executeQuery();
			boolean containsPlayer = resultSet.next();

			ps.close();
			resultSet.close();

			return containsPlayer;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static synchronized double getSed(Player player) {

		try {
			PreparedStatement sql = connection
					.prepareStatement("SELECT sed FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player.getName());
			ResultSet result = sql.executeQuery();
			result.next();
			double sed = result.getDouble("sed");
			sql.close();
			result.close();

			return sed;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static synchronized void addSed(Player player, double sed) {

		try {

			PreparedStatement ps1 = connection
					.prepareStatement("SELECT sed FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player.getName());
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double sed2 = result1.getDouble("sed");
			if (!(sed2 + sed > 100)) {

				PreparedStatement ps2 = connection
						.prepareStatement("UPDATE `user_data` SET `sed`=? WHERE name=?");
				ps2.setString(2, player.getName());
				ps2.setDouble(1, sed + sed2);
				ps2.executeUpdate();
				ps1.close();
				result1.close();
				ps2.close();
			} else {
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void removeSed(Player player, double sed) {

		try {
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT sed FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player.getName());
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double sed2 = result1.getDouble("sed");

			if (!(sed2 - sed < 0)) {
				PreparedStatement ps2 = connection
						.prepareStatement("UPDATE `bukkit`.`user_data` SET `sed`=? WHERE name=?");
				ps2.setString(2, player.getName());
				ps2.setDouble(1, sed2 - sed);
				ps2.executeUpdate();
				ps1.close();
				result1.close();
				ps2.close();
			} else {
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setSed(Player player, double newSed) {

		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `sed`=? WHERE name=?");
			ps.setString(2, player.getName());
			ps.setDouble(1, newSed);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized double getResidual(Player player) {

		try {
			PreparedStatement sql = connection
					.prepareStatement("SELECT residual FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player.getName());
			ResultSet result = sql.executeQuery();
			result.next();
			double sed = result.getDouble("residual");
			sql.close();
			result.close();

			return sed;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static synchronized void setResidual(Player player,
			double newResidual) {

		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `residual`=? WHERE name=?");
			ps.setString(2, player.getName());
			ps.setDouble(1, newResidual);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void addResidual(Player player, double residual) {

		try {

			PreparedStatement ps1 = connection
					.prepareStatement("SELECT residual FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player.getName());
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double residual2 = result1.getDouble("residual");
			if (!(residual2 + residual > 100)) {

				PreparedStatement ps2 = connection
						.prepareStatement("UPDATE `user_data` SET `residual`=? WHERE name=?");
				ps2.setString(2, player.getName());
				ps2.setDouble(1, residual + residual2);
				ps2.executeUpdate();
				ps1.close();
				result1.close();
				ps2.close();
			} else {
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized double getFatiga(Player player) {

		try {
			PreparedStatement sql = connection
					.prepareStatement("SELECT fatiga FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player.getName());
			ResultSet result = sql.executeQuery();
			result.next();
			double fatiga = result.getDouble("fatiga");
			sql.close();
			result.close();

			return fatiga;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static synchronized void addFatiga(Player player, double fatiga) {

		try {
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT fatiga FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player.getName());
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double fatiga2 = result1.getDouble("fatiga");
			if (!(fatiga + fatiga2 > 100)) {
				PreparedStatement ps2 = connection
						.prepareStatement("UPDATE `user_data` SET `fatiga`=? WHERE name=?");
				ps2.setString(2, player.getName());
				ps2.setDouble(1, fatiga + fatiga2);
				ps2.executeUpdate();
				ps1.close();
				result1.close();
				ps2.close();
			} else {
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void removeFatiga(Player player, double fatiga) {

		try {
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT fatiga FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player.getName());
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double fatiga2 = result1.getDouble("fatiga");
			if (!(fatiga - fatiga2 < 0)) {
				PreparedStatement ps2 = connection
						.prepareStatement("UPDATE `bukkit`.`user_data` SET `fatiga`=? WHERE name=?");
				ps2.setString(2, player.getName());
				ps2.setDouble(1, fatiga2 - fatiga);
				ps2.executeUpdate();
				ps1.close();
				result1.close();
				ps2.close();
			} else {
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setFatiga(Player player, double newfatiga) {

		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `fatiga`=? WHERE name=?");
			ps.setString(2, player.getName());
			ps.setDouble(1, newfatiga);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized double getOxygen(Player player) {

		try {
			PreparedStatement sql = connection
					.prepareStatement("SELECT oxygen FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player.getName());
			ResultSet result = sql.executeQuery();
			result.next();
			double oxygen = result.getDouble("oxygen");
			sql.close();
			result.close();

			return oxygen;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static synchronized void addOxygen(Player player, double oxygen) {

		try {

			PreparedStatement ps1 = connection
					.prepareStatement("SELECT oxygen FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player.getName());
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double oxygen2 = result1.getDouble("oxygen");

			PreparedStatement ps2 = connection
					.prepareStatement("UPDATE `user_data` SET `oxygen`=? WHERE name=?");
			ps2.setString(2, player.getName());
			ps2.setDouble(1, oxygen + oxygen2);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void removeOxygen(Player player, double oxygen) {

		try {
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT oxygen FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player.getName());
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double oxygen2 = result1.getDouble("oxygen");
			if (!(oxygen2 - oxygen < 0)) {
				PreparedStatement ps2 = connection
						.prepareStatement("UPDATE `bukkit`.`user_data` SET `oxygen`=? WHERE name=?");
				ps2.setString(2, player.getName());
				ps2.setDouble(1, oxygen2 - oxygen);
				ps2.executeUpdate();
				ps1.close();
				result1.close();
				ps2.close();
			} else {
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setOxygen(Player player, double newOxygen) {

		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `oxygen`=? WHERE name=?");
			ps.setString(2, player.getName());
			ps.setDouble(1, newOxygen);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized Player[] getNames() {

		try {
			PreparedStatement sql = connection
					.prepareStatement("SELECT name FROM `bukkit`.`user_data`;");

			ResultSet result = sql.executeQuery();
			ArrayList<Player> list = new ArrayList<Player>();
			while (result.next()) {
				list.add(Bukkit.getPlayer(result.getString("name")));
			}

			Player[] names = new Player[list.size()];
			names = list.toArray(names);
			sql.close();
			result.close();
			return names;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
}
