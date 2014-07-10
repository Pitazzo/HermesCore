package es.programahermes.Health;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bukkit.entity.Player;

import es.programahermes.MySQL;

public class HealthSQL {

	public static void setFracturaTS(Player player, boolean state) {
		try {
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `fracturaTS`=? WHERE name=?");
			ps.setString(2, player.getName());
			ps.setBoolean(1, state);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setFracturaTI(Player player, boolean state) {
		try {
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `fracturaTI`=? WHERE name=?");
			ps.setString(2, player.getName());
			ps.setBoolean(1, state);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean FracturaTS(Player player) {
		try {
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT fracturaTS FROM `bukkit`.`user_data` WHERE name=? ");
			sql.setString(1, player.getName());
			ResultSet result = sql.executeQuery();
			result.next();
			boolean state = result.getBoolean("fracturaTS");
			sql.close();

			return state;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public static boolean FracturaTI(Player player) {
		try {
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT fracturaTI FROM `bukkit`.`user_data` WHERE name=? ");
			sql.setString(1, player.getName());
			ResultSet result = sql.executeQuery();
			result.next();
			boolean state = result.getBoolean("fracturaTI");
			sql.close();

			return state;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public void setSepticemia(Player player, boolean state) {
		try {
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `sepsis`=? WHERE name=?");
			ps.setString(2, player.getName());
			ps.setBoolean(1, state);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean Septicemia(Player player) {
		try {
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT sepsis FROM `bukkit`.`user_data` WHERE name=? ");
			sql.setString(1, player.getName());
			ResultSet result = sql.executeQuery();
			result.next();
			boolean state = result.getBoolean("sepsis");
			sql.close();

			return state;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
}
