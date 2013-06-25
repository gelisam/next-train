package com.gelisam.prochainpassage;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.app.Activity;

public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener {
	private GoogleTransit googleTransit;
	
	private TextView dataset_name_view;
	private ListView schedule_view;
	private RadioGroup radiobuttons_view;
	
	private String stop_name;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		googleTransit = new GoogleTransit(this);
		
		dataset_name_view = (TextView) findViewById(R.id.dataset_name);
		schedule_view = (ListView) findViewById(R.id.schedule);
		
		radiobuttons_view = (RadioGroup) findViewById(R.id.radiobuttons);
		radiobuttons_view.setOnCheckedChangeListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		updateSchedule();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.to_montreal:
			setStopName("ROX1D");
			break;
		case R.id.to_roxboro:
			setStopName("MTL5B");
			break;
		}
	}
	
	
	private void setStopName(String stop_name) {
		this.stop_name = stop_name;
		updateSchedule();
	}
	
	private void updateSchedule() {
		Schedule schedule = googleTransit.scheduleForToday(stop_name);
		
		int next_stop = StopTime.nextStop(schedule.stop_times);
		
		dataset_name_view.setText(schedule.service_name + " schedule");
		schedule_view.setAdapter(new StringListAdapter<StopTime>(this, schedule.stop_times, next_stop));
		schedule_view.setSelection(next_stop);
	}

}
