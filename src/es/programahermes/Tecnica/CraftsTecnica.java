package es.programahermes.Tecnica;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.material.MaterialData;

public class CraftsTecnica {

	
	public static ShapedRecipe Chest = new ShapedRecipe(new ItemStack(Material.CHEST, 1))
			.shape("   ", "*%*", "   ")
			.setIngredient('*', new MaterialData(Material.BAKED_POTATO))
			.setIngredient('%', new ItemStack(Material.FLINT, (byte) 1195).getData());

	public static void registrarTecnina() {
		Bukkit.getServer().addRecipe(CraftsTecnica.Chest);
	}
	
}
