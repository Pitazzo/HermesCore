package es.programahermes.Tecnica;

import net.morematerials.MoreMaterials;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.getspout.spout.inventory.SimpleSpoutShapedRecipe;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;

import programahermes.es.Crafteos.Ingredients;
import programahermes.es.Crafteos.Items;


public class CraftsTecnica {

	public static MoreMaterials mm = (MoreMaterials) Bukkit.getServer()
			.getPluginManager().getPlugin("MoreMaterials");

	public static SpoutShapedRecipe puertaAluminio = new SpoutShapedRecipe(
			new ItemStack(Material.WOODEN_DOOR, 1))
			.shape("qe ", "ter", "qe ")
			.setIngredient('q', Ingredients.bisagra)
			.setIngredient(
					'e',
					new SpoutItemStack(Ingredients.plancha_aluminio)
							.getMaterial())
			.setIngredient('r', Ingredients.chip_silicio)
			.setIngredient('t', Ingredients.barra_aluminio);

	public static SpoutShapedRecipe capsulaAlmacenamiento = new SpoutShapedRecipe(
			new ItemStack(Material.CHEST, 1))
			.shape("qee", "ete", "aea")
			.setIngredient('q', Ingredients.bisagra)
			.setIngredient(
					'e',
					new SpoutItemStack(Ingredients.plancha_aluminio)
							.getMaterial())
			.setIngredient('a', Ingredients.junta_aluminio)
			.setIngredient('t', Ingredients.chip_cobre);
	
	public static SpoutShapedRecipe lamparaHalogena = new SpoutShapedRecipe(
			new ItemStack(Material.TORCH, 1))
			.shape(" e ", " q ", " r ")
			.setIngredient('q', Ingredients.chip_cobre)
			.setIngredient('e', (Ingredients.led_blanco))
			.setIngredient('r', new SpoutItemStack(Ingredients.cables)
			.getMaterial());
	
	public static SpoutShapedRecipe lamparaXenon = new SpoutShapedRecipe(
			new ItemStack(Material.REDSTONE_TORCH_OFF, 1))
			.shape(" e ", " q ", " r ")
			.setIngredient('q', Ingredients.chip_cobre)
			.setIngredient('e', (Ingredients.led_rojo))
			.setIngredient('r', new SpoutItemStack(Ingredients.cables)
			.getMaterial());

	public static SpoutShapedRecipe tanqueO2 = new SpoutShapedRecipe(
			new ItemStack(Material.GHAST_TEAR, 1))
			.shape(" e ", "q q", "qrq")
			.setIngredient(
					'q',
					new SpoutItemStack(Ingredients.plancha_aluminio)
							.getMaterial())
			.setIngredient('e', Ingredients.tubo_plastico)
			.setIngredient('r', Ingredients.junta_aluminio);

	public static ShapedRecipe bol = new ShapedRecipe(new ItemStack(
			Material.BOWL, 1)).shape("   ", "q q", " q ").setIngredient('q',
			new ItemStack(Ingredients.plancha_aluminio).getData());

	public static ShapedRecipe planchaAluminio = new ShapedRecipe(
			Ingredients.plancha_aluminio).shape("   ", "qq ", "qq ")
			.setIngredient('q', new ItemStack(Material.IRON_INGOT).getData());

	public static ShapedRecipe barraAluminio = new ShapedRecipe(
			Items.VanillaCustom((short) 1587, 1)).shape(" q ", " q ", " q ")
			.setIngredient('q', new ItemStack(Material.IRON_INGOT).getData());

	public static SpoutShapedRecipe motor = new SpoutShapedRecipe(
			new SpoutItemStack(Ingredients.motor, 1))
			.shape("qeq", "wtw", "aya")
			.setIngredient('q', Ingredients.chip_cobre)
			.setIngredient('e', Ingredients.hilo_cobre)
			.setIngredient('w', Ingredients.bobina_cobre)
			.setIngredient('t', Ingredients.barra_aluminio)
			.setIngredient(
					'a',
					new SpoutItemStack(Ingredients.plancha_aluminio)
							.getMaterial())
			.setIngredient('y', Ingredients.junta_aluminio);

	public static SpoutShapedRecipe assembler = new SpoutShapedRecipe(
			new ItemStack(Material.WORKBENCH, 1))
			.shape("qeq", "wtw", "qwq")
			.setIngredient('q', Ingredients.junta_aluminio)
			.setIngredient('e', Ingredients.chip_silicio)
			.setIngredient(
					'w',
					new SpoutItemStack(Ingredients.plancha_aluminio)
							.getMaterial())
			.setIngredient(
					't',
					new SpoutItemStack(new ItemStack(Material.SIGN))
							.getMaterial());

	public static SpoutShapedRecipe bobina = new SpoutShapedRecipe(
			new ItemStack(new SpoutItemStack(Ingredients.bobina_cobre, 1)))
			.shape("qeq", "qqq", "qqq")
			.setIngredient('q', Ingredients.hilo_cobre)
			.setIngredient(
					'e',
					new SpoutItemStack(new ItemStack(Ingredients.cables))
							.getMaterial());

	public static SpoutShapedRecipe moduloLASER = new SpoutShapedRecipe(
			Items.VanillaCustom((short) 1575, 1))
			.shape(" q  ", "*%*", "ror")
			.setIngredient('*', Ingredients.led_rojo)
			.setIngredient('q', Ingredients.lente_optica)
			.setIngredient('%', Ingredients.resistencia)
			.setIngredient(
					'r',
					new SpoutItemStack(new ItemStack(Ingredients.cables))
							.getMaterial())
			.setIngredient('o', Ingredients.chip_silicio);

	public static SpoutShapedRecipe horno = new SpoutShapedRecipe(
			new ItemStack(Material.FURNACE, 1))
			.shape("qeq", "w w", "qwq")
			.setIngredient('q', Ingredients.junta_aluminio)
			.setIngredient('e', Ingredients.resistencia)
			.setIngredient(
					'w',
					new SpoutItemStack(Ingredients.plancha_aluminio)
							.getMaterial());

	public static void registrarTecnina() {
		SimpleSpoutShapedRecipe smoduloLASER = SimpleSpoutShapedRecipe
				.fromSpoutRecipe(moduloLASER);
		SimpleSpoutShapedRecipe slamparaHalogena = SimpleSpoutShapedRecipe
				.fromSpoutRecipe(lamparaHalogena);
		SimpleSpoutShapedRecipe slamparaXenon = SimpleSpoutShapedRecipe
				.fromSpoutRecipe(lamparaXenon);
		SimpleSpoutShapedRecipe stanqueO2 = SimpleSpoutShapedRecipe
				.fromSpoutRecipe(tanqueO2);
		SimpleSpoutShapedRecipe shorno = SimpleSpoutShapedRecipe
				.fromSpoutRecipe(horno);
		SimpleSpoutShapedRecipe sbobina = SimpleSpoutShapedRecipe
				.fromSpoutRecipe(bobina);
		SimpleSpoutShapedRecipe smotor = SimpleSpoutShapedRecipe
				.fromSpoutRecipe(motor);
		SimpleSpoutShapedRecipe sassembler = SimpleSpoutShapedRecipe
				.fromSpoutRecipe(assembler);
		SimpleSpoutShapedRecipe scapsulaAlmacenamiento = SimpleSpoutShapedRecipe
				.fromSpoutRecipe(capsulaAlmacenamiento);

		Bukkit.addRecipe(barraAluminio);
		Bukkit.addRecipe(bol);
		Bukkit.addRecipe(planchaAluminio);
		
		slamparaHalogena.addToCraftingManager();
		slamparaXenon.addToCraftingManager();
		stanqueO2.addToCraftingManager();
		shorno.addToCraftingManager();
		sbobina.addToCraftingManager();
		smoduloLASER.addToCraftingManager();
		scapsulaAlmacenamiento.addToCraftingManager();
		sassembler.addToCraftingManager();
		smotor.addToCraftingManager();

	}

}
