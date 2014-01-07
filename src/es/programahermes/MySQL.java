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
		openConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`user_data` WHERE name=?;");
			ps.setString(1, player.getName());
			ResultSet resultSet = ps.executeQuery();
			boolean containsPlayer = resultSet.next();

			ps.close();
			resultSet.close();
			closeConnection();
			return containsPlayer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	static synchronized void firstJoin(Player player) {
		openConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO `user_data`(`name`, `points`) VALUES (?,0)");
			ps.setString(1, player.getName());
			ps.executeUpdate();
			ps.close();
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static synchronized double getPoints(Player player) {
		openConnection();
		try {
			PreparedStatement sql = connection
					.prepareStatement("SELECT points FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player.getName());
			ResultSet result = sql.executeQuery();
			result.next();
			double points = result.getDouble("points");
			sql.close();
			result.close();
			closeConnection();
			return points;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static synchronized int getLevel(Player player) {
		openConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT nivel1 FROM `bukkit`.`user_data` WHERE name=?");
			ps.setString(1, player.getName());
			ResultSet result = ps.executeQuery();
			result.next();
			int nivel = result.getInt("nivel1");
			ps.close();
			closeConnection();
			return nivel;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static synchronized void addPoints(Player player, double newpoints) {
		openConnection();
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
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void removePoints(Player player,
			double pointsToRemove) {
		openConnection();
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
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setPoints(Player player, double newPoints) {
		openConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `points`=? WHERE name=?");
			ps.setString(2, player.getName());
			ps.setDouble(1, newPoints);
			ps.executeUpdate();
			ps.close();
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setHability(Player player, String hability) {
		openConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `habilidad1`=? WHERE name=?");
			ps.setString(2, player.getName());
			ps.setString(1, hability);
			ps.executeUpdate();
			ps.close();
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized String getHability(Player player) {
		openConnection();
		try {
			PreparedStatement sql = connection
					.prepareStatement("SELECT habilidad1 FROM `bukkit`.`user_data` WHERE name=? ");
			sql.setString(1, player.getName());
			ResultSet result = sql.executeQuery();
			result.next();
			String habilidad = result.getString("habilidad1");
			sql.close();
			closeConnection();
			return habilidad;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static synchronized void setLevel(Player player, int newLevel) {
		openConnection();

		try {

			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `nivel1`=? WHERE name=?");
			ps.setString(2, player.getName());
			ps.setInt(1, newLevel);
			ps.executeUpdate();
			ps.close();
			closeConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static synchronized void levelUp(Player player) {
		openConnection();
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
			closeConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void addEarnedPoints(Player player, String type,
			String material, double amount) {
		openConnection();
		try {

			int level = getLevel(player);

			switch (type) {
			case "break":
				PreparedStatement ps1 = connection
						.prepareStatement("SELECT points FROM `break_data` WHERE material=?");
				ps1.setString(1, material);
			
				ResultSet result1 = ps1.executeQuery();
				result1.next();
				double points = result1.getDouble("points");
				addPoints(player, points / level);
				ps1.close();
				result1.close();
				closeConnection();

			case "craft":
				PreparedStatement ps2 = connection
						.prepareStatement("SELECT points FROM `bukkit`.`craft` WHERE material=?");
				ps2.setString(1, material);
				ResultSet result2 = ps2.executeQuery();
				result2.next();
				double points2 = result2.getDouble("points");
				addPoints(player, (points2 / level) * amount);
				ps2.close();
				result2.close();
				closeConnection();
			case "placement":
				PreparedStatement ps3 = connection
						.prepareStatement("SELECT points FROM `bukkit`.`break_data` WHERE material=?");
				ps3.setString(1, material);
				ResultSet result3 = ps3.executeQuery();
				result3.next();
				double points3 = result3.getDouble("points");
				addPoints(player, points3 / level);

			case "special":

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
