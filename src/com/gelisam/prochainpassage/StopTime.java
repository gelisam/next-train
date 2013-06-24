package com.gelisam.prochainpassage;

import java.util.Calendar;
import java.util.List;

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
	
	@Override
	public String toString() {
		return orig_string;
	}
	
	@Override
	public int compareTo(StopTime another) {
		return calendar.compareTo(another.calendar);
	}
	
	
	// null for forever
	private static boolean inBetween(StopTime min, Calendar calendar, StopTime max) {
		if (min != null && calendar.before(min.calendar)) return false;
		if (max != null && calendar.after(max.calendar)) return false;
		
		return true;
	}
	
	public static int nextStop(List<StopTime> stop_times) {
		Calendar now = Calendar.getInstance();
		
		StopTime prev_stop = null;
		for(int i=0; i<stop_times.size(); ++i) {
			StopTime next_stop = stop_times.get(i);
			if (inBetween(prev_stop, now, next_stop)) {
				return i;
			}
			
			prev_stop = next_stop;
		}
		
		return stop_times.size();
	}
}
