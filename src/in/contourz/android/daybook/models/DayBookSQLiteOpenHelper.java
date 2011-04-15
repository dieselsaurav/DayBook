package in.contourz.android.daybook.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DayBookSQLiteOpenHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "day_book_db.sqlite";
	public static final int VERSION = 1;
	public static final String ENTRIES_TABLE = "entries";
	public static final String ENTRY_ID = "id";
	public static final String ENTRY_DESCRIPTION = "description";
	public static final String ENTRY_AMOUNT = "amount";
	public static final String ENTRY_FLAG = "flag";

	public DayBookSQLiteOpenHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		dropAndCreate(db);
	}

	protected void dropAndCreate(SQLiteDatabase db) {
		db.execSQL("drop table if exists " + ENTRIES_TABLE + ";");
		createTable(db);
	}

	protected void createTable(SQLiteDatabase db) {
		db.execSQL("create table " + ENTRIES_TABLE + "(" + ENTRY_ID
				+ " integer primary key autoincrement not null,"
				+ ENTRY_DESCRIPTION + " text," + ENTRY_AMOUNT + " real,"
				+ ENTRY_FLAG + " text" + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		dropAndCreate(db);
	}

}
