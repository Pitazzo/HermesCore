package es.programahermes;


import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import es.programahermes.Combat.Accuracy;
import es.programahermes.Combat.Melee;
import es.programahermes.Commands.LevelUpCommand;
import es.programahermes.Commands.PointsCommand;
import es.programahermes.Commands.SetHability;
import es.programahermes.Commands.Stats;
import es.programahermes.Commands.VSC;
import es.programahermes.Commands.Visor;
import es.programahermes.Geologia.Geologia;
import es.programahermes.Geologia.Perforadora;
import es.programahermes.Geologia.Prospectar;
import es.programahermes.Geologia.Refuerzos;
import es.programahermes.Habilidades.Biologia;
import es.programahermes.Habilidades.Estructural;
import es.programahermes.Habilidades.Quimica;
import es.programahermes.Habilidades.Tecnica;
import es.programahermes.Health.Fracturas;
import es.programahermes.Health.Septicemia;
import es.programahermes.SoporteVital.Fatiga;
import es.programahermes.SoporteVital.Hydratation;
import es.programahermes.SoporteVital.Oxygen;
import es.programahermes.SoporteVital.OxygenCommand;
import es.programahermes.SoporteVital.Residual;
import es.programahermes.Training.ETS;
import es.programahermes.Training.Resistencia;
import es.programahermes.Utilidades.Batteries;
import es.programahermes.Utilidades.Bulletproof;
import es.programahermes.Utilidades.FireExtinguisher;
import es.programahermes.Utilidades.GPS;
import es.programahermes.Utilidades.Miscelaneo;
import es.programahermes.Utilidades.Pernos;
import es.programahermes.Utilidades.Recipes;
import es.programahermes.WGRegions.WGFlags;

public class Main extends JavaPlugin{

	public Plugin plugin = this;
	
	public void onEnable() {

		Residual.residualUpdate(plugin);
		Hydratation.thirstUpdate(plugin);
		Oxygen.oxyenUpdate(plugin);
		Fatiga.waitFatigaCheck(plugin);
		getServer().getPluginManager().registerEvents(new Geologia(), this);
		getServer().getPluginManager().registerEvents(new Refuerzos(), this);
		getServer().getPluginManager().registerEvents(new Perforadora(), this);
		getServer().getPluginManager().registerEvents(new Batteries(), this);
		getServer().getPluginManager().registerEvents(new Biologia(), this);
		getServer().getPluginManager().registerEvents(new Estructural(), this);
		getServer().getPluginManager().registerEvents(new Quimica(), this);
		getServer().getPluginManager().registerEvents(new Tecnica(), this);
		getServer().getPluginManager().registerEvents(new Miscelaneo(), this);
		getServer().getPluginManager().registerEvents(new Hydratation(), this);
		getServer().getPluginManager().registerEvents(new Fatiga(), this);
		getServer().getPluginManager().registerEvents(new Pernos(), this);
		getServer().getPluginManager().registerEvents(new Resistencia(), this);
		getServer().getPluginManager().registerEvents(new Accuracy(), this);
		getServer().getPluginManager().registerEvents(new ETS(), this);
		getServer().getPluginManager().registerEvents(new FireExtinguisher(),
				this);
		getServer().getPluginManager().registerEvents(new Melee(), this);
		getServer().getPluginManager().registerEvents(new Bulletproof(), this);
		getServer().getPluginManager().registerEvents(new Prospectar(), this);
		getServer().getPluginManager().registerEvents(new Fracturas(), this);
		getServer().getPluginManager().registerEvents(new Septicemia(), this);
		getCommand("vendarse").setExecutor(new Septicemia());
		getCommand("subirnivel").setExecutor(new LevelUpCommand());
		getCommand("puntos").setExecutor(new PointsCommand());
		getCommand("stats").setExecutor(new Stats());
		getCommand("sethabilidad").setExecutor(new SetHability());
		getCommand("evacuar").setExecutor(new Residual());
		getCommand("csv").setExecutor(new VSC());
		getCommand("presurizar").setExecutor(new OxygenCommand());
		getCommand("visor").setExecutor(new Visor());
		getCommand("gps").setExecutor(new GPS());
		getCommand("entrenartiro").setExecutor(new Accuracy());
		getCommand("cuerpoacuerpo").setExecutor(new Melee());
		getCommand("emergency").setExecutor(new Miscelaneo());
		loadConfiguration();
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		MySQL.openConnection();
		getServer().addRecipe(Recipes.Web);
		WGFlags.getWGCustomFlags().addCustomFlag(WGFlags.presurizada);
		WGFlags.getWGCustomFlags().addCustomFlag(WGFlags.gold);
		WGFlags.getWGCustomFlags().addCustomFlag(WGFlags.iron);
		WGFlags.getWGCustomFlags().addCustomFlag(WGFlags.coal);
		WGFlags.getWGCustomFlags().addCustomFlag(WGFlags.lapis);
		WGFlags.getWGCustomFlags().addCustomFlag(WGFlags.diamond);
		WGFlags.getWGCustomFlags().addCustomFlag(WGFlags.lapis);
	}

	public void onDisable() {
		MySQL.closeConnection();
		getServer().clearRecipes();
	}

	public void loadConfiguration() {
		this.plugin.getConfig().options().copyDefaults(true);
		this.plugin.saveConfig();

	}	

}
