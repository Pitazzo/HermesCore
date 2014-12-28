package es.programahermes.Tecnica;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.getspout.spout.inventory.SimpleSpoutShapedRecipe;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;

import es.programahermes.Crafteos.Ingredients;
import es.programahermes.Crafteos.Items;

public class EMCATools {

	// falcon
	public static SpoutShapedRecipe falcon = new SpoutShapedRecipe(
			Items.SemiCustom(Material.IRON_PICKAXE, 1,
					"Cortador de plasma Falcon", "Carga: 0%"))
			.shape("qwe", "tsw", "qwe")
			.setIngredient('q', Ingredients.chip_silicio)
			.setIngredient(
					'w',
					new SpoutItemStack(Ingredients.plancha_aluminio)
							.getMaterial())
			.setIngredient('e', Ingredients.junta_aluminio)
			.setIngredient('t', Ingredients.modulo_laser)
			.setIngredient('s', Ingredients.motor);

	// prospector
	public static SpoutShapedRecipe prospector = new SpoutShapedRecipe(
			Items.SemiCustom(Material.WOOD_PICKAXE, 1,
					"Martillo eléctrico de prospección", "Carga: 0%"))
			.shape(" q ", "tst", " wt")
			.setIngredient('q', Ingredients.chip_silicio)
			.setIngredient('w', Ingredients.gatillo)
			.setIngredient('t', Ingredients.barra_aluminio)
			.setIngredient('s', Ingredients.motor);
	
	// rhino
	public static SpoutShapedRecipe rhino = new SpoutShapedRecipe(
			Items.SemiCustom(Material.IRON_HOE, 1,
					"Recolector Rhino", "Carga: 0%"))
			.shape(" q ", "tsa", " wd")
			.setIngredient('q', new SpoutItemStack(new ItemStack(Material.GLASS_BOTTLE, 1)).getMaterial())
			.setIngredient('w', Ingredients.gatillo)
			.setIngredient('t', Ingredients.aguja_silicio)
			.setIngredient('s', Ingredients.motor)
			.setIngredient('a', Ingredients.chip_silicio)
			.setIngredient('d', Ingredients.barra_aluminio);
	
	// seed2222
	public static SpoutShapedRecipe seed = new SpoutShapedRecipe(
			Items.SemiCustom(Material.STONE_HOE, 1,
					"SEED 2222", "Carga: 0%"))
			.shape(" q ", "tsa", " wd")
			.setIngredient('q', new SpoutItemStack(new ItemStack(Material.GLASS_BOTTLE, 1)).getMaterial())
			.setIngredient('w', Ingredients.gatillo)
			.setIngredient('t', Ingredients.triple_aguja)
			.setIngredient('s', Ingredients.motor)
			.setIngredient('a', Ingredients.chip_silicio)
			.setIngredient('d', Ingredients.barra_aluminio);

	//eagle
	public static SpoutShapedRecipe eagle = new SpoutShapedRecipe(
			Items.SemiCustom(Material.STONE_HOE, 1,
					"Láser de preción Eagle", "Carga: 0%"))
			.shape("aqw", "qts", "ac")
			.setIngredient('q', Ingredients.modulo_laser)
			.setIngredient('w', Ingredients.junta_aluminio)
			.setIngredient('a', Ingredients.plancha_asli)
			.setIngredient('t', Ingredients.motor)
			.setIngredient('s', Ingredients.chip_silicio)
			.setIngredient('c', Ingredients.gatillo);
	
	
	
	public static void registerEMCA(){
		SimpleSpoutShapedRecipe sfalcon = SimpleSpoutShapedRecipe
				.fromSpoutRecipe(falcon);
		SimpleSpoutShapedRecipe sprospector = SimpleSpoutShapedRecipe
				.fromSpoutRecipe(prospector);
		SimpleSpoutShapedRecipe srhino = SimpleSpoutShapedRecipe
				.fromSpoutRecipe(rhino);
		SimpleSpoutShapedRecipe sseed = SimpleSpoutShapedRecipe
				.fromSpoutRecipe(seed);
		SimpleSpoutShapedRecipe seagle = SimpleSpoutShapedRecipe
				.fromSpoutRecipe(eagle);
		
		seagle.addToCraftingManager();
		sfalcon.addToCraftingManager();
		sprospector.addToCraftingManager();
		srhino.addToCraftingManager();
		sseed.addToCraftingManager();
		
	}

}
