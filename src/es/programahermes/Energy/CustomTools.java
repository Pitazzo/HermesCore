package es.programahermes.Energy;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import es.programahermes.Utilidades.Miscelaneo;

public class CustomTools {

	public static List<String> validTools = new ArrayList<String>();

	public static void loadTools() {
		validTools.add("Cortador de plasma Falcon");
	}

	public static boolean isATool(ItemStack item) {
		String name = Miscelaneo.getName(item);
		if (validTools.contains(name)) {
			return true;
		}
		return false;
	}

	public static double getCarga(ItemStack item) {
		if (isATool(item)) {
			String carga = item.getItemMeta().getLore().get(0);
			if (carga != null) {
				String carga2 = carga.replace("Carga: ", "");
				String carga3 = carga2.replace("%", "");
				return Double.parseDouble(carga3);
			}
		}

		return 0;
	}
	
	public static void setCarga(ItemStack item, double carga){
		if(isATool(item)){
			ItemMeta meta = item.getItemMeta();
			List<String> lore = meta.getLore();
			lore.set(0, "Carga: "+carga+"%");
			meta.setLore(lore);
			item.setItemMeta(meta);
		}
	}
	
	public static void useTool(ItemStack item){
		if(isATool(item)){
			double usos = getCarga(item);
			if(usos > 0){
				setCarga(item, (usos-2.5));
			}
		}
	}
}
