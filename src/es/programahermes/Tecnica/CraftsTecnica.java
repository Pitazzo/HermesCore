package es.programahermes.Tecnica;


import net.morematerials.MoreMaterials;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.getspout.spout.inventory.SimpleSpoutShapedRecipe;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;

import es.programahermes.Utilidades.Ingredients;
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
	
	public static SpoutShapedRecipe moduloLASER = new SpoutShapedRecipe(Items.VanillaCustom((short) 1575, 1))
	.shape("   ", "*%*", "   ")
	.setIngredient('*', Ingredients.led_rojo)
	.setIngredient('%', Ingredients.resistencia);

	
	public static void registrarTecnina() {
		SimpleSpoutShapedRecipe smoduloLASER = SimpleSpoutShapedRecipe.fromSpoutRecipe(moduloLASER);
		smoduloLASER.addToCraftingManager();

	}
	
}
