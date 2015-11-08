package com.jsservey.view.home.profile;

import java.util.Calendar;

import org.json.JSONObject;

import com.abx.jsservey.R;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SurveyCreationActivity extends Activity implements OnClickListener,ApiRequestListner {
	private Calendar calendar;
	private TextView dateView;
	private int year, month, day;
	private int dateViewId;
	private TextView selectedDate;
	private TextView selectedFromDate;
	private TextView selectedToDate;
	private Button setDateBtn, fromDateBtn, toDateBtn;
	private ToggleButton toggleButton1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.survey_creation_layout);
		calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		Bundle bundle= getIntent().getExtras();
		final String pf_id=bundle.getString("pf_id");
		initView();
		LinearLayout scheduleSurveyLayout = (LinearLayout) findViewById(R.id.schedule_survey_layout);
		final EditText survey_name_ed =(EditText)findViewById(R.id.survey_name_ed);
		findViewById(R.id.create_survey).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				RequestCreator requestCreator = new RequestCreator();
				String pf_name=survey_name_ed.getText().toString();
				new ApiRequester(SurveyCreationActivity.this, requestCreator.createSurvey("csfeedback", "123456", pf_name, pf_id, "thenga"), SurveyCreationActivity.this).execute("");
				//new DbInsertDbop(pf_name).execute("");
				
			}
		});
	}

	private void initView() {
		findViewById(R.id.home_pg_rl).setVisibility(View.GONE);
		final LinearLayout scheduleSurveyLayout = (LinearLayout) findViewById(R.id.schedule_survey_layout);
		CheckBox validUpto = (CheckBox) findViewById(R.id.valid_upto);
		CheckBox scheduleSurveyCheckbox = (CheckBox) findViewById(R.id.schedule_survey);
		CheckBox visibleSurveyCheckbox = (CheckBox) findViewById(R.id.visible_survey);
		final CheckBox selectSurveyCheckbox = (CheckBox) findViewById(R.id.selection_possible);
		selectedDate = (TextView) findViewById(R.id.selected_date);
		selectedFromDate = (TextView) findViewById(R.id.scheduled_from_date);
		selectedToDate = (TextView) findViewById(R.id.scheduled_to_date);
		setDateBtn = (Button) findViewById(R.id.set_date);
		fromDateBtn = (Button) findViewById(R.id.schedule_set);
		toDateBtn = (Button) findViewById(R.id.schedule_to_set);
		setDateBtn.setOnClickListener(this);
		fromDateBtn.setOnClickListener(this);
		toDateBtn.setOnClickListener(this);

		final LinearLayout datePickerLayout = (LinearLayout) findViewById(R.id.date_picker_layout);

		validUpto.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked == true) {
					datePickerLayout.setVisibility(View.VISIBLE);
				} else {
					datePickerLayout.setVisibility(View.GONE);
				}
			}
		});
		visibleSurveyCheckbox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked == true) {
							selectSurveyCheckbox.setVisibility(View.VISIBLE);
						} else {
							selectSurveyCheckbox.setVisibility(View.GONE);
						}
					}
				});
		scheduleSurveyCheckbox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						scheduleSurveyLayout.setVisibility(View.VISIBLE);
						if (isChecked == true) {
							scheduleSurveyLayout.setVisibility(View.VISIBLE);
						} else {
							scheduleSurveyLayout.setVisibility(View.GONE);
						}
					}
				});
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		//if (id == R.id.set_date) {
			return new DatePickerDialog(this, myDateListener, year, month, day);
		//}
		//return null;
	}

	private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker datePicker, int year, int month,
				int date) {
			showDate(year, month + 1, date);
		}
	};

	private void showDate(int year, int month, int day) {
		if (dateViewId == R.id.set_date) {
			selectedDate.setVisibility(View.VISIBLE);
			selectedDate.setText(new StringBuilder().append(day).append("/")
					.append(month).append("/").append(year));
		} else if (dateViewId == R.id.schedule_set) {
			selectedFromDate.setVisibility(View.VISIBLE);
			selectedFromDate.setText(new StringBuilder().append(day)
					.append("/").append(month).append("/").append(year));
		} else if (dateViewId == R.id.schedule_to_set) {
			selectedToDate.setVisibility(View.VISIBLE);
			selectedToDate.setText(new StringBuilder().append(day).append("/")
					.append(month).append("/").append(year));
		}
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.set_date) {
			dateViewId = R.id.set_date;
			showDialog(R.id.set_date);

		} else if (view.getId() == R.id.schedule_set) {
			dateViewId = R.id.schedule_set;
			showDialog(R.id.schedule_set);

		} else if (view.getId() == R.id.schedule_to_set) {
			dateViewId = R.id.schedule_to_set;
			showDialog(R.id.schedule_to_set);

		}

	}

	@Override
	public String onSuccess(JSONObject result) {
		findViewById(R.id.home_pg_rl).setVisibility(View.GONE);
		Toast.makeText(getApplicationContext(), "Survey created", 1000).show();
		onBackPressed();
		return null;
	}

	@Override
	public String onFailed() {
		findViewById(R.id.home_pg_rl).setVisibility(View.GONE);
		// TODO Auto-generated method stub
		onBackPressed();
		return null;
	}

	@Override
	public String onStarted() {
		findViewById(R.id.home_pg_rl).setVisibility(View.VISIBLE);
		// TODO Auto-generated method stub
		onBackPressed();
		return null;
	}

}
