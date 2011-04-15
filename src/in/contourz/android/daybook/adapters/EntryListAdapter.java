package in.contourz.android.daybook.adapters;

import in.contourz.android.daybook.R;
import in.contourz.android.daybook.models.Entry;
import in.contourz.android.daybook.views.DayBookListItem;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class EntryListAdapter extends ArrayAdapter<Entry> {

	private Context context;

	public EntryListAdapter(Context context, ArrayList<Entry> entries) {
		super(context, android.R.layout.simple_list_item_1, entries);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DayBookListItem v;
		if (null == convertView) {
			v = (DayBookListItem) View.inflate(context,
					R.layout.day_book_list_item, null);
		} else {
			v = (DayBookListItem) convertView;
		}
		v.setEntries((Entry) getItem(position));
		return v;
	}

	public void forceReload() {
		notifyDataSetChanged();
	}

}
