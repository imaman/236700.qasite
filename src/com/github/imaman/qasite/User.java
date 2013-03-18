package com.github.imaman.qasite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.JsonElement;

public class User {

	private final List<String> topics = new ArrayList<String>();
	private final int pageSize;
	private final String name;
	
	public User(int pageSize, String name, Collection<String> topics) {
	  this.pageSize = pageSize;
	  if (topics != null)
	  	this.topics.addAll(topics);
	  
	  this.name = name;
  }

	public int pageSize() {
	  return pageSize;
  }

	public List<String> favoriteTopics() {
	  return topics;
  }
	
	public String name() {
		return name;
	}

	public JsonElement generateFeed(FeedGenerator feedGenerator, Storage storage) {
		List<Question> favoriteQuestions = storage.lookupQuestions(pageSize, favoriteTopics());
		List<Question> generalQuestions = storage.lookupQuestions(pageSize, null);
		
		return feedGenerator.generateFeed(topics, favoriteQuestions, generalQuestions);
  }
}
