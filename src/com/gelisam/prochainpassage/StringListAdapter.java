package com.gelisam.prochainpassage;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class StringListAdapter extends ArrayAdapter<String> {
	private Context context;
	private int selectedIndex;
	
	public StringListAdapter(Context context, List<String> strings, int selectedIndex) {
		super(context, R.layout.schedule_item, strings);
		
		this.context = context;
		this.selectedIndex = selectedIndex;
	}
	
	public View getView (int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		
		if (position == selectedIndex) {
			view.setBackgroundColor(resolveColor(R.color.pressed_color));
		} else {
			view.setBackgroundColor(resolveColor(R.color.default_color));
		}
		
		return view;
	}
	
	private int resolveColor(int id) {
		return context.getResources().getColor(id);
	}
}
