package com.github.imaman.qasite;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class MainPage_Test {
	
	private static final long NOW = 1000 * 24 * 60 * 60;
	
	private static class CustomClock extends Clock {

		private final long now;
		
		public CustomClock(long now) {
	    this.now = now;
    }

		@Override
    public String timeAgo(long timestamp) {
	    return (now - timestamp) / 1000 + " seconds ago";
    }		
	}
	
	@Test
	public void f() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(response.getWriter()).thenReturn(pw);

		Cookie cookie = mock(Cookie.class);
		when(cookie.getValue()).thenReturn("u1");
		Cookie[] cookies = new Cookie[] { cookie };
		when(request.getCookies()).thenReturn(cookies);
		
		
		
		
		Storage storage = mock(Storage.class);
		when(storage.clock()).thenReturn(new CustomClock(NOW));
		List<String> topics = Arrays.asList("T1", "T2", "T5", "T6");
		when(storage.lookupUser("u1")).thenReturn(new User(10, "N1", topics));
		when(storage.lookupUser("u10")).thenReturn(new User(10, "N10", null));
		when(storage.lookupUser("u11")).thenReturn(new User(10, "N11", null));
		when(storage.lookupUser("u12")).thenReturn(new User(10, "N12", null));
		when(storage.lookupUser("u20")).thenReturn(new User(10, "N20", null));
		when(storage.lookupUser("u21")).thenReturn(new User(10, "N21", null));
		when(storage.lookupUser("u22")).thenReturn(new User(10, "N22", null));
		
		List<Question> favoriteQuestions = new ArrayList<Question>();

		
		favoriteQuestions.add(new Question(ago(70), "u10", "B10"));
		favoriteQuestions.add(new Question(ago(60), "u11", "B11"));
		favoriteQuestions.add(new Question(ago(50), "u12", "B12"));
		when(storage.lookupQuestions(10, topics)).thenReturn(favoriteQuestions);
		
		List<Question> generalQuestions = new ArrayList<Question>();
		generalQuestions.add(new Question(ago(69), "u20", "B20", "T1", "T2", "T3"));
		generalQuestions.add(new Question(ago(68), "u21", "B21", "T1", "T2", "T3", "T4"));
		generalQuestions.add(new Question(ago(67), "u22", "B22", "T4", "T5", "T6"));
		when(storage.lookupQuestions(10, null)).thenReturn(generalQuestions);
				
		MainPage mainPage = new MainPage(storage);
		mainPage.showFeedFor(request, response);
		
		pw.close();
		assertEquals(
				"B12 by N12, 50 seconds ago." + 
				"B11 by N11, 60 seconds ago." + 
				"B22 by N22, 67 seconds ago." + 
				"B20 by N20, 69 seconds ago." + 
				"B10 by N10, 70 seconds ago.",
				sw.toString().trim());
		
	}

	private Date ago(long seconds) {
	  return new Date(NOW - seconds * 1000);
  }

}
