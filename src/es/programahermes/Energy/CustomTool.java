package es.programahermes.Energy;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomTool {

	private static Material material;
	private static String name;
	private static ItemStack item;
	
	public CustomTool(ItemStack item, String name){
		this.material = item.getType();
		this.name = name;	
		this.item = item;
	}
	
	public static Material getMaterial(){
		return material;
	}
	
	public static String getName(){
		return name;
	}
	
	public static int getUsos(){
		String lore = item.getItemMeta().getLore().get(0);
		lore.replace("Usos:", "");
		return Integer.parseInt(lore);
	}
	
	public static ItemStack getItem(){
		
		return item;
	}
	
	public static List<CustomTool> tools = new ArrayList();
	
	public static void loadTools(){
		tools.add(new CustomTool(new ItemStack(Material.IRON_PICKAXE, 1), "Cortadora láser Falcon"));
		tools.add(new CustomTool(new ItemStack(Material.DIAMOND_PICKAXE, 1), "POLARIS Stonebreaker 2425K"));
	}
	
	public static boolean isATool(ItemStack item){
		for(CustomTool tool : tools){
			if(tool.getMaterial().equals(item.getType()) && tool.getName().equals(item.getItemMeta().getDisplayName())){
				return true;
			}
		}
		
		return false;
	}
	
	public void setUsos(CustomTool tool, int usos){
		ItemMeta meta = tool.getItem().getItemMeta();
		meta.getLore().set(0, "Usos: "+usos);
		tool.getItem().setItemMeta(meta);
	}
	
}
