package in.contourz.android.daybook.activities;

import in.contourz.android.daybook.DayBookApplication;
import in.contourz.android.daybook.R;
import in.contourz.android.daybook.adapters.EntryListAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ListBookActivity extends ListActivity {
	
	private DayBookApplication app;
	private EntryListAdapter adapter;
	private Button addButton;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.list_book);
        setUpViews();
        app = (DayBookApplication) getApplication();
        adapter = new EntryListAdapter(this, app.getEntries());
        setListAdapter(adapter);
    }

	@Override
	protected void onResume() {
		super.onResume();
		adapter.forceReload();
	}

	private void setUpViews() {
		addButton = (Button) findViewById(R.id.add_entry);
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ListBookActivity.this, AddEntryActivity.class);
				startActivity(intent);
			}
		});
	}
}