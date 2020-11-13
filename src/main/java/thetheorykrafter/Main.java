package thetheorykrafter;

import java.io.File;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.channel.TextChannelEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;

public class Main {
	
	public static void main(String[] args) {
		
		FallbackLoggerConfiguration.setDebug(true);
		FallbackLoggerConfiguration.setTrace(true);
		
		DiscordApi api = new DiscordApiBuilder().setToken(args[0]).setAllIntents().login().join();
		
		api.addMessageCreateListener(event -> {	
			String[] message = event.getMessageContent().split(" ");
			if (message.length == 2) {
				String commande = message[0];
				String champion = message[1];
				if (commande.equals("!build")) {
					String channelName = event.getServerTextChannel().get().getName();
					if (channelName.equals("theoricraft")) {
						event.getChannel().sendMessage(JsonReader.getInstance().getEmbedChampion(champion));
					} else {
						event.getChannel().sendMessage("Tu ne peux pas utiliser cette commande sur ce channel, rend toi sur le channel \"theoricraft\" pour en bénéficier");
					}
				}
			}
		});
		
		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().equals("!importset")) {
				String channelName = event.getServerTextChannel().get().getName();
				if (channelName.equals("theoricraft")) {
					event.getChannel().sendMessage("Les étapes pour importer un set d'objet :", new File("C:/Users/Oydrey/SetObj/Images/etapes.png"));
				} else {
					event.getChannel().sendMessage("Tu ne peux pas utiliser cette commande sur ce channel, rend toi sur le channel \"theoricraft\" pour en bénéficier");
				}
			} 
		});
		
	}

}