package es.programahermes.Utilidades;


import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class Ingredients implements Listener {

	public static MaterialData fecula = Items.SemiCustom(Material.CLAY_BALL, 1,
			"Fécula de patata").getData();
	public static MaterialData plastico_bio = Items.SemiCustom(
			Material.INK_SACK, (short) 1, 1,
			"Láminas de plástico biodegradable").getData();
	public static MaterialData reactivos_fecula = Items.SemiCustom(
			Material.INK_SACK, (short) 5, 1, "Reactivos para fécula").getData();
	public static MaterialData rollo_plastico = Items.SemiCustom(Material.BOWL,
			1, "Rollo de plástico").getData();
	public static MaterialData panel_silicio = Items.SemiCustom(
			Material.COAL_BLOCK, 1, "Panel de silicio").getData();
	public static MaterialData circuito_cobre = Items.SemiCustom(
			Material.INK_SACK, (short) 7, 1, "Circuito de cobre").getData();
	public static MaterialData plancha_aluminio = Items.SemiCustom(
			Material.INK_SACK, (short) 9, 1, "Plancha de aluminio").getData();
	public static MaterialData circuito_impreso = Items.SemiCustom(
			Material.INK_SACK, (short) 10, 1, "Circuito impreso").getData();
	public static MaterialData cactus = Items.Custom(
			(short) 1195, 1).getData();

	
	@EventHandler
	public void onItemClick(InventoryClickEvent event) {			
		if (event.getSlotType().equals(SlotType.CRAFTING)
				&& event.getCursor() != null) {
			ItemStack item = event.getCursor();
			if (Miscelaneo.getDisplayName(item) == null
					|| Miscelaneo.getDisplayName(item) == "ERROR") {
				if (Miscelaneo.getIRName(item) != null
						&& Miscelaneo.getIRName(item) != "Sin nombre IR") {
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(Miscelaneo.getIRName(item));
					item.setItemMeta(meta);

				}
			}
		}

	}

}
