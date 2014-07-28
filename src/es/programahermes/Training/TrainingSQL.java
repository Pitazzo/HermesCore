package es.programahermes.Training;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import es.programahermes.MySQL;
import es.programahermes.Health.HealthSQL;

public class TrainingSQL {

	public static synchronized double getFTS(String player) {

		try {
			MySQL.openConnection();
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT FTS FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			double FTS = result.getDouble("FTS");
			double finalFTS;
			if (HealthSQL.FracturaTS(player)) {
				finalFTS = FTS * 0.4;
			} else {
				finalFTS = FTS;
			}
			sql.close();
			result.close();
			MySQL.closeConnection();
			return finalFTS;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static synchronized void addFTS(String player, double newFTS) {

		try {
			MySQL.openConnection();
			PreparedStatement ps1 = MySQL.connection
					.prepareStatement("SELECT FTS FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double previousFTS = result1.getDouble("FTS");

			PreparedStatement ps2 = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `FTS`=? WHERE name=?");
			ps2.setString(2, player);
			ps2.setDouble(1, newFTS + previousFTS);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();
			MySQL.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void removeFTS(String player, double FTSToRemove) {

		try {
			MySQL.openConnection();
			PreparedStatement ps1 = MySQL.connection
					.prepareStatement("SELECT FTS FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double previousFTS = result1.getDouble("FTS");

			PreparedStatement ps2 = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `FTS`=? WHERE name=?");
			ps2.setString(2, player);
			ps2.setDouble(1, previousFTS - FTSToRemove);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();
			MySQL.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setFTS(String player, double newFTS) {

		try {
			MySQL.openConnection();
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `FTS`=? WHERE name=?");
			ps.setString(2, player);
			ps.setDouble(1, newFTS);
			ps.executeUpdate();
			ps.close();
			MySQL.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized double getFTI(String player) {

		try {
			MySQL.openConnection();
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT FTI FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			double FTI = result.getDouble("FTI");
			double finalFTS;
			if (HealthSQL.FracturaTI(player)) {
				finalFTS = FTI * 0.4;
			} else {
				finalFTS = FTI;
			}
			sql.close();
			result.close();
			MySQL.closeConnection();
			return finalFTS;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static synchronized void addFTI(String player, double newFTI) {

		try {
			MySQL.openConnection();
			PreparedStatement ps1 = MySQL.connection
					.prepareStatement("SELECT FTI FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double previousFTI = result1.getDouble("FTI");

			PreparedStatement ps2 = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `FTI`=? WHERE name=?");
			ps2.setString(2, player);
			ps2.setDouble(1, newFTI + previousFTI);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();
			MySQL.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void removeFTI(String player, double FTIToRemove) {

		try {
			MySQL.openConnection();
			PreparedStatement ps1 = MySQL.connection
					.prepareStatement("SELECT FTI FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double previousFTI = result1.getDouble("FTI");

			PreparedStatement ps2 = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `FTI`=? WHERE name=?");
			ps2.setString(2, player);
			ps2.setDouble(1, previousFTI - FTIToRemove);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();
			MySQL.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setFTI(String player, double newFTI) {

		try {
			MySQL.openConnection();
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `FTI`=? WHERE name=?");
			ps.setString(2, player);
			ps.setDouble(1, newFTI);
			ps.executeUpdate();
			ps.close();
			MySQL.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
