package com.github.imaman.qasite;

import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FeedView {

	private Gson gson = new Gson();
	
	public void render(JsonElement data, PrintWriter writer) {
		JsonArray a = new JsonArray();
		for (JsonElement current : data.getAsJsonArray()) {
			JsonObject x = current.getAsJsonObject();
			a.add(gson.toJsonTree(x.get("body").getAsString() + " by " + x.get("by").getAsString()));			
		}
		writer.println(a.toString());	  
  }

}
