package es.programahermes.Tecnica;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.inventory.SpoutItemStack;

import es.programahermes.MySQL;
import es.programahermes.Crafteos.Ingredients;
import es.programahermes.Crafteos.Items;

public class TecnicaPerms implements Listener {

	@EventHandler
	public void onCraftEvent(CraftItemEvent event) {
		Player player = (Player) event.getWhoClicked();
		if (MySQL.getHability(player.getName()).equals("Tecnica")) {
			org.getspout.spoutapi.material.Material material = new SpoutItemStack(
					event.getRecipe().getResult()).getMaterial();
			int req;

			if (lvl1m.contains(material)) {
				req = 1;
			} else if (lvl2m.contains(material)) {
				req = 2;
			} else if (lvl3m.contains(material)) {
				req = 3;
			} else {
				req = 10;
			}

			// player.sendMessage("Nivel requerido: " + req);

			if (MySQL.getLevel(player.getName()) < req) {
				player.sendMessage(ChatColor.DARK_RED + "Tu nivel ("
						+ MySQL.getLevel(player.getName())
						+ ") no es suficiente para hacer esto");
				event.setCancelled(true);
			} else {
				String result = event.getRecipe().getResult().getType()
						.toString();
				int amount = event.getRecipe().getResult().getAmount();

				MySQL.addEarnedPoints(player.getName(), "craft", result, amount);
			}
		}

	}

	public static List<Object> lvl1 = new ArrayList<Object>();
	public static List<Object> lvl2 = new ArrayList<Object>();
	public static List<Object> lvl3 = new ArrayList<Object>();

	public static List<org.getspout.spoutapi.material.Material> lvl1m = new ArrayList<org.getspout.spoutapi.material.Material>();
	public static List<org.getspout.spoutapi.material.Material> lvl2m = new ArrayList<org.getspout.spoutapi.material.Material>();
	public static List<org.getspout.spoutapi.material.Material> lvl3m = new ArrayList<org.getspout.spoutapi.material.Material>();

	public static void loader() {
		// lvl1
		lvl1.add(Ingredients.motor_avanzado);
		lvl1.add(Items.SemiCustom(Material.IRON_PICKAXE, 1,
				"Cortador de plasma Falcon", "Carga: 0%"));

		// lvl2
		lvl2.add(Items.SemiCustom(Material.IRON_HOE, 1, "Recolector Rhino",
				"Carga: 0%"));
		lvl2.add(Ingredients.bobina_cobre);

		// 1
		for (Object object : lvl1) {
			if (object.getClass().equals(
					net.morematerials.materials.MMCustomItem.class)) {
				lvl1m.add((org.getspout.spoutapi.material.Material) object);
			} else if (object.getClass().equals(
					org.bukkit.inventory.ItemStack.class)) {
				lvl1m.add(new SpoutItemStack((ItemStack) object).getMaterial());
			}
		}
		// 2
		for (Object object : lvl2) {
			if (object.getClass().equals(
					net.morematerials.materials.MMCustomItem.class)) {
				lvl2m.add((org.getspout.spoutapi.material.Material) object);
			} else if (object.getClass().equals(
					org.bukkit.inventory.ItemStack.class)) {
				lvl2m.add(new SpoutItemStack((ItemStack) object).getMaterial());
			}
		}
		// 2
		for (Object object : lvl3) {
			if (object.getClass().equals(
					net.morematerials.materials.MMCustomItem.class)) {
				lvl3m.add((org.getspout.spoutapi.material.Material) object);
			} else if (object.getClass().equals(
					org.bukkit.inventory.ItemStack.class)) {
				lvl3m.add(new SpoutItemStack((ItemStack) object).getMaterial());
			}
		}

	}
}
