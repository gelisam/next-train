package com.gelisam.prochainpassage;

import java.util.List;
import java.util.Map;

// describe all the stops for a particular (stop_id, day)
public class Schedule {
	public String service_name; // (ex: "monday", or "special")
	public List<String> service_ids;
	
	public Map<String, String> stop_times; // map from trip_id to departure_time
}
