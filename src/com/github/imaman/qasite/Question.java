package com.github.imaman.qasite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Question {
	public final Date date;
	public final String userId;
	public final String body;
	private final List<String> topics = new ArrayList<String>();
	
	public Question(Date date, String userId, String body, String... topics) {
	  this.date = date;
	  this.userId = userId;
	  this.body = body;
	  this.topics.addAll(Arrays.asList(topics));
  }

	@Override
  public String toString() {
	  return "Question [date=" + date.getTime() + ", userId=" + userId + ", body=" + body + "]";
  }

	public List<String> topics() {
		return topics;
  }
}
