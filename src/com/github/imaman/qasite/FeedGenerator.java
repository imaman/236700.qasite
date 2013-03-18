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
	
	public JsonElement generateFeed(User user,
      List<Question> favoriteQuestions, List<Question> generalQuestions) {
		List<Question> selected = new ArrayList<Question>();
		for (Question q : favoriteQuestions) 
			selected.add(q);
		
		for (Question q : generalQuestions) {
			double score = computeInterestScore(user, q);
			if (score > 0.6) 
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
		return o;
  }
	
	

	private double computeInterestScore(User user, Question q) {
		Set<String> a = new HashSet<String>(q.topics());
		a.retainAll(user.favoriteTopics());
		return 1.0 * a.size() / q.topics().size();
  }
}
