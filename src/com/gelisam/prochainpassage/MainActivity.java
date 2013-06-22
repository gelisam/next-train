package com.gelisam.prochainpassage;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.app.Activity;

public class MainActivity extends Activity {
	private TextView dataset_name_view;
	private ListView passage_list_view;
	private RadioButton to_montreal_view;
	private RadioButton to_roxboro_view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dataset_name_view = (TextView) findViewById(R.id.dataset_name);
		passage_list_view = (ListView) findViewById(R.id.passage_list);
		to_montreal_view = (RadioButton) findViewById(R.id.to_montreal);
		to_roxboro_view = (RadioButton) findViewById(R.id.to_roxboro);
	}

}
