package es.programahermes.Chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {

		Player sender = event.getPlayer();

		if (!event.getMessage().contains("!")) {
			HermesChat.chat(sender, event.getMessage(),
					HermesChat.tono.get(sender),
					HermesChat.channel.get(sender),
					HermesChat.idioma.get(sender));
		} else {
			int tono = HermesChat.tono.get(sender);
			HermesChat.tono.put(sender, 20);

			HermesChat.chat(sender, event.getMessage(),
					HermesChat.tono.get(sender),
					HermesChat.channel.get(sender),
					HermesChat.idioma.get(sender));
			HermesChat.tono.put(sender, tono);

		}

		System.out.println("[" + HermesChat.channel.get(sender).toUpperCase()
				+ "]" + sender.getName() + ": " + event.getMessage());
		event.setCancelled(true);
	}

}
