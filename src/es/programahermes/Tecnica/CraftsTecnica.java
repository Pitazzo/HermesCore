package es.programahermes.Tecnica;


import net.morematerials.MoreMaterials;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.getspout.spout.inventory.SimpleSpoutShapedRecipe;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;

import es.programahermes.Utilidades.Items;

public class CraftsTecnica {

    public static MoreMaterials mm = (MoreMaterials)
    Bukkit.getServer().getPluginManager().getPlugin("MoreMaterials");
	
	/*
	@SuppressWarnings("deprecation")
	public static ShapedRecipe Chest = new ShapedRecipe(new ItemStack(Material.CHEST, 1))
			.shape("   ", "*%*", "   ")
			.setIngredient('*', new MaterialData(Material.ACTIVATOR_RAIL))
			.setIngredient('%', new MaterialData(Material.STAINED_CLAY, (byte) 10));
*/
	
	public static SpoutShapedRecipe test = new SpoutShapedRecipe(Items.VanillaCustom((short) 1550, 1))
	.shape("   ", "* *", "   ")
	.setIngredient('*', mm.getSmpManager().getMaterial(1553));
	
	public static SpoutShapedRecipe test2 = new SpoutShapedRecipe(new org.bukkit.inventory.ItemStack(Material.CHEST, 1))
	.shape("   ", "* *", "   ")
	.setIngredient('*', Items.Custom(1547));
	
	public static void registrarTecnina() {
		
		SimpleSpoutShapedRecipe sshaped = SimpleSpoutShapedRecipe.fromSpoutRecipe(test);
		SimpleSpoutShapedRecipe sshaped2 = SimpleSpoutShapedRecipe.fromSpoutRecipe(test2);
		sshaped.addToCraftingManager();
		sshaped2.addToCraftingManager();

	}
	
}
