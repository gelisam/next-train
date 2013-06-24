package com.gelisam.prochainpassage;

import java.util.List;

// describe all the stops for a particular (stop_id, day)
public class Schedule {
	public String service_name; // (ex: "monday", or "special")
	public List<String> service_ids;
	
	public List<String> stop_times;
}
