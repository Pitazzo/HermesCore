package es.programahermes;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import es.programahermes.Chat.ChannelSwitcher;
import es.programahermes.Chat.ChatListener;
import es.programahermes.Chat.GCommand;
import es.programahermes.Chat.HCommand;
import es.programahermes.Chat.JoinListener;
import es.programahermes.Chat.LanguageSwitcher;
import es.programahermes.Chat.Meeter;
import es.programahermes.Chat.OCommand;
import es.programahermes.Chat.SCommand;
import es.programahermes.Chat.TonoSwitcher;
import es.programahermes.Combat.Accuracy;
import es.programahermes.Combat.Melee;
import es.programahermes.Commands.LevelUpCommand;
import es.programahermes.Commands.PointsCommand;
import es.programahermes.Commands.SetHability;
import es.programahermes.Commands.Stats;
import es.programahermes.Commands.VSC;
import es.programahermes.Commands.Visor;
import es.programahermes.CustomEntities.CustomEntityType;
import es.programahermes.Energy.Batteries;
import es.programahermes.Energy.Crafts;
import es.programahermes.Energy.CustomTools;
import es.programahermes.Energy.EnergyListeners;
import es.programahermes.Geologia.Geologia;
import es.programahermes.Geologia.Perforadora;
import es.programahermes.Geologia.Prospectar;
import es.programahermes.Geologia.Refuerzos;
import es.programahermes.Habilidades.Biologia;
import es.programahermes.Habilidades.Estructural;
import es.programahermes.Habilidades.Quimica;
import es.programahermes.Health.Anemia;
import es.programahermes.Health.Diarrea;
import es.programahermes.Health.Fracturas;
import es.programahermes.Health.Septicemia;
import es.programahermes.PHDS.DeathTimer;
import es.programahermes.PHDS.Desmayo;
import es.programahermes.PHDS.Listeners;
import es.programahermes.Skins.SkinManager;
import es.programahermes.Skins.SkinReloadCommand;
import es.programahermes.SoporteVital.Fatiga;
import es.programahermes.SoporteVital.Hydratation;
import es.programahermes.SoporteVital.Oxygen;
import es.programahermes.SoporteVital.OxygenCommand;
import es.programahermes.SoporteVital.Residual;
import es.programahermes.Tecnica.CraftsTecnica;
import es.programahermes.Tecnica.Tecnica;
import es.programahermes.Training.ETS;
import es.programahermes.Training.Resistencia;
import es.programahermes.Utilidades.Bulletproof;
import es.programahermes.Utilidades.DataGetter;
import es.programahermes.Utilidades.FakeTable;
import es.programahermes.Utilidades.FireExtinguisher;
import es.programahermes.Utilidades.GPS;
import es.programahermes.Utilidades.Ingredients;
import es.programahermes.Utilidades.Miscelaneo;
import es.programahermes.Utilidades.Pernos;
import es.programahermes.Utilidades.PointsAdjust;
import es.programahermes.Utilidades.Poison;
import es.programahermes.Utilidades.Recipes;
import es.programahermes.Utilidades.TestCommand;
import es.programahermes.WGRegions.WGFlags;

public class Main extends JavaPlugin {

	public static Plugin plugin = null;
	public File jugadores;
	public static FileConfiguration JugadoresConfig;

	@SuppressWarnings("static-access")
	public void onEnable() {

		CustomTools.loadTools();
		CustomEntityType.registerEntities();
		this.plugin = this;
		Residual.residualUpdate(this);
		Hydratation.thirstUpdate(plugin);
		Oxygen.oxyenUpdate(plugin);
		Fatiga.waitFatigaCheck(plugin);
		Diarrea.diarreaUpdate(plugin);
		PointsAdjust.pointsAdjust(plugin);
		DeathTimer.limbo(plugin);
		Septicemia.sepsis(plugin);
		Desmayo.postDesmayo(plugin);
		loadJugadoresFile();
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
		getServer().getPluginManager().registerEvents(new Anemia(), this);
		getServer().getPluginManager().registerEvents(new Diarrea(), this);
		getServer().getPluginManager().registerEvents(new Poison(), this);
		getServer().getPluginManager().registerEvents(new FakeTable(), this);
		getServer().getPluginManager().registerEvents(new Listeners(), this);
		getServer().getPluginManager().registerEvents(new ChatListener(), this);
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getServer().getPluginManager().registerEvents(new Meeter(), this);
		getServer().getPluginManager().registerEvents(new EnergyListeners(),
				this);
		getServer().getPluginManager().registerEvents(new SkinManager(), this);
		getServer().getPluginManager().registerEvents(new Ingredients(), this);
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
		getCommand("data").setExecutor(new DataGetter());
		getCommand("s").setExecutor(new SCommand());
		getCommand("o").setExecutor(new OCommand());
		getCommand("g").setExecutor(new GCommand());
		getCommand("h").setExecutor(new HCommand());
		getCommand("idioma").setExecutor(new LanguageSwitcher());
		getCommand("canal").setExecutor(new ChannelSwitcher());
		getCommand("presentarse").setExecutor(new Meeter());
		getCommand("tono").setExecutor(new TonoSwitcher());
		getCommand("recargarskin").setExecutor(new SkinReloadCommand());
		getCommand("test").setExecutor(new TestCommand());
		loadConfiguration();
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		getServer().addRecipe(Recipes.O2);
		getServer().addRecipe(Recipes.reconstructor);
		Crafts.register();
		CraftsTecnica.registrarTecnina();
		WGFlags.getWGCustomFlags().addCustomFlag(WGFlags.presurizada);
		WGFlags.getWGCustomFlags().addCustomFlag(WGFlags.gold);
		WGFlags.getWGCustomFlags().addCustomFlag(WGFlags.iron);
		WGFlags.getWGCustomFlags().addCustomFlag(WGFlags.coal);
		WGFlags.getWGCustomFlags().addCustomFlag(WGFlags.lapis);
		WGFlags.getWGCustomFlags().addCustomFlag(WGFlags.diamond);
		WGFlags.getWGCustomFlags().addCustomFlag(WGFlags.lapis);
	}

	@SuppressWarnings("static-access")
	public void onDisable() {
		this.plugin = null;
		getServer().clearRecipes();
	}
	@SuppressWarnings("static-access")
	public void loadConfiguration() {
		this.plugin.getConfig().options().copyDefaults(true);
		this.plugin.saveConfig();
	}

	public void loadJugadoresFile() {
		jugadores = new File(getDataFolder(), "jugadores.yml");
		if (!jugadores.exists()) {
			try {
				jugadores.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		JugadoresConfig = YamlConfiguration.loadConfiguration(jugadores);
	}

}
