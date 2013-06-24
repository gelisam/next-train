package com.gelisam.prochainpassage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import com.gelisam.prochainpassage.CsvDocument.CsvRow;

import android.content.Context;

public class GoogleTransit {
	private static final String[] days_of_the_week = new String[] {"", "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
	private Context context;
	
	public GoogleTransit(Context context) {
		this.context = context;
	}
	
	
	private CsvDocument document(String document_basename) {
		try {
			return new CsvDocument(context.getAssets().open(document_basename + ".txt"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	// a version of today which can be equal to the output of parseCalendar
	private Calendar now() {
		Calendar calendar = Calendar.getInstance();
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		// clear the time of day, only keep the date
		calendar.clear();
		calendar.set(year, month, day);
		
		return calendar;
	}
	
	private Calendar parseCalendar(String YYYYMMDD) {
		Calendar calendar = Calendar.getInstance();
		
		String YYYY = YYYYMMDD.substring(0, 4);
		String   MM = YYYYMMDD.substring(4, 6);
		String   DD = YYYYMMDD.substring(6, 8);
		
		int year = Integer.parseInt(YYYY);
		int month = Integer.parseInt(MM);
		int day = Integer.parseInt(DD);
		
		--month; // Java's months start at zero
		
		calendar.clear();
		calendar.set(year, month, day);
		
		return calendar;
	}
	
	public Schedule scheduleForToday(String stop_id) {
		Schedule schedule = new Schedule();
		Calendar today = now();
		
		ArrayList<String> service_ids = new ArrayList<String>();
		{
			CsvDocument doc = document("calendar");
			final int service_id = doc.getIndex("service_id");
			final int start_date = doc.getIndex("start_date");
			final int end_date = doc.getIndex("end_date");
			
			String day_string = days_of_the_week[today.get(Calendar.DAY_OF_WEEK)];
			final int day_of_week = doc.getIndex(day_string);
			schedule.service_name = day_string;
			
			for(CsvRow service : doc) {
				if (service.getInt(day_of_week) == 1
						&& today.compareTo(parseCalendar(service.getString(start_date))) >= 0
						&& today.compareTo(parseCalendar(service.getString(end_date))) <= 0
				) {
					service_ids.add(service.getString(service_id));
				}
			}
		}
		
		// apply exceptions if today is a holiday
		{
			CsvDocument doc = document("calendar_dates");
			final int service_id = doc.getIndex("service_id");
			final int date = doc.getIndex("date");
			final int exception_type = doc.getIndex("exception_type");
			final int ADD = 1;
			final int REMOVE = 2;
			
			for(CsvRow exception : doc) {
				if (today.equals(parseCalendar(exception.getString(date)))) {
					schedule.service_name = "special";
					switch (exception.getInt(exception_type)) {
						case ADD:
							service_ids.add(exception.getString(service_id));
							break;
						case REMOVE:
							service_ids.remove(exception.getString(service_id));
							break;
					}
				}
			}
		}
		
		schedule.service_ids = service_ids;
		return schedule;
	}
}