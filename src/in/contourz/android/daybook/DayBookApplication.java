package in.contourz.android.daybook;

import in.contourz.android.daybook.models.DayBookSQLiteOpenHelper;
import in.contourz.android.daybook.models.Entry;

import java.util.ArrayList;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DayBookApplication extends Application {

	private ArrayList<Entry> entries;
	private SQLiteDatabase database;

	@Override
	public void onCreate() {
		super.onCreate();
		DayBookSQLiteOpenHelper helper = new DayBookSQLiteOpenHelper(this);
		database = helper.getWritableDatabase();
		if (null == entries) {
			loadEntries();
		}
	}

	@Override
	public void onTerminate() {
		database.close();
		super.onTerminate();
	}

	private void loadEntries() {
		entries = new ArrayList<Entry>();
		Cursor entriesCursor = database.query(
				DayBookSQLiteOpenHelper.ENTRIES_TABLE, new String[] {
						DayBookSQLiteOpenHelper.ENTRY_ID,
						DayBookSQLiteOpenHelper.ENTRY_DESCRIPTION,
						DayBookSQLiteOpenHelper.ENTRY_AMOUNT,
						DayBookSQLiteOpenHelper.ENTRY_FLAG }, null, null, null,
				null, String.format("%s", "id"));
		entriesCursor.moveToFirst();
		Entry e;
		if (!entriesCursor.isAfterLast()) {
			do {
				int id = entriesCursor.getInt(0);
				String description = entriesCursor.getString(1);
				float amount = entriesCursor.getFloat(2);
				String flag = entriesCursor.getString(3);

				e = new Entry();
				e.setId(id);
				e.setDescription(description);
				e.setAmount(amount);
				e.setFlag(flag);
				entries.add(e);
			} while (entriesCursor.moveToNext());
		}
		entriesCursor.close();
	}

	public void addEntry(Entry e) {
		assert (null != e);

		ContentValues values = new ContentValues();
		values.put(DayBookSQLiteOpenHelper.ENTRY_DESCRIPTION,
				e.getDescription());
		values.put(DayBookSQLiteOpenHelper.ENTRY_AMOUNT, e.getAmount());
		values.put(DayBookSQLiteOpenHelper.ENTRY_FLAG, e.getFlag());
		e.setId(database.insert(DayBookSQLiteOpenHelper.ENTRIES_TABLE, null,
				values));
		entries.add(e);
	}

	public ArrayList<Entry> getEntries() {
		return entries;
	}

	public void setEntries(ArrayList<Entry> entries) {
		this.entries = entries;
	}

}
