package com.gelisam.prochainpassage;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class StringListAdapter extends ArrayAdapter<String> {
	public StringListAdapter(Context context, List<String> strings) {
		super(context, R.layout.schedule_item, strings);
	}
}
