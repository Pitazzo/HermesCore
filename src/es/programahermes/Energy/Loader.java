package es.programahermes.Energy;

import java.util.List;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Loader implements Listener {

	public static void reload(ItemStack item){
		String lore = item.getItemMeta().getLore().get(0);
		if(Batteries.isEmpty(item) && lore.contains("Rendimiento: ")){
			
			String lore2 = lore.replace("Rendimiento:", "");
			String lore3 = lore2.replace("%", "");
			double percentage = Double.parseDouble(lore3);
			System.out.println("0");
			System.out.println(percentage+"%");
			if(percentage>0){
				System.out.println("1");
				double newP = percentage-5.5;
				List<String> list = item.getItemMeta().getLore();
				list.set(0, "Rendimiento: "+newP+"%");
				System.out.println("5");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(list);
				meta.setDisplayName("Batería cargada");
				item.setItemMeta(meta);
				System.out.println("4");
			}else{
				System.out.println("-1");
				List<String> list = item.getItemMeta().getLore();
				list.set(0, "INSERVIBLE");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(list);
				item.setItemMeta(meta);
			}
			
		}
	}
	
}
