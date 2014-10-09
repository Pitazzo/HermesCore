package es.programahermes.Energy;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;


import es.programahermes.Utilidades.Ingredients;
import es.programahermes.Utilidades.Items;

public class ReloadCrafts {

	public static ShapedRecipe Battery = new ShapedRecipe(Items.SemiCustom(
			Material.COAL, 1, "Batería cargada", "Rendimiento: 100%"))
			.shape("   ", "*%*", "   ").setIngredient('*', Material.IRON_INGOT)
			.setIngredient('%', Material.REDSTONE);

	public static ShapedRecipe Pickaxe = new ShapedRecipe(Items.SemiCustom(
			Material.IRON_PICKAXE, 1, "Cortador de plasma Falcon", "Carga: 0%"))
			.shape("   ", "*%*", "   ")
			.setIngredient('*', Material.STICK)
			.setIngredient('%', Ingredients.cactus);
	
	public static ShapelessRecipe loadedPickaxe = new ShapelessRecipe(Items.SemiCustom(
			Material.IRON_PICKAXE, 1, "Cortador de plasma Falcon", "Carga: 100%"))
	.addIngredient(Items.SemiCustom(
			Material.COAL, 1, "Batería cargada").getData())
	.addIngredient(Items.SemiCustom(Material.IRON_PICKAXE, 1, "Cortadora de plasma Falcon").getData());
	
	public static void register(){
		Bukkit.getServer().addRecipe(ReloadCrafts.Battery);
		Bukkit.getServer().addRecipe(ReloadCrafts.Pickaxe);
		Bukkit.getServer().addRecipe(ReloadCrafts.loadedPickaxe);
	}

	
}
