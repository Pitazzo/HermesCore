package es.programahermes.Tecnica;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.material.MaterialData;
import org.getspout.spoutapi.material.CustomItem;

import es.programahermes.Utilidades.Ingredients;
import es.programahermes.Utilidades.Items;

public class CraftsTecnica {

	@SuppressWarnings("deprecation")
	public static ShapedRecipe Chest = new ShapedRecipe(Items.SemiCustom(
			Material.CHEST, 1, "Cápsula de almacenamiento"))
			.shape("   ", "*%*", "   ")
			.setIngredient('*', new MaterialData(Material.BAKED_POTATO))
			.setIngredient('%', new ItemStack(Material.FLINT, (byte) 1195).getData());

	public static void registrarTecnina() {
		Bukkit.getServer().addRecipe(CraftsTecnica.Chest);
	}
	
}
