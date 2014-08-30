package es.programahermes.Energy;

import java.util.List;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Loader implements Listener {

	public static void reload(ItemStack item){
		String lore = item.getItemMeta().getLore().get(0);
		if(Batteries.isEmpty(item) && lore.contains("Rendimiento: ")){
			
			lore.replace("Rendimiento:", "");
			lore.replace("%", "");
			double percentage = Double.parseDouble(lore);
			if(percentage>0){
				double newP = percentage-5.5;
				List<String> list = item.getItemMeta().getLore();
				list.set(0, "Porcentaje: "+newP+"%");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(list);
				meta.setDisplayName("Batería cargada");
				item.setItemMeta(meta);
			}else{
				List<String> list = item.getItemMeta().getLore();
				list.set(0, "INSERVIBLE");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(list);
				item.setItemMeta(meta);
			}
			
		}
	}
	
}
