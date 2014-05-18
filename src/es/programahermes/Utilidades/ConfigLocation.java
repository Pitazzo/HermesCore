package es.programahermes.Utilidades;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigLocation {
	public static Location getFromConfig(String loctype) {
		Location l = new Location(Bukkit.getWorld("world"), 0, 0, 0);
		World w = Bukkit.getWorld((String) getPlayerObject("locations", loctype
				+ ".w"));
		float x = (float) getPlayerObject("locations", loctype + ".x");
		float y = (float) getPlayerObject("locations", loctype + ".y");
		float z = (float) getPlayerObject("locations", loctype + ".z");
		float p = (float) getPlayerObject("locations", loctype + ".p");
		float ya = (float) getPlayerObject("locations", loctype + ".ya");
		l.setWorld(w);
		l.setPitch(p);
		l.setX(x);
		l.setY(y);
		l.setZ(z);
		l.setYaw(ya);
		return l;
	}

	public static void setToConfig(String loctype, Location l) {
		setObject("locations", loctype + ".x", l.getX());
		setObject("locations", loctype + ".y", l.getY());
		setObject("locations", loctype + ".z", l.getZ());
		setObject("locations", loctype + ".p", l.getPitch());
		setObject("locations", loctype + ".ya", l.getYaw());
		setObject("locations", loctype + ".w", l.getWorld().getName());
	}

	public static String getPath(String par1Name) {
		return "locations/" + par1Name + ".yml";
	}

	private static void createNewData(String par1Name) {
		File f = new File(getPath(par1Name));
		if (!f.exists()) {
			try {
				YamlConfiguration.loadConfiguration(
						new File(Bukkit.getPluginManager()
								.getPlugin("HermesCore").getDataFolder(),
								"blocks.yml")).save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void setObject(String par1Name, String par2Path,
			Object par3Value) {
		try {
			File f = new File(getPath(par1Name));
			createNewData(par1Name);
			FileConfiguration pDat = YamlConfiguration.loadConfiguration(f);
			pDat.set(par2Path, par3Value);
			pDat.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Object getPlayerObject(String par1Name, String par2Path) {
		File f = new File(getPath(par1Name));
		createNewData(par1Name);
		FileConfiguration pDat = YamlConfiguration.loadConfiguration(f);
		return pDat.get(par2Path);
	}
}