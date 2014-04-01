package es.programahermes.Utilidades;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Recipes {

	public static ShapedRecipe Web = new ShapedRecipe(item())
			.shape("   ", "*%<", "   ")
			.setIngredient('*', Material.COAL)
			.setIngredient('%', Material.GHAST_TEAR)
			.setIngredient('<', Material.POTION);

	public static ItemStack item() {
		ItemStack item = new ItemStack(Material.GHAST_TEAR, 1);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		meta.setDisplayName("Tanque de O2");
		lore.add("O2: 500L");
		meta.setLore(lore);
		item.setItemMeta(meta);

		return item;
	}

}
