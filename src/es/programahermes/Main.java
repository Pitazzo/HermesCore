package es.programahermes;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;





import es.programahermes.Commands.LevelUpCommand;
import es.programahermes.Commands.PointsCommand;
import es.programahermes.Commands.SetHability;
import es.programahermes.Commands.Stats;
import es.programahermes.Habilidades.Biologia;
import es.programahermes.Habilidades.Geologia;
import es.programahermes.Utilidades.EnergyCells;
import es.programahermes.Utilidades.Prospectar;

public class Main extends JavaPlugin implements CommandExecutor {

	public  Plugin plugin = this;

	public void onEnable() {
		
		getServer().getPluginManager().registerEvents(new Geologia(), this);
		getServer().getPluginManager().registerEvents(new Prospectar(), this);
		getServer().getPluginManager().registerEvents(new EnergyCells(), this);
		getServer().getPluginManager().registerEvents(new Biologia(), this);

		loadConfiguration();
		getCommand("subirnivel").setExecutor(new LevelUpCommand());
		getCommand("puntos").setExecutor(new PointsCommand());
		getCommand("stats").setExecutor(new Stats());
		getCommand("sethabilidad").setExecutor(new SetHability());
		MySQL.openConnection();
	}


	public void onDisable() {
		MySQL.closeConnection();
	}

;

	public void loadConfiguration() {
		this.plugin.getConfig().options().copyDefaults(true);
		this.plugin.saveConfig();
	}

}