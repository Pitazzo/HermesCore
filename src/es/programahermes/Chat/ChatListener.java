package es.programahermes.Chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {

		Player sender = event.getPlayer();
		HermesChat.chat(sender, event.getMessage(),
				HermesChat.tono.get(sender), HermesChat.channel.get(sender),
				HermesChat.idioma.get(sender));
		System.out.println("["+HermesChat.channel.get(sender).toUpperCase()+"]"+sender.getName() + ": " + event.getMessage());
		event.setCancelled(true);
	}

}
