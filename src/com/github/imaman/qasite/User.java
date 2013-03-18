package com.github.imaman.qasite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

}
