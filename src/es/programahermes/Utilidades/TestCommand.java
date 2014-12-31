package es.programahermes.Utilidades;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import es.programahermes.Tecnica.TecnicaPerms;

public class TestCommand implements CommandExecutor, Listener {

	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("test")) {
				
				Iterator<Recipe> iterator = Bukkit.recipeIterator();
				ItemStack result;
				Recipe recipe;
				Material resultType;
				 
				while(iterator.hasNext())
				{
				    recipe = iterator.next();
				 
				    if(recipe == null) // some server mods adds a null recipe, better safe than sorry :P
				         continue;
				 
				    result = recipe.getResult();
				    resultType = result.getType();
				 
				    if(resultType == Material.SHEARS || (resultType == Material.STICK && result.getDurability() == 15) )
				        iterator.remove();
				}
				
			}
		}

		return false;
	}


	
}
