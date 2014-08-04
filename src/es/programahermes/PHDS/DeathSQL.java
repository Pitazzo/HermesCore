package es.programahermes.PHDS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import es.programahermes.MySQL;

public class DeathSQL {

	public static boolean isInLimbo(String player) {
		try {
			MySQL.openConnection();
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT isInLimbo FROM `bukkit`.`user_data` WHERE name=? ");
			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			boolean state = result.getBoolean("isInLimbo");
			sql.close();
			MySQL.closeConnection();
			return state;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public static synchronized int getTimeLeft(String player) {

		try {
			MySQL.openConnection();
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT timeLeft FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			int timeLeft = result.getInt("timeLeft");
			sql.close();
			result.close();
			MySQL.closeConnection();
			return timeLeft;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static synchronized void addTimeLeft(String player, int time) {

		try {
			MySQL.openConnection();
			PreparedStatement ps1 = MySQL.connection
					.prepareStatement("SELECT timeLeft FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			int timeLeft = result1.getInt("timeLeft");
			PreparedStatement ps2 = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `timeLeft`=? WHERE name=?");
			ps2.setString(2, player);

			ps2.setInt(1, timeLeft + time);
			ps2.executeUpdate();

			ps1.close();
			result1.close();
			ps2.close();
			MySQL.closeConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void removeTimeLeft(String player, int time) {
		try {
			MySQL.openConnection();
			PreparedStatement ps1 = MySQL.connection
					.prepareStatement("SELECT timeLeft FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			int timeLeft = result1.getInt("timeLeft");

			PreparedStatement ps2 = MySQL.connection
					.prepareStatement("UPDATE `bukkit`.`user_data` SET `timeLeft`=? WHERE name=?");
			ps2.setString(2, player);
			ps2.setInt(1, timeLeft - time);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();
			MySQL.closeConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setTimeLeft(String player, int time) {

		try {
			MySQL.openConnection();
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `timeLeft`=? WHERE name=?");
			ps.setString(2, player);
			ps.setInt(1, time);
			ps.executeUpdate();
			ps.close();
			MySQL.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setDeathLoc(String player, Location loc) {

		try {
			MySQL.openConnection();
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `DeathLoc`=? WHERE name=?");
			String sloc = loc.getWorld().getName() + "/" + loc.getX() + "/"
					+ loc.getY() + "/" + loc.getZ();
			ps.setString(2, player);
			ps.setString(1, sloc);
			ps.executeUpdate();
			ps.close();
			MySQL.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized Location getDeathLoc(String player) {

		try {
			MySQL.openConnection();
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT DeathLoc FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			String DeathLoc = result.getString("DeathLoc");
			String[] parts = DeathLoc.split("/");
			Location loc = null;
			try {
				loc = new Location(Bukkit.getServer().getWorld(parts[0]),
						Double.valueOf(parts[1]), Double.valueOf(parts[2]),
						Double.valueOf(parts[3]));
			} catch (Exception e) {
				e.printStackTrace();
			}

			sql.close();
			result.close();
			MySQL.closeConnection();
			return loc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setInLimbo(String player, boolean state) {
		try {
			MySQL.openConnection();
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `isInLimbo`=? WHERE name=?");
			ps.setString(2, player);
			ps.setBoolean(1, state);
			ps.executeUpdate();
			ps.close();
			MySQL.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setInPost(String player, boolean state) {
		try {
			MySQL.openConnection();
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `isInPost`=? WHERE name=?");
			ps.setString(2, player);
			ps.setBoolean(1, state);
			ps.executeUpdate();
			ps.close();
			MySQL.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean isPostDesmayado(String player) {
		try {
			MySQL.openConnection();
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT isPostDesmayado FROM `bukkit`.`user_data` WHERE name=? ");
			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			boolean state = result.getBoolean("isPostDesmayado");
			sql.close();
			MySQL.closeConnection();
			return state;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public static synchronized int getTimePostLeft(String player) {

		try {
			MySQL.openConnection();
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT timePostLeft FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			int timeLeft = result.getInt("timePostLeft");
			sql.close();
			result.close();
			MySQL.closeConnection();
			return timeLeft;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static synchronized void addTimePostLeft(String player, int time) {

		try {
			MySQL.openConnection();
			PreparedStatement ps1 = MySQL.connection
					.prepareStatement("SELECT timePostLeft FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			int timeLeft = result1.getInt("timePostLeft");
			PreparedStatement ps2 = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `timePostLeft`=? WHERE name=?");
			ps2.setString(2, player);

			ps2.setInt(1, timeLeft + time);
			ps2.executeUpdate();

			ps1.close();
			result1.close();
			ps2.close();
			MySQL.closeConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void removeTimePostLeft(String player, int time) {
		try {
			MySQL.openConnection();
			PreparedStatement ps1 = MySQL.connection
					.prepareStatement("SELECT timePostLeft FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			int timeLeft = result1.getInt("timePostLeft");

			PreparedStatement ps2 = MySQL.connection
					.prepareStatement("UPDATE `bukkit`.`user_data` SET `timePostLeft`=? WHERE name=?");
			ps2.setString(2, player);
			ps2.setInt(1, timeLeft - time);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();
			MySQL.closeConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setTimePostLeft(String player, int time) {

		try {
			MySQL.openConnection();
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `timePostLeft`=? WHERE name=?");
			ps.setString(2, player);
			ps.setInt(1, time);
			ps.executeUpdate();
			ps.close();
			MySQL.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
