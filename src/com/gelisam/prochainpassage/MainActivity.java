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
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.to_montreal:
			fillSchedule("ROX1D");
			break;
		case R.id.to_roxboro:
			fillSchedule("ROX1B");
			break;
		}
	}
	
	
	static int ii=0;
	private void fillSchedule(String stop_id) {
		Schedule schedule = googleTransit.scheduleForToday(stop_id);
		
		dataset_name_view.setText(schedule.service_name + " schedule");
		schedule_view.setAdapter(new StringListAdapter<StopTime>(this, schedule.stop_times, ii));
		schedule_view.setSelection(ii);
		++ii;
	}

}
