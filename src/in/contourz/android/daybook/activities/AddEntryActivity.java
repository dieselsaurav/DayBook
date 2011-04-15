package in.contourz.android.daybook.activities;


import in.contourz.android.daybook.R;
import in.contourz.android.daybook.models.Entry;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddEntryActivity extends DayBookActivity {

	private EditText entryDescriptionEditText;
	private Button addButton;
	private Button cancelButton;
	private boolean changesPending;
	private AlertDialog unsavedChangesDialog;
	private EditText entryAmountEditText;
	private Spinner entryFlagSpinner;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_entry);
		setUpViews();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	protected void addEntry() {
		String entryDescription = entryDescriptionEditText.getText().toString();
		float entryAmount = Float.parseFloat(entryAmountEditText.getText().toString());
		String entryFlag = entryFlagSpinner.getSelectedItem().toString();
		Entry e = new Entry();
		e.setDescription(entryDescription);
		e.setAmount(entryAmount);
		e.setFlag(entryFlag);
		getStuffApplication().addEntry(e);
		finish();
	}

	protected void cancel() {
		if (changesPending) {
			unsavedChangesDialog = new AlertDialog.Builder(this)
					.setTitle(R.string.unsaved_changes_title)
					.setMessage(R.string.unsaved_changes_message)
					.setPositiveButton(R.string.add_entry,
							new AlertDialog.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									addEntry();
								}
							})
					.setNeutralButton(R.string.discard,
							new AlertDialog.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int which) {
									finish();
								}
							})
					.setNegativeButton(android.R.string.cancel,
							new AlertDialog.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									unsavedChangesDialog.cancel();
								}

							}).create();
			unsavedChangesDialog.show();
		} else {
			finish();
		}
	}

	private void setUpViews() {
		entryDescriptionEditText = (EditText) findViewById(R.id.entry_description);
		entryAmountEditText = (EditText) findViewById(R.id.entry_amount);
		entryFlagSpinner = (Spinner) findViewById(R.id.entry_flag);
		addButton = (Button) findViewById(R.id.add_button);
		cancelButton = (Button) findViewById(R.id.cancel_button);

		entryDescriptionEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				changesPending = true;
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			@Override
			public void afterTextChanged(Editable arg0) {
			}
		});

		entryAmountEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				changesPending = true;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter
				.createFromResource(this, R.array.entry_flag_array,
						android.R.layout.simple_spinner_item);
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		entryFlagSpinner.setAdapter(spinnerAdapter);

		addButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				addEntry();
			}
		});

		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				cancel();
			}
		});

	}

}
