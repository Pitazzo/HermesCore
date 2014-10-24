package programahermes.es.Crafteos;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import es.programahermes.Utilidades.Miscelaneo;

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
	public static ItemStack plancha_aluminio = Items.SemiCustom(
			Material.INK_SACK, (short) 9, 1, "Plancha de aluminio");
	public static MaterialData circuito_impreso = Items.SemiCustom(
			Material.INK_SACK, (short) 10, 1, "Circuito impreso").getData();	
	public static ItemStack cables = Items.SemiCustom(
			Material.INK_SACK, (short) 12, 1, "Cableado");
	
	public static ItemStack tanqueO2vacio = Items.SemiCustom(
			Material.GHAST_TEAR, 1, "Tanque de O2", "O2: 500L");
	
	//customs
	public static org.getspout.spoutapi.material.Material led_rojo = Items.Custom(1577);
	public static org.getspout.spoutapi.material.Material resistencia = Items.Custom(1579);
	public static org.getspout.spoutapi.material.Material bisagra = Items.Custom(1581);
	public static org.getspout.spoutapi.material.Material junta_aluminio = Items.Custom(1578);
	public static org.getspout.spoutapi.material.Material led_blanco = Items.Custom(1580);
	public static org.getspout.spoutapi.material.Material modulo_laser = Items.Custom(1575);
	public static org.getspout.spoutapi.material.Material aguja_silicio = Items.Custom(1574);
	public static org.getspout.spoutapi.material.Material sierra_acero = Items.Custom(1576);
	public static org.getspout.spoutapi.material.Material triple_aguja = Items.Custom(1586);
	public static org.getspout.spoutapi.material.Material canon = Items.Custom(1589);
	public static org.getspout.spoutapi.material.Material gatillo = Items.Custom(1590);
	public static org.getspout.spoutapi.material.Material motor = Items.Custom(1588);
	public static org.getspout.spoutapi.material.Material bobina_cobre = Items.Custom(1592);
	public static org.getspout.spoutapi.material.Material tubo_plastico = Items.Custom(1591);
	public static org.getspout.spoutapi.material.Material barra_aluminio = Items.Custom(1587);
	public static org.getspout.spoutapi.material.Material tarjeta_memoria = Items.Custom(1598);
	public static org.getspout.spoutapi.material.Material chip_cobre = Items.Custom(1595);
	public static org.getspout.spoutapi.material.Material chip_silicio = Items.Custom(1597);
	public static org.getspout.spoutapi.material.Material lente_optica = Items.Custom(1596);
	public static org.getspout.spoutapi.material.Material armonica = Items.Custom(1593);
	public static org.getspout.spoutapi.material.Material hilo_cobre = Items.Custom(1594);



	@EventHandler
	public void onItemClick(InventoryClickEvent event) {
		if (event.getSlotType().equals(SlotType.CRAFTING)
				&& event.getCursor() != null) {
			ItemStack item = event.getCursor();
			
			System.out.println("Data: "+item.getData());
			
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
