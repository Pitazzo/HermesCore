package es.programahermes.Utilidades;

import java.util.ArrayList;
import java.util.List;

import net.morematerials.MoreMaterials;
import net.morematerials.materials.MMCustomBlock;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.getspout.spout.block.CustomBlockStore;
import org.getspout.spout.block.CustomMaterial;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomBlock;

public class FlowerPower {

	public static MoreMaterials mm = (MoreMaterials) Bukkit.getServer()
			.getPluginManager().getPlugin("MoreMaterials");

	public static void flowers(Player player) {

		List<Block> list = new ArrayList<Block>();

		int radius = 10;
		Location loc = player.getLocation();
		World world = loc.getWorld();
		for (int x = -radius; x < radius; x++) {
			for (int y = -4; y < radius; y++) {
				for (int z = -radius; z < radius; z++) {
					Block block1 = world.getBlockAt(loc.getBlockX() + x,
							loc.getBlockY() + y, loc.getBlockZ() + z);
					if (block1.getRelative(BlockFace.UP).getType()
							.equals(Material.AIR)
							&& !block1.getRelative(BlockFace.DOWN).getType()
									.equals(Material.AIR)
							&& !block1.getType().equals(Material.AIR)) {
						list.add(block1.getRelative(BlockFace.UP));
					}

				}
			}
		}

		/*
		 * 2014-11-30 21:20:06 [SEVERE] Error occurred while enabling HermesCore v0.2 (Is it up to date?)
java.lang.NullPointerException
>       at org.getspout.spout.inventory.SimpleSpoutShapedRecipe.addToCraftingManager(SimpleSpoutShapedRecipe.java:68)
>       at es.programahermes.Tecnica.CraftsTecnica.registrarTecnina(CraftsTecnica.java:170)
>       at es.programahermes.Main.onEnable(Main.java:164)
>       at org.bukkit.plugin.java.JavaPlugin.setEnabled(JavaPlugin.java:217)
>       at org.bukkit.plugin.java.JavaPluginLoader.enablePlugin(JavaPluginLoader.java:457)
>       at org.bukkit.plugin.SimplePluginManager.enablePlugin(SimplePluginManager.java:381)
>       at org.bukkit.craftbukkit.v1_6_R3.CraftServer.loadPlugin(CraftServer.java:284)
>       at org.bukkit.craftbukkit.v1_6_R3.CraftServer.enablePlugins(CraftServer.java:266)
>       at net.minecraft.server.v1_6_R3.MinecraftServer.l(MinecraftServer.java:315)
>       at net.minecraft.server.v1_6_R3.MinecraftServer.f(MinecraftServer.java:292)
>       at net.minecraft.server.v1_6_R3.MinecraftServer.a(MinecraftServer.java:252)
>       at net.minecraft.server.v1_6_R3.DedicatedServer.init(DedicatedServer.java:152)
>       at net.minecraft.server.v1_6_R3.MinecraftServer.run(MinecraftServer.java:393)
>       at net.minecraft.server.v1_6_R3.ThreadServerApplication.run(SourceFile:583)
		 */
		for (Block block : list) {
			if (Math.random() * 100 > 95) {
				CustomBlockStore store = new CustomBlockStore();
				CustomMaterial cmaterial = org.getspout.spout.block.CustomMaterial.getMaterial(1, 0);
				store.setBlock(cmaterial, block.getX(), block.getY(), block.getZ());
			}
		}

	}

}
