package com.gelisam.prochainpassage;

import java.util.Calendar;

public class StopTime implements Comparable<StopTime> {
	private Calendar calendar;
	private String orig_string;
	
	public StopTime(Calendar day, String time) {
		String[] time_fragments = time.split(":");
		int hour = Integer.parseInt(time_fragments[0]);
		int minute = Integer.parseInt(time_fragments[1]);
		int second = Integer.parseInt(time_fragments[2]);
		
		calendar = (Calendar) day.clone();
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		
		orig_string = time;
	}
	
	// null for forever
	public boolean inBetween(Calendar min, Calendar max) {
		if (min != null && calendar.before(min)) return false;
		if (max != null && calendar.after(max)) return false;
		
		return true;
	}
	
	
	@Override
	public String toString() {
		return orig_string;
	}
	
	@Override
	public int compareTo(StopTime another) {
		return calendar.compareTo(another.calendar);
	}
}
