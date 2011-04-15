package in.contourz.android.daybook.activities;

import in.contourz.android.daybook.DayBookApplication;
import android.app.Activity;

public class DayBookActivity extends Activity {

	public DayBookActivity() {
		super();
	}

	protected DayBookApplication getStuffApplication() {
		return (DayBookApplication) getApplication();
	}

}
