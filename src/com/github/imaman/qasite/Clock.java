package com.github.imaman.qasite;

import java.util.Date;

public class Clock {

	public Date dateFromTimestamp(long timestamp) {
		return new Date(timestamp);
	}

	public String timeAgo(long timestamp) {
		return (new Date().getTime() - timestamp) / 1000 + " seconds ago"; 
  }
}