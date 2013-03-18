package com.github.imaman.qasite;

import java.util.List;

public class Storage {

	private final Clock clock;
	
	public Storage(Clock clock) {
		this.clock = clock;
  }

	public User lookupUser(String userId) {
	  return null;
  }

	public List<Question> lookupQuestions(int howMany, List<String> topics) {
		// Fetch records from the Database.
		// Use clock for constructing the Date object of the new Question object 
	  return null;
  }

	public Clock clock() {
		return clock;
  }

}
