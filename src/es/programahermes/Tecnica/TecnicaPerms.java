package es.programahermes.Tecnica;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import es.programahermes.MySQL;
import es.programahermes.Crafteos.Ingredients;
import es.programahermes.Crafteos.Items;

public class TecnicaPerms implements Listener {

	@EventHandler
	public void onCraftEvent(CraftItemEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getRecipe().getResult();
		org.getspout.spoutapi.material.Material material;

	}

	public static List<Object> lvl1 = new ArrayList<Object>();
	public static List<Object> lvl2 = new ArrayList<Object>();

	public static List<org.getspout.spoutapi.material.Material> lvl1m = new ArrayList<org.getspout.spoutapi.material.Material>();
	public static List<org.getspout.spoutapi.material.Material> lvl2m = new ArrayList<org.getspout.spoutapi.material.Material>();

	public static void loader() {
		//lvl1
		lvl1.add(Ingredients.motor_avanzado);
		lvl1.add(Items.SemiCustom(Material.IRON_PICKAXE, 1,
					"Cortador de plasma Falcon", "Carga: 0%"));
		
		
		//lvl2
		lvl2.add(Items.SemiCustom(Material.IRON_HOE, 1,
					"Recolector Rhino", "Carga: 0%"));
		lvl2.add(Ingredients.bobina_cobre);
		
		
		for(Object object : lvl1){
			if(object.getClass().equals(net.morematerials.materials.MMCustomItem.class)){
				lvl1m.add((org.getspout.spoutapi.material.Material) object);
			}else if(object.getClass().equals(org.bukkit.inventory.ItemStack.class)){
				lvl1m.add(new SpoutItemStack((ItemStack)object).getMaterial());
			}
		}
		
		for(Object object : lvl2){
			System.out.println("Clase: "+object.getClass()+" Object: "+object);
		}
		
	}
}
