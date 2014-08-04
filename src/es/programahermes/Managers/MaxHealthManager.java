package es.programahermes.Managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import es.programahermes.MySQL;

public class MaxHealthManager {

	public static double getMaxHealth(String player) {
		MySQL.openConnection();
		try {
			PreparedStatement sql = MySQL.connection
					.prepareStatement("SELECT complexion FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			switch (result.getString("complexion")) {
			case "debil":
				return 16;
			case "normal":
				return 20;
			case "atletico":
				return 28;
			case "fuerte":
				return 34;
			default:
				return 20;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
