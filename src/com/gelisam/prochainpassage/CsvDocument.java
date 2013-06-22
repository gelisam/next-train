package com.gelisam.prochainpassage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvDocument implements Iterable<CsvDocument.CsvRow>{
	private List<String[]> rows = new ArrayList<String[]>();
	
	
	public static class CsvRow {
		private String[] entries;
		
		public CsvRow(String[] entries) {
			this.entries = entries;
		}
		
		
		public String getString(int index) {
			return entries[index];
		}
		
		public int getInt(int index) {
			return Integer.parseInt(getString(index));
		}
		
		public double getDouble(int index) {
			return Double.parseDouble(getString(index));
		}
	}
	
	private class RowIterator implements Iterator<CsvRow> {
		public Iterator<String[]> raw_iterator;
		
		public RowIterator(Iterator<String[]> inner_iterator) {
			this.raw_iterator = inner_iterator;
		}
		

		@Override
		public boolean hasNext() {
			return raw_iterator.hasNext();
		}

		@Override
		public CsvRow next() {
			return new CsvRow(raw_iterator.next());
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	
	public CsvDocument(InputStream in) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		try {
			for (
					String line = reader.readLine();
					line != null;
					line = reader.readLine()
			) {
				String[] entries = line.split(",");
				rows.add(entries);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int getIndex(String column_name) {
		String[] column_names = rows.get(0);
		
		for(int i=0; i<column_names.length; ++i) {
			if (column_names[i].equals(column_name)) {
				return i;
			}
		}
		
		throw new RuntimeException("no such column: \"" + column_name + "\"");
	}
	
	@Override
	public Iterator<CsvRow> iterator() {
		Iterator<String[]> raw_iterator = rows.iterator();
		
		// skip the first row, which contains the column names
		raw_iterator.next();
		
		return new RowIterator(raw_iterator);
	}
}
