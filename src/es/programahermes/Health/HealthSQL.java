package es.programahermes.Health;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import es.programahermes.MySQL;

public class HealthSQL {

	public static void setFracturaTS(String player, boolean state) {
		try {
			MySQL.openConnection();
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `fracturaTS`=? WHERE name=?");
			ps.setString(2, player);
			ps.setBoolean(1, state);
			ps.executeUpdate();
			ps.close();
			MySQL.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setFracturaTI(String player, boolean state) {
		try {
			MySQL.openConnection();
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `fracturaTI`=? WHERE name=?");
			ps.setString(2, player);
			ps.setBoolean(1, state);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean FracturaTS(String player) {
		try {
			MySQL.openConnection();
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT fracturaTS FROM `bukkit`.`user_data` WHERE name=? ");
			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			boolean state = result.getBoolean("fracturaTS");
			sql.close();
			MySQL.closeConnection();
			return state;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	
	
	
	
	public static boolean FracturaTI(String player) {
		try {
			MySQL.openConnection();
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT fracturaTI FROM `bukkit`.`user_data` WHERE name=? ");
			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			boolean state = result.getBoolean("fracturaTI");
			sql.close();
			MySQL.closeConnection();
			return state;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public static void setSepticemia(String player, boolean state) {
		try {
			MySQL.openConnection();
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `sepsis`=? WHERE name=?");
			ps.setString(2, player);
			ps.setBoolean(1, state);
			ps.executeUpdate();
			ps.close();
			MySQL.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean Septicemia(String player) {
		try {
			MySQL.openConnection();
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT sepsis FROM `bukkit`.`user_data` WHERE name=? ");
			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			boolean state = result.getBoolean("sepsis");
			sql.close();
			MySQL.closeConnection();
			return state;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public static synchronized double getVPoints(String player) {

		try {
			MySQL.openConnection();
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT VPoints FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			double VPoints = result.getDouble("VPoints");
			sql.close();
			result.close();
			MySQL.closeConnection();
			return VPoints;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static synchronized void addVPoints(String player, double VPoints) {

		try {
			MySQL.openConnection();
			PreparedStatement ps1 = MySQL.connection
					.prepareStatement("SELECT VPoints FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double VPoints2 = result1.getDouble("VPoints");
			PreparedStatement ps2 = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `VPoints`=? WHERE name=?");
			ps2.setString(2, player);
			if(VPoints2+VPoints<100){

				
				ps2.setDouble(1, VPoints + VPoints2);
				ps2.executeUpdate();
			}
				ps1.close();
				result1.close();
				ps2.close();
				MySQL.closeConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void removeVPoints(String player, double VPoints) {
		try {
			MySQL.openConnection();
			PreparedStatement ps1 = MySQL.connection
					.prepareStatement("SELECT VPoints FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double VPoints2 = result1.getDouble("VPoints");
	
				PreparedStatement ps2 = MySQL.connection
						.prepareStatement("UPDATE `bukkit`.`user_data` SET `VPoints`=? WHERE name=?");
				ps2.setString(2, player);
				ps2.setDouble(1, VPoints2 - VPoints);
				ps2.executeUpdate();
				ps1.close();
				result1.close();
				ps2.close();
				MySQL.closeConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setVPoints(String player, double newVPoints) {

		try {
			MySQL.openConnection();
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `VPoints`=? WHERE name=?");
			ps.setString(2, player);
			ps.setDouble(1, newVPoints);
			ps.executeUpdate();
			ps.close();
			MySQL.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setAnemia(String player, boolean state) {
		try {
			MySQL.openConnection();
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `anemia`=? WHERE name=?");
			ps.setString(2, player);
			ps.setBoolean(1, state);
			ps.executeUpdate();
			ps.close();
			MySQL.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean Anemia(String player) {
		try {
			MySQL.openConnection();
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT anemia FROM `bukkit`.`user_data` WHERE name=? ");
			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			boolean state = result.getBoolean("anemia");
			sql.close();
			MySQL.closeConnection();
			return state;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public static void setDiarrea(String player, boolean state) {
		try {
			MySQL.openConnection();
			PreparedStatement ps = MySQL.connection
					.prepareStatement("UPDATE `user_data` SET `diarrea`=? WHERE name=?");
			ps.setString(2, player);
			ps.setBoolean(1, state);
			ps.executeUpdate();
			ps.close();
			MySQL.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean Diarrea(String player) {
		try {
			MySQL.openConnection();
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT diarrea FROM `bukkit`.`user_data` WHERE name=? ");
			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			boolean state = result.getBoolean("diarrea");
			sql.close();
			MySQL.closeConnection();
			return state;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	
}
