package es.programahermes;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import es.programahermes.Commands.LevelUpCommand;
import es.programahermes.Commands.PointsCommand;
import es.programahermes.Commands.Stats;
import es.programahermes.Habilidades.Biologia;
import es.programahermes.Habilidades.Geologia;

public class Main extends JavaPlugin implements CommandExecutor {
	
	
	public Plugin plugin = this;
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Geologia(), this);
		getServer().getPluginManager().registerEvents(new Biologia(null), this);
		loadConfiguration();
		getCommand("subirnivel").setExecutor(new LevelUpCommand());
		getCommand("puntos").setExecutor(new PointsCommand());
		getCommand("stats").setExecutor(new Stats());
	}

	
	Biologia Biologia = new Biologia(this);
	
	
	public void onDisable() {
	}

	public void loadConfiguration() {
		this.plugin.getConfig().options().copyDefaults(true);
		this.plugin.saveConfig();
	}
	
	

}