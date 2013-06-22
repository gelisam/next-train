package com.gelisam.prochainpassage;

import java.io.IOException;
import java.util.ArrayList;

import com.gelisam.prochainpassage.CsvDocument.CsvRow;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.app.Activity;

public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener {
	private static final String LOG_TAG = "MainActivity";
	
	private TextView dataset_name_view;
	private ListView schedule_view;
	
	private RadioGroup radiobuttons_view;
	private RadioButton to_montreal_view;
	private RadioButton to_roxboro_view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dataset_name_view = (TextView) findViewById(R.id.dataset_name);
		schedule_view = (ListView) findViewById(R.id.schedule);
		
		radiobuttons_view = (RadioGroup) findViewById(R.id.radiobuttons);
		to_montreal_view = (RadioButton) findViewById(R.id.to_montreal);
		to_roxboro_view = (RadioButton) findViewById(R.id.to_roxboro);
		
		radiobuttons_view.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.to_montreal:
			fillSchedule();
			Log.d(LOG_TAG, "to montreal");
			break;
		case R.id.to_roxboro:
			Log.d(LOG_TAG, "to roxboro");
			break;
		}
	}
	
	
	private void fillSchedule() {
		try {
			CsvDocument routes = new CsvDocument(getAssets().open("routes.txt"));
			final int route_name = routes.getIndex("route_long_name");
			
			ArrayList<String> route_names = new ArrayList<String>();
			for(CsvRow row : routes) {
				route_names.add(row.getString(route_name));
			}
			schedule_view.setAdapter(new StringListAdapter(this, route_names, 1));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
