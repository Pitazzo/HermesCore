package es.programahermes.Utilidades;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Recipes {

	public static ShapedRecipe O2 = new ShapedRecipe(tank())
			.shape("   ", "*%<", "   ").setIngredient('*', Material.COAL)
			.setIngredient('%', Material.GHAST_TEAR)
			.setIngredient('<', Material.POTION);

	public static ItemStack tank() {
		ItemStack item = new ItemStack(Material.GHAST_TEAR, 1);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		meta.setDisplayName("Tanque de O2");
		lore.add("O2: 500L");
		meta.setLore(lore);
		item.setItemMeta(meta);

		return item;
	}

	public static ShapelessRecipe reconstructor = new ShapelessRecipe(reconstructor())
	.addIngredient(bateria().getData())
	.addIngredient(reconstructorEmpty().getData());
	
	public static ItemStack bateria() {
		ItemStack item = new ItemStack(Material.COAL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Batería cargada");
		item.setItemMeta(meta);

		return item;

	}
	
	public static ItemStack reconstructorEmpty() {
		ItemStack item = new ItemStack(Material.GLOWSTONE_DUST, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Reconstructor óseo desarmado");
		item.setItemMeta(meta);

		return item;

	}
	
	public static ItemStack reconstructor() {
		ItemStack item = new ItemStack(Material.GLOWSTONE_DUST, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Reconstructor óseo armado");
		item.setItemMeta(meta);

		return item;

	}
}
