package thetheorykrafter;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;

public class Main {
	
	public static void main(String[] args) {
		
		FallbackLoggerConfiguration.setDebug(true);
		FallbackLoggerConfiguration.setTrace(true);
		
		DiscordApi api = new DiscordApiBuilder().setToken(args[0]).setAllIntents().login().join();
		
		api.addMessageCreateListener(event -> {
			if (event.getMessage().getContent().equals("test")) {
				EmbedBuilder embed = JsonReader.getInstance().getEmbedChampion("Twitch");
				event.getChannel().sendMessage(embed);				
			}
		});
		
	}

}