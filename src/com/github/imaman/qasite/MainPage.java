package com.github.imaman.qasite;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;

public class MainPage {

	private static final String DEFAULT_TAB = "interesting";
	Storage storage;
	private final RequestParser parser = new RequestParser();
	private final FeedView view;
	private final FeedGenerator feedGenerator;
	
	public MainPage(Storage storage) {
	  this.storage = storage;
	  this.feedGenerator = new FeedGenerator(storage);
	  this.view = new FeedView(storage.clock());
  }

	public void showFeedFor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userId = parser.getLoggedInUser(request);
		String tab = parser.get(request, "tab", DEFAULT_TAB);
		
		if (DEFAULT_TAB.equals(tab)) {		
  		User user = storage.lookupUser(userId);
  		int pageSize = user.pageSize();
  		
  		List<Question> favoriteQuestions = storage.lookupQuestions(pageSize, user.favoriteTopics());
  		List<Question> generalQuestions = storage.lookupQuestions(pageSize, null);
  		
  		JsonElement data = feedGenerator.generateFeed(user, favoriteQuestions, generalQuestions);
  		view.render(data, response.getWriter());
		}
		// else if (....) ... 
	}
}
