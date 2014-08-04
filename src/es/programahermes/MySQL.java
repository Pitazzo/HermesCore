package es.programahermes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

	public static Connection connection;

	private static String host = Main.plugin.getConfig().getString("MySQL.Host");
	private static String port = Main.plugin.getConfig().getString("MySQL.Port");
	private static String db = Main.plugin.getConfig().getString("MySQL.DB");
	private static String user = Main.plugin.getConfig().getString("MySQL.User");
	private static String password = Main.plugin.getConfig().getString(
			"MySQL.Password");

	public static synchronized void openConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + host
					+ ":" + port + "/" + db, user, password);
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

	public static synchronized boolean dbContanisPlayer(String player) {

		try {
			openConnection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`user_data` WHERE name=?;");
			ps.setString(1, player);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				ps.close();
				resultSet.close();
				closeConnection();
				return true;
			} else {
				ps.close();
				resultSet.close();
				closeConnection();
				return false;
			}

		} catch (Exception e) {
			System.out
					.println("Error al conectar con la base de datos. Revisar credenciales.");
			e.printStackTrace();
		}
		return false;
	}

	public static synchronized double getPoints(String player) {

		try {
			openConnection();
			PreparedStatement sql = connection
					.prepareStatement("SELECT points FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player);
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

	public static synchronized int getLevel(String player) {

		try {
			openConnection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT nivel1 FROM `bukkit`.`user_data` WHERE name=?");
			ps.setString(1, player);
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

	public static synchronized void addPoints(String player, double newpoints) {

		try {
			openConnection();
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT points FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double previouspoints = result1.getDouble("points");

			PreparedStatement ps2 = connection
					.prepareStatement("UPDATE `user_data` SET `points`=? WHERE name=?");
			ps2.setString(2, player);
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

	public static synchronized void removePoints(String player,
			double pointsToRemove) {

		try {
			openConnection();
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT points FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double previouspoints = result1.getDouble("points");

			PreparedStatement ps2 = connection
					.prepareStatement("UPDATE `user_data` SET `points`=? WHERE name=?");
			ps2.setString(2, player);
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

	public static synchronized void setPoints(String player, double newPoints) {

		try {
			openConnection();
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `points`=? WHERE name=?");
			ps.setString(2, player);
			ps.setDouble(1, newPoints);
			ps.executeUpdate();
			ps.close();
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setHability(String player, String hability) {

		try {
			openConnection();
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `habilidad1`=? WHERE name=?");
			ps.setString(2, player);
			ps.setString(1, hability);
			ps.executeUpdate();
			ps.close();
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized String getHability(String player) {

		try {
			openConnection();
			PreparedStatement sql = connection
					.prepareStatement("SELECT habilidad1 FROM `bukkit`.`user_data` WHERE name=? ");
			sql.setString(1, player);
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

	public static synchronized void setLevel(String player, int newLevel) {

		try {
			openConnection();
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `nivel1`=? WHERE name=?");
			ps.setString(2, player);
			ps.setInt(1, newLevel);
			ps.executeUpdate();
			ps.close();
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static synchronized void levelUp(String player) {

		try {
			openConnection();
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT nivel1 FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			int previousLevel = result1.getInt("nivel1");
			PreparedStatement ps2 = connection
					.prepareStatement("UPDATE `user_data` SET `nivel1`=? WHERE name=?");
			ps2.setString(2, player);
			if (previousLevel + 1 <= 5) {
				ps2.setInt(1, 1 + previousLevel);
				ps2.executeUpdate();

			}
			ps1.close();
			result1.close();
			ps2.close();
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void addEarnedPoints(String player, String type,
			String material, double amount) {

		int level = getLevel(player);

		switch (type) {
		case "break":
			try {
				openConnection();
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
					closeConnection();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "entity":
			try {
				openConnection();
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
					closeConnection();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "craft":
			try {
				openConnection();
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
					closeConnection();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "smelt":
			try {
				openConnection();
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
					closeConnection();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "interact":
			try {
				openConnection();
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
					closeConnection();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "special":
			try {
				openConnection();
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
					closeConnection();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "place":
			try {
				openConnection();
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
					closeConnection();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

	public static synchronized boolean dbContanisBreak(String material) {
		try {
			openConnection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`break_data` WHERE material=?;");
			ps.setString(1, material);
			ResultSet resultSet = ps.executeQuery();
			boolean containsString = resultSet.next();

			ps.close();
			resultSet.close();
			closeConnection();
			return containsString;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static synchronized boolean dbContanisCraft(String material) {
		try {
			openConnection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`craft_data` WHERE material=?;");
			ps.setString(1, material);
			ResultSet resultSet = ps.executeQuery();
			boolean containsString = resultSet.next();

			ps.close();
			resultSet.close();
			closeConnection();
			return containsString;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static synchronized boolean dbContanisEntity(String entity) {
		try {
			openConnection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`entity_data` WHERE entity=?;");
			ps.setString(1, entity);
			ResultSet resultSet = ps.executeQuery();
			boolean containsString = resultSet.next();

			ps.close();
			resultSet.close();
			closeConnection();
			return containsString;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static synchronized boolean dbContanisInteract(String material) {
		try {
			openConnection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`interact_data` WHERE material=?;");
			ps.setString(1, material);
			ResultSet resultSet = ps.executeQuery();
			boolean containsString = resultSet.next();

			ps.close();
			resultSet.close();
			closeConnection();
			return containsString;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static synchronized boolean dbContanisSpecial(String special) {
		try {
			openConnection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`special_data` WHERE special=?;");
			ps.setString(1, special);
			ResultSet resultSet = ps.executeQuery();
			boolean containsString = resultSet.next();

			ps.close();
			resultSet.close();
			closeConnection();
			return containsString;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static synchronized boolean dbContanisPlace(String material) {
		try {
			openConnection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`place_data` WHERE material=?;");
			ps.setString(1, material);
			ResultSet resultSet = ps.executeQuery();
			boolean containsPlace = resultSet.next();

			ps.close();
			resultSet.close();
			closeConnection();
			return containsPlace;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static synchronized boolean dbContanisSmelt(String material) {
		try {
			openConnection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM `bukkit`.`smelt_data` WHERE material=?;");
			ps.setString(1, material);
			ResultSet resultSet = ps.executeQuery();
			boolean containsString = resultSet.next();

			ps.close();
			resultSet.close();
			closeConnection();
			return containsString;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static synchronized double getSed(String player) {

		try {
			openConnection();
			PreparedStatement sql = connection
					.prepareStatement("SELECT sed FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			double sed = result.getDouble("sed");
			sql.close();
			result.close();
			closeConnection();
			return sed;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static synchronized void addSed(String player, double sed) {

		try {
			openConnection();
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT sed FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double sed2 = result1.getDouble("sed");
			PreparedStatement ps2 = connection
					.prepareStatement("UPDATE `user_data` SET `sed`=? WHERE name=?");
			ps2.setString(2, player);
			if (sed2 + sed < 100) {

				ps2.setDouble(1, sed + sed2);
				ps2.executeUpdate();

			}
			ps1.close();
			result1.close();
			ps2.close();
			closeConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void removeSed(String player, double sed) {

		try {
			openConnection();
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT sed FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double sed2 = result1.getDouble("sed");
			PreparedStatement ps2 = connection
					.prepareStatement("UPDATE `bukkit`.`user_data` SET `sed`=? WHERE name=?");
			ps2.setString(2, player);
			if (sed2 - sed > 0) {

				ps2.setDouble(1, sed2 - sed);
				ps2.executeUpdate();

			}
			ps1.close();
			result1.close();
			ps2.close();
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setSed(String player, double newSed) {

		try {
			openConnection();
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `sed`=? WHERE name=?");
			ps.setString(2, player);
			ps.setDouble(1, newSed);
			ps.executeUpdate();
			ps.close();
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized double getResidual(String player) {

		try {
			openConnection();
			PreparedStatement sql = connection
					.prepareStatement("SELECT residual FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			double sed = result.getDouble("residual");
			sql.close();
			result.close();
			closeConnection();
			return sed;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static synchronized void setResidual(String player,
			double newResidual) {

		try {
			openConnection();
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `residual`=? WHERE name=?");
			ps.setString(2, player);
			ps.setDouble(1, newResidual);
			ps.executeUpdate();
			ps.close();
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void addResidual(String player, double residual) {

		try {
			openConnection();
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT residual FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double residual2 = result1.getDouble("residual");
			PreparedStatement ps2 = connection
					.prepareStatement("UPDATE `user_data` SET `residual`=? WHERE name=?");
			ps2.setString(2, player);
			if (residual2 + residual < 100) {

				ps2.setDouble(1, residual + residual2);
				ps2.executeUpdate();

			}
			ps1.close();
			result1.close();
			ps2.close();
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized double getFatiga(String player) {

		try {
			openConnection();
			PreparedStatement sql = connection
					.prepareStatement("SELECT fatiga FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			double fatiga = result.getDouble("fatiga");
			sql.close();
			result.close();
			closeConnection();
			return fatiga;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static synchronized void addFatiga(String player, double fatiga) {

		try {
			openConnection();
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT fatiga FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double fatiga2 = result1.getDouble("fatiga");
			PreparedStatement ps2 = connection
					.prepareStatement("UPDATE `user_data` SET `fatiga`=? WHERE name=?");
			ps2.setString(2, player);
			if (fatiga + fatiga2 < 100) {

				ps2.setDouble(1, fatiga + fatiga2);
				ps2.executeUpdate();

			}
			ps1.close();
			result1.close();
			ps2.close();
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void removeFatiga(String player, double fatiga) {
		try {
			openConnection();
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT fatiga FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();

			double fatiga2 = result1.getDouble("fatiga");

			PreparedStatement ps2 = connection
					.prepareStatement("UPDATE `bukkit`.`user_data` SET `fatiga`=? WHERE name=?");
			ps2.setString(2, player);
			if (fatiga2 - fatiga > 0) {
				ps2.setDouble(1, fatiga2 - fatiga);
				ps2.executeUpdate();
			}

			ps1.close();
			result1.close();
			ps2.close();
			closeConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setFatiga(String player, double newfatiga) {

		try {
			openConnection();
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `fatiga`=? WHERE name=?");
			ps.setString(2, player);
			ps.setDouble(1, newfatiga);
			ps.executeUpdate();
			ps.close();
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized double getOxygen(String player) {

		try {
			openConnection();
			PreparedStatement sql = connection
					.prepareStatement("SELECT oxygen FROM `bukkit`.`user_data` WHERE name=?;");

			sql.setString(1, player);
			ResultSet result = sql.executeQuery();
			result.next();
			double oxygen = result.getDouble("oxygen");
			sql.close();
			result.close();
			closeConnection();
			return oxygen;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static synchronized void addOxygen(String player, double oxygen) {

		try {
			openConnection();
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT oxygen FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double oxygen2 = result1.getDouble("oxygen");

			PreparedStatement ps2 = connection
					.prepareStatement("UPDATE `user_data` SET `oxygen`=? WHERE name=?");
			ps2.setString(2, player);
			ps2.setDouble(1, oxygen + oxygen2);
			ps2.executeUpdate();
			ps1.close();
			result1.close();
			ps2.close();
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void removeOxygen(String player, double oxygen) {

		try {
			openConnection();
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT oxygen FROM `bukkit`.`user_data` WHERE name=?");
			ps1.setString(1, player);
			ResultSet result1 = ps1.executeQuery();
			result1.next();
			double oxygen2 = result1.getDouble("oxygen");
			if (!(oxygen2 - oxygen < 0)) {
				PreparedStatement ps2 = connection
						.prepareStatement("UPDATE `bukkit`.`user_data` SET `oxygen`=? WHERE name=?");
				ps2.setString(2, player);
				ps2.setDouble(1, oxygen2 - oxygen);
				ps2.executeUpdate();
				ps1.close();
				result1.close();
				ps2.close();
				closeConnection();
			} else {
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void setOxygen(String player, double newOxygen) {

		try {
			openConnection();
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `user_data` SET `oxygen`=? WHERE name=?");
			ps.setString(2, player);
			ps.setDouble(1, newOxygen);
			ps.executeUpdate();
			ps.close();
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
