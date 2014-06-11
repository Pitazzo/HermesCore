package es.programahermes.Utilidades;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;

public class Bulletproof implements Listener{

	
	@EventHandler
	public void onDamage(WeaponDamageEntityEvent event){
		if(!event.isHeadshot()){
			Player victim = (Player) event.getVictim();
			if(event.getVictim() instanceof Player){
				if(victim.getInventory().getChestplate().getType().equals(Material.LEATHER_CHESTPLATE)){
					victim.setVelocity(victim.getLocation().getDirection().multiply(-0.15));
					victim.sendMessage(ChatColor.RED+"Notas un fuerte impacto en el pecho");
					event.setDamage(event.getDamage()*0.4);
				}
			}
		}
	}
}
