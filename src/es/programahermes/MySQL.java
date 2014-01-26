package es.programahermes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

public class MySQL {
	public static Connection connection;

	public static synchronized void openConnection() {
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bukkit", "root", "");
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

			PreparedStatement ps2 = connection
					.prepareStatement("UPDATE `user_data` SET `nivel1`=? WHERE name=?");
			ps2.setString(2, player.getName());
			ps2.setInt(1, 1 + previousLevel);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();

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
					addPoints(player, points / level);
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
	
	
}
