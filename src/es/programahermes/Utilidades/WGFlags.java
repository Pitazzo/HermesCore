package es.programahermes.Utilidades;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.mewin.WGCustomFlags.WGCustomFlagsPlugin;
import com.sk89q.worldguard.protection.flags.BooleanFlag;

public class WGFlags {

	public static WGCustomFlagsPlugin getWGCustomFlags()
	{
	  Plugin plugin = Bukkit.getPluginManager().getPlugin("WGCustomFlags");
	  
	  if (plugin == null || !(plugin instanceof WGCustomFlagsPlugin))
	  {
	    return null;
	  }

	  return (WGCustomFlagsPlugin) plugin;
	}

	public static BooleanFlag presurizada = new BooleanFlag("presurizada");
	
}
