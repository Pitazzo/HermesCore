package WGRegions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.mewin.WGCustomFlags.WGCustomFlagsPlugin;
import com.sk89q.worldguard.protection.flags.BooleanFlag;
import com.sk89q.worldguard.protection.flags.DoubleFlag;


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
	public static DoubleFlag gold = new DoubleFlag("gold");
	public static DoubleFlag coal = new DoubleFlag("coal");
	public static DoubleFlag iron = new DoubleFlag("iron");
	public static DoubleFlag diamond = new DoubleFlag("diamond");
	public static DoubleFlag redstone = new DoubleFlag("redstone");
	public static DoubleFlag lapis = new DoubleFlag("lapis");
	
	
}
