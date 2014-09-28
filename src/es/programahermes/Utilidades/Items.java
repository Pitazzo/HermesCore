package es.programahermes.Utilidades;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {

	public static ItemStack SemiCustom(Material material, int amount, String name, String lore1){
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		meta.setDisplayName(name);
		lore.add(lore1);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack SemiCustom(Material material, short durability, int amount, String name, String lore1){
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		meta.setDisplayName(name);
		item.setDurability(durability);
		lore.add(lore1);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack SemiCustom(Material material, int amount, String name){
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack SemiCustom(Material material, short durability, int amount, String name){
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		item.setDurability(durability);
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack Custom(short durability, int amount){
		ItemStack item = new ItemStack(Material.FLINT, amount);
		item.setDurability(durability);
		return item;
	}
	
}
