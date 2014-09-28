package es.programahermes.Tecnica;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

import es.programahermes.Utilidades.Ingredients;
import es.programahermes.Utilidades.Items;

public class CraftsTecnica {

	public static ShapedRecipe Chest = new ShapedRecipe(Items.SemiCustom(
			Material.CHEST, 1, "Cápsula de almacenamiento"))
			.shape("   ", "*%*", "   ")
			.setIngredient('*', Ingredients.cactus)
			.setIngredient('%', Ingredients.rollo_plastico);

	public static void registrarTecnina() {
		Bukkit.getServer().addRecipe(CraftsTecnica.Chest);
	}

}
