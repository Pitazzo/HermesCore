package es.programahermes.Weight;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class WeightManager {

	public static void check(Player player){
		Inventory inv = player.getInventory();
		for(ItemStack item: inv.getContents()){
			
		}
	}
	
	public static boolean hasWeight(ItemStack item){
		if (item != null && item.getItemMeta() != null) {
			if (item.getItemMeta().getLore() != null) {
				if (item.getItemMeta().getLore().get(1).contains("Peso: ")) {
					return true;
				}
			}
		}

		return false;

	}
	
	public static int getWight(ItemStack item) {
		if (item.getItemMeta() != null) {
			if (item.getItemMeta().getLore() != null) {
				String o2 = item.getItemMeta().getLore().get(0);
				if (o2.contains("Peso: ")) {
					String o3 = o2.replace("Peso: ", "");
					String o4 = o3.replace("Kg", "");
					int oxygen = Integer.parseInt(o4);
					return oxygen;

				}

			}
		}

		return 0;

	}
	
}
