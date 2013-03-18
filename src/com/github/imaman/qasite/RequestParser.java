package com.github.imaman.qasite;

import javax.servlet.http.HttpServletRequest;

public class RequestParser {

	public String get(HttpServletRequest request, String name, String defaultValue) {
		String result = request.getParameter(name);
		if (result != null)
			return result;
		return defaultValue;
  }

	public String getLoggedInUser(HttpServletRequest request) {
		return request.getCookies()[0].getValue();
  }
}
