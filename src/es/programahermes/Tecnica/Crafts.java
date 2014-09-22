package es.programahermes.Tecnica;

import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

import es.programahermes.Utilidades.Ingredients;
import es.programahermes.Utilidades.Items;

public class Crafts {

	
	public ShapedRecipe Chest = new ShapedRecipe(Items.SemiCustom(
			Material.CHEST, 1, "Cápsula de almacenamiento"))
			.shape("   ", "*%*", "   ")
			.setIngredient('*', Ingredients.plastico_bio)
			.setIngredient('%', Ingredients.rollo_plastico);
	
}
