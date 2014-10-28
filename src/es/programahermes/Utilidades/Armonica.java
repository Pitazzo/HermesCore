package es.programahermes.Utilidades;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.keyboard.Keyboard;


import es.programahermes.Main;
import es.programahermes.Crafteos.Ingredients;

public class Armonica implements Listener{

	@EventHandler
	public void onPress(KeyPressedEvent event){
		Player player = event.getPlayer();
		SpoutItemStack item = new SpoutItemStack(event.getPlayer().getItemInHand());
		if(item.getMaterial().equals(Ingredients.armonica)){
			if(player.hasPermission("hermescore.musica.armonica")){
				if(event.getKey().equals(Keyboard.KEY_1)){
					SpoutManager.getSoundManager().playGlobalCustomSoundEffect(
							Main.plugin,
							"http://programahermes.es/content/Hermes/otrs/sounds/armonia1.ogg",
							false, player.getLocation(), 10, 60);
				}
				if(event.getKey().equals(Keyboard.KEY_2)){
					SpoutManager.getSoundManager().playGlobalCustomSoundEffect(
							Main.plugin,
							"http://programahermes.es/content/Hermes/otrs/sounds/armonia2.ogg",
							false, player.getLocation(), 10, 60);
				}
				if(event.getKey().equals(Keyboard.KEY_3)){
					SpoutManager.getSoundManager().playGlobalCustomSoundEffect(
							Main.plugin,
							"http://programahermes.es/content/Hermes/otrs/sounds/armonia3.ogg",
							false, player.getLocation(), 10, 60);
				}
				if(event.getKey().equals(Keyboard.KEY_4)){
					SpoutManager.getSoundManager().playGlobalCustomSoundEffect(
							Main.plugin,
							"http://programahermes.es/content/Hermes/otrs/sounds/armonia4.ogg",
							false, player.getLocation(), 10, 60);
				}
				if(event.getKey().equals(Keyboard.KEY_5)){
					SpoutManager.getSoundManager().playGlobalCustomSoundEffect(
							Main.plugin,
							"http://programahermes.es/content/Hermes/otrs/sounds/armonia5.ogg",
							false, player.getLocation(), 10, 60);
				}
				if(event.getKey().equals(Keyboard.KEY_6)){
					SpoutManager.getSoundManager().playGlobalCustomSoundEffect(
							Main.plugin,
							"http://programahermes.es/content/Hermes/otrs/sounds/armonia6.ogg",
							false, player.getLocation(), 10, 60);
				}
			}else{
				//sonido horrible + mensaje
			}
			
		}
	}
	
}
