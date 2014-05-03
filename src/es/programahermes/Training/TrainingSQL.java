package es.programahermes.Training;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import es.programahermes.MySQL;

public class TrainingSQL {


	public static synchronized double getFTS(Player player) {

		try {
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT FTS FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player.getName());
			ResultSet result = sql.executeQuery();
			result.next();
			double FTS = result.getDouble("FTS");
			sql.close();
			result.close();

			return FTS;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}



	public static synchronized void addFTS(Player player, double newFTS) {

		try {
			PreparedStatement ps1 = MySQL.connection
					.prepareStatement("SELECT FTS FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player.getName());
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double previousFTS = result1.getDouble("FTS");

			PreparedStatement ps2 = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `FTS`=? WHERE name=?");
			ps2.setString(2, player.getName());
			ps2.setDouble(1, newFTS + previousFTS);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void removeFTS(Player player,
			double FTSToRemove) {

		try {
			PreparedStatement ps1 = MySQL.connection
					.prepareStatement("SELECT FTS FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player.getName());
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double previousFTS = result1.getDouble("FTS");

			PreparedStatement ps2 = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `FTS`=? WHERE name=?");
			ps2.setString(2, player.getName());
			ps2.setDouble(1, previousFTS - FTSToRemove);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setFTS(Player player, double newFTS) {

		try {
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `FTS`=? WHERE name=?");
			ps.setString(2, player.getName());
			ps.setDouble(1, newFTS);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static synchronized double getFTI(Player player) {

		try {
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT FTI FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player.getName());
			ResultSet result = sql.executeQuery();
			result.next();
			double FTI = result.getDouble("FTI");
			sql.close();
			result.close();

			return FTI;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}



	public static synchronized void addFTI(Player player, double newFTI) {

		try {
			PreparedStatement ps1 = MySQL.connection
					.prepareStatement("SELECT FTI FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player.getName());
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double previousFTI = result1.getDouble("FTI");

			PreparedStatement ps2 = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `FTI`=? WHERE name=?");
			ps2.setString(2, player.getName());
			ps2.setDouble(1, newFTI + previousFTI);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void removeFTI(Player player,
			double FTIToRemove) {

		try {
			PreparedStatement ps1 = MySQL.connection
					.prepareStatement("SELECT FTI FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player.getName());
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double previousFTI = result1.getDouble("FTI");

			PreparedStatement ps2 = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `FTI`=? WHERE name=?");
			ps2.setString(2, player.getName());
			ps2.setDouble(1, previousFTI - FTIToRemove);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setFTI(Player player, double newFTI) {

		try {
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `FTI`=? WHERE name=?");
			ps.setString(2, player.getName());
			ps.setDouble(1, newFTI);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
