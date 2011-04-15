package in.contourz.android.daybook.views;

import in.contourz.android.daybook.R;
import in.contourz.android.daybook.models.Entry;

import java.text.NumberFormat;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DayBookListItem extends RelativeLayout {

	private Entry entry;
	private TextView entryDecription;
	private TextView entryAmount;

	public DayBookListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		findViews();
	}

	private void findViews() {
		entryDecription = (TextView) findViewById(R.id.entry_description);
		entryAmount = (TextView) findViewById(R.id.entry_amount);
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntries(Entry entry) {
		this.entry = entry;
		if (entry.getFlag() != null) {
			if (entry.getFlag().toString().equals("Credit")) {
				this.setBackgroundResource(R.color.green);
			} else if (entry.getFlag().toString().equals("Debit")) {
				this.setBackgroundResource(R.color.red);
			}
		} else {
			this.setBackgroundResource(android.R.color.transparent);
		}
		entryDecription.setText(entry.getDescription().trim());
		entryAmount.setText(NumberFormat.getCurrencyInstance().format(
				(entry.getAmount())));
	}

}
