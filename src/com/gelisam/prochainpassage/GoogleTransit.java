package com.gelisam.prochainpassage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.gelisam.prochainpassage.CsvDocument.CsvRow;

import android.content.Context;
import android.text.method.DateTimeKeyListener;

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
	
	
	public List<String> servicesForToday() {
		Calendar today = Calendar.getInstance();
		
		ArrayList<String> usual_services = new ArrayList<String>();
		{
			CsvDocument doc = document("calendar");
			final int service_id = doc.getIndex("service_id");
			final int start_date = doc.getIndex("start_date");
			final int end_date = doc.getIndex("end_date");
			
			final int day_of_week = doc.getIndex(days_of_the_week[today.get(Calendar.DAY_OF_WEEK)]);
			
			for(CsvRow service : doc) {
				if (service.getInt(day_of_week) == 1) {
					usual_services.add(service.getString(service_id));
				}
			}
		}
		
		return usual_services;
	}
}