package es.programahermes.NPC;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class AlienMob {

	public static ArrayList<Location> spawners = new ArrayList<Location>();

	public void spawners() {
		for (Location loc : spawners) {
			for (Entity entity : loc.getChunk().getEntities()) {
				if(entity.hasMetadata("NPC")){
					int amount = 0;
					amount++;
					if (amount <= 10) {
						Citizens.spawnNPC("Alien", loc,
								"http://www.minecraftskins.com/newuploaded_skins/skin_20140910192038177577.png");
					}
				}
				
			}
		}
	}

}
