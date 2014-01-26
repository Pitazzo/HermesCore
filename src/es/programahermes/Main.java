package es.programahermes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

import es.programahermes.Commands.LevelUpCommand;
import es.programahermes.Commands.PointsCommand;
import es.programahermes.Commands.SetHability;
import es.programahermes.Commands.Stats;
import es.programahermes.Habilidades.Biologia;
import es.programahermes.Habilidades.Estructural;
import es.programahermes.Habilidades.Geologia;
import es.programahermes.Habilidades.Quimica;
import es.programahermes.Habilidades.Tecnica;
import es.programahermes.Utilidades.EnergyCells;
import es.programahermes.Utilidades.Furnaces;
import es.programahermes.Utilidades.Miscelaneo;
import es.programahermes.Utilidades.Prospectar;

public class Main extends JavaPlugin implements CommandExecutor {

	public Plugin plugin = this;

	public void onEnable() {

		getServer().getPluginManager().registerEvents(new Geologia(), this);
		getServer().getPluginManager().registerEvents(new Prospectar(), this);
		getServer().getPluginManager().registerEvents(new EnergyCells(), this);
		getServer().getPluginManager().registerEvents(new Biologia(), this);
		getServer().getPluginManager().registerEvents(new Estructural(), this);
		getServer().getPluginManager().registerEvents(new Quimica(), this);
		getServer().getPluginManager().registerEvents(new Tecnica(), this);
		getServer().getPluginManager().registerEvents(new Furnaces(), this);
		getServer().getPluginManager().registerEvents(new Miscelaneo(), this);
		getCommand("subirnivel").setExecutor(new LevelUpCommand());
		getCommand("puntos").setExecutor(new PointsCommand());
		getCommand("stats").setExecutor(new Stats());
		getCommand("sethabilidad").setExecutor(new SetHability());
		MySQL.openConnection();

		ScoreboardManager manager = Bukkit.getScoreboardManager();
		org.bukkit.scoreboard.Scoreboard board = manager.getNewScoreboard();
		Objective obj = board.registerNewObjective("test", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(ChatColor.GREEN + "Hermes Core");
		Score score = obj.getScore(Bukkit.getOfflinePlayer("Puntos: "));
		for (Player online : Bukkit.getOnlinePlayers()) {
			online.setScoreboard(board);
			score.setScore((int) MySQL.getPoints(online));
		}
	}

	public void onDisable() {
		MySQL.closeConnection();
	}

}