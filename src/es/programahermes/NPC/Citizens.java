package es.programahermes.NPC;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

public class Citizens {

	static NPCRegistry registry = CitizensAPI.getNPCRegistry();

	public static void spawnNPC(String name, Location loc, String skin) {
		NPC npc = registry.createNPC(EntityType.PLAYER, name);
		npc.spawn(loc);
		try {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
					"/npcskin " + npc.getId() + "" + skin);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}