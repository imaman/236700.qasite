package com.github.imaman.qasite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FeedGenerator {
	
	private Storage storage;
	
	public FeedGenerator(Storage storage) {
		this.storage = storage;
	}
	
	public JsonElement generateFeed(Predicate selector,
      List<Question> favoriteQuestions, List<Question> generalQuestions) {
		List<Question> selected = new ArrayList<Question>();
		for (Question q : favoriteQuestions) 
			selected.add(q);
		
		for (Question q : generalQuestions) {			
			if (selector.isSelected(q)) 
				selected.add(q);
		}
		
		Collections.sort(selected, new Comparator<Question>() {
			@Override
      public int compare(Question o1, Question o2) {
				return o2.date.compareTo(o1.date);
      }
		});
		
		JsonArray result = new JsonArray();
		for (Question q : selected) {
			result.add(toJson(q));			
		}
		
		return result;
  }

	private JsonObject toJson(Question q) {
		JsonObject o = new JsonObject();
		o.addProperty("body", q.body);
		User u = storage.lookupUser(q.userId);
		if (u == null)
			throw new RuntimeException("User '" + q.userId + "' not found");
		o.addProperty("by", u.name());
		o.addProperty("when", q.date.getTime());
		return o;
  }
}
