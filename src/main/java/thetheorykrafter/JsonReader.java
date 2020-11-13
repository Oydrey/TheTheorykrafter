package thetheorykrafter;

import java.awt.Color;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonReader {
	
	private static final JsonReader instance = new JsonReader();
	private JSONObject jsonObject;
	
	public JsonReader() {
		try {
			initJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final JsonReader getInstance() {
		return instance;
	}
	
	public EmbedBuilder getEmbedChampion(String champion) {
		JSONArray allChampions = jsonObject.getJSONArray("champions");
		JSONObject championToSearch = null;
		for (int i = 0; i<allChampions.length(); i++) {
			if (((String) allChampions.getJSONObject(i).getString("champion")).equals(champion)) {
				championToSearch = allChampions.getJSONObject(i);
			}
		}
		
		EmbedBuilder embed = new EmbedBuilder()
				.setColor(Color.GREEN)
				.setTitle(champion);
		
		//marche pas
		String imageURI = championToSearch.getString("image");
		embed.setThumbnail(new File(imageURI));
		
		return embed;
	}
	
	private void initJson() throws Exception {
		File file = new File("C:/Users/Oydrey/SetObj/set_obj_discord.json");
		String content = FileUtils.readFileToString(file, "utf-8");
		
		this.jsonObject = new JSONObject(content);
	}
	
}
