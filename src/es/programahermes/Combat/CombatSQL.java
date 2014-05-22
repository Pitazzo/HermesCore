package es.programahermes.Combat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import es.programahermes.MySQL;

public class CombatSQL {

	public static synchronized double getWP(Player player) {

		try {
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT WP FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player.getName());
			ResultSet result = sql.executeQuery();
			result.next();
			double WP = result.getDouble("WP");
			sql.close();
			result.close();

			return WP;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}



	public static synchronized void addWP(Player player, double newWP) {

		try {
			PreparedStatement ps1 = MySQL.connection
					.prepareStatement("SELECT WP FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player.getName());
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double previousWP = result1.getDouble("WP");

			PreparedStatement ps2 = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `WP`=? WHERE name=?");
			ps2.setString(2, player.getName());
			ps2.setDouble(1, newWP + previousWP);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void removeWP(Player player,
			double WPToRemove) {

		try {
			PreparedStatement ps1 = MySQL.connection
					.prepareStatement("SELECT WP FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player.getName());
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double previousWP = result1.getDouble("WP");

			PreparedStatement ps2 = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `WP`=? WHERE name=?");
			ps2.setString(2, player.getName());
			ps2.setDouble(1, previousWP - WPToRemove);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setWP(Player player, double newWP) {

		try {
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `WP`=? WHERE name=?");
			ps.setString(2, player.getName());
			ps.setDouble(1, newWP);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
