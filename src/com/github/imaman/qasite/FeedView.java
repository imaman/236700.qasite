package com.github.imaman.qasite;

import java.io.PrintWriter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FeedView {

	private Clock clock;
	
	public FeedView(Clock clock) {
	  this.clock = clock;
  }

	public void render(JsonElement data, PrintWriter writer) {
		for (JsonElement current : data.getAsJsonArray()) {
			JsonObject x = current.getAsJsonObject();
			long when = x.get("when").getAsLong();
			writer.print(x.get("body").getAsString() + " by " + x.get("by").getAsString() + ", " 
					+ clock.timeAgo(when) + ".");			
		}
  }

}
