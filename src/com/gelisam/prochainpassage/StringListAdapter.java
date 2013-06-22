package com.gelisam.prochainpassage;

import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

public class StringListAdapter implements Adapter {
	private Context context;
	private List<String> strings;

	public StringListAdapter(Context context, List<String> strings) {
		this.context = context;
		this.strings = strings;
	}
	
	
	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public int getItemViewType(int index) {
		return 0;
	}
	

	@Override
	public int getCount() {
		return strings.size();
	}

	@Override
	public Object getItem(int index) {
		return strings.get(index);
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		TextView item;
		
		if (convertView != null) {
			item = (TextView) convertView;
		} else {
			item = new TextView(context);
		}
		
		item.setText(strings.get(index));
		
		return item;
	}

	
	@Override
	public long getItemId(int index) {
		return index;
	}
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public boolean isEmpty() {
		return strings.isEmpty();
	}

	
	@Override
	public void registerDataSetObserver(DataSetObserver arg0) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver arg0) {
		throw new RuntimeException("not implemented");
	}

}
