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
		JSONObject championToSearch = getChampion(champion);
		
		if (championToSearch != null) {
			EmbedBuilder embed = new EmbedBuilder()
					.setColor(Color.GREEN)
					.setTitle(champion)
					.setDescription("Clique sur le nom du champion pour accéder au lien de téléchargement afin d'importer le set d'objet. \n Tu ne sais pas comment faire ? Tape !importset pour voir les étapes !");
			
			String imageURI = championToSearch.getString("image");
			embed.setThumbnail(new File(imageURI));
			
			embed.addField("Early :", listItems(championToSearch.getJSONArray("Early")));
			embed.addField("Core", listItems(championToSearch.getJSONArray("Core")));
			embed.addField("Offensif", listItems(championToSearch.getJSONArray("Offensif")));
			embed.addField("Défensif", listItems(championToSearch.getJSONArray("Défensif")));
			
			embed.setUrl(championToSearch.getString("Importer"));
			
			return embed;
		}
		
		throw new NullPointerException();
	}
	
	private JSONObject getChampion(String champion) {
		JSONArray allChampions = jsonObject.getJSONArray("champions");
		JSONObject championToSearch = null;
		for (int i = 0; i<allChampions.length(); i++) {
			if (((String) allChampions.getJSONObject(i).getString("champion")).equals(champion)) {
				championToSearch = allChampions.getJSONObject(i);
			}
		}
		
		return championToSearch;
	}
	
	private String listItems(JSONArray json) {
		String listItems = "";
		
		for (int i = 0; i < json.length(); i++) {
			listItems+=json.getString(i) + "\n";
		}
		
		return listItems;
	}
	
	private void initJson() throws Exception {
		File file = new File("C:/Users/Oydrey/SetObj/set_obj_discord.json");
		String content = FileUtils.readFileToString(file, "utf-8");
		
		this.jsonObject = new JSONObject(content);
	}
	
}
