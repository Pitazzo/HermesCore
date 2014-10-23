package es.programahermes.Tecnica;


import net.morematerials.MoreMaterials;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.getspout.spout.inventory.SimpleSpoutShapedRecipe;
import org.getspout.spoutapi.inventory.SpoutItemStack;
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
    public static SpoutShapedRecipe capsulaAlmacenamiento = new SpoutShapedRecipe(new ItemStack(Material.CHEST, 1))
	.shape("qee", "ete", "aea")
	.setIngredient('q', Ingredients.bisagra)
	.setIngredient('e', new SpoutItemStack(Ingredients.plancha_aluminio).getMaterial())
	.setIngredient('a', Ingredients.junta_aluminio)
	.setIngredient('t', Ingredients.chip_cobre);
    
    public static SpoutShapedRecipe motor = new SpoutShapedRecipe(new ItemStack(Material.CHEST, 1))
	.shape("qeq", "wtw", "aya")
	.setIngredient('q', Ingredients.chip_silicio)
	.setIngredient('e', Ingredients.hilo_cobre)
	.setIngredient('w', Ingredients.bobina_cobre)
	.setIngredient('t', Ingredients.barra_aluminio)
	.setIngredient('a', new SpoutItemStack(Ingredients.plancha_aluminio).getMaterial())
	.setIngredient('y', Ingredients.junta_aluminio);
    
	public static SpoutShapedRecipe moduloLASER = new SpoutShapedRecipe(Items.VanillaCustom((short) 1575, 1))
	.shape("   ", "*%*", "   ")
	.setIngredient('*', Ingredients.led_rojo)
	.setIngredient('%', Ingredients.resistencia);

	
	public static void registrarTecnina() {
		SimpleSpoutShapedRecipe smoduloLASER = SimpleSpoutShapedRecipe.fromSpoutRecipe(moduloLASER);
		SimpleSpoutShapedRecipe smotor = SimpleSpoutShapedRecipe.fromSpoutRecipe(motor);
		SimpleSpoutShapedRecipe scapsulaAlmacenamiento = SimpleSpoutShapedRecipe.fromSpoutRecipe(capsulaAlmacenamiento);
		
		smoduloLASER.addToCraftingManager();
		scapsulaAlmacenamiento.addToCraftingManager();
		smotor.addToCraftingManager();

	}
	
}
