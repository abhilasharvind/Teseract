package com.jsservey.view.home.profile;

import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.abx.jsservey.R;
import com.jsservey.model.SurveyEditDetailsBean;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;

public class SurveyEditDetails extends Activity implements OnClickListener,ApiRequestListner {
	private Calendar calendar;
	//private TextView dateView;
	private int year, month, day , hour,minute;
	private boolean is24HourView = false;
	private int dateViewId;
	private TextView selectedDate;
	private TextView selectedFromDate;
	private TextView selectedToDate;
	private Button setDateBtn, fromDateBtn, toDateBtn;
	String survey_id;
	private EditText survey_name_ed;
	private CheckBox validUpto;
	private CheckBox scheduleSurveyCheckbox;
	private CheckBox visibleSurveyCheckbox;
	private CheckBox selectSurveyCheckbox;
	//private ToggleButton toggleButton1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.survey_edit_layout);
		Log.e("abx", "inside seurvey edit ");
		findViewById(R.id.home_pg_rl).setVisibility(View.GONE);
		calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		Bundle bundle= getIntent().getExtras();
		survey_id=bundle.getString("survey_id");
		requestSurveyDetails(survey_id);
		initView();
		//LinearLayout scheduleSurveyLayout = (LinearLayout) findViewById(R.id.schedule_survey_layout);
		survey_name_ed =(EditText)findViewById(R.id.survey_name_ed);
		findViewById(R.id.create_survey).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				RequestCreator requestCreator = new RequestCreator(getApplicationContext());
				String pf_name=survey_name_ed.getText().toString();
				new ApiRequester(SurveyEditDetails.this, requestCreator.createSurvey(pf_name, survey_id, "thenga"), SurveyEditDetails.this).execute("");
				//new DbInsertDbop(pf_name).execute("");
				
			}
		});
	}

	private void requestSurveyDetails(String survey_id2) {
		RequestCreator requestCreator = new RequestCreator(getApplicationContext());
		new ApiRequester(SurveyEditDetails.this, requestCreator.editSurvey(survey_id2), SurveyEditDetails.this).execute("");		
	}

	private void initView() {
		
		final LinearLayout scheduleSurveyLayout = (LinearLayout) findViewById(R.id.schedule_survey_layout);
		validUpto = (CheckBox) findViewById(R.id.valid_upto);
		scheduleSurveyCheckbox = (CheckBox) findViewById(R.id.schedule_survey);
		visibleSurveyCheckbox = (CheckBox) findViewById(R.id.visible_survey);
		selectSurveyCheckbox = (CheckBox) findViewById(R.id.selection_possible);
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
		if (id == R.id.set_date) {
			return new DatePickerDialog(this, myDateListener, year, month, day);
		}else {
			return new TimePickerDialog(this, timePickerListener, hour, minute, is24HourView);
		}
		//return null;
	}

	private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker datePicker, int year, int month,
				int date) {
			showDate(year, month + 1, date);
		}
	};
	
	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hour, int minute) {
			showDate(hour,minute, 0);
			
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
		SurveyEditDetailsBean surveyEditDetailsBean = new SurveyEditDetailsBean();
		JSONObject data;
		try {
			data = result.getJSONObject("data");
			surveyEditDetailsBean.setSurvey_name(data.getString("survey_name"));
			surveyEditDetailsBean.setProfile_id(data.getString("profile_id"));
			surveyEditDetailsBean.setDescription(data.getString("description"));
			surveyEditDetailsBean.setSchedule_from(data.getString("schedule_from"));
			surveyEditDetailsBean.setSchedule_to(data.getString("schedule_to"));
			surveyEditDetailsBean.setThank_you_id(data.getString(data.getString("thank_you_id")));
			surveyEditDetailsBean.setSchedule_isactive(data.getString("schedule_isactive"));
			populateDetails(surveyEditDetailsBean);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		//onBackPressed();
		return null;
	}
	
	private void populateDetails(SurveyEditDetailsBean surveyEditDetailsBean) {
		survey_name_ed.setText(surveyEditDetailsBean.getSurvey_name());
		if (surveyEditDetailsBean.getSchedule_to().equalsIgnoreCase("1")){
			validUpto.setChecked(true);
			selectedDate.setVisibility(View.VISIBLE);
			selectedDate.setText(surveyEditDetailsBean.getSchedule_to());
		}else{
			validUpto.setChecked(false);
			selectedDate.setVisibility(View.GONE);
		}
		if(surveyEditDetailsBean.getSchedule_isactive().equalsIgnoreCase("1")){
			scheduleSurveyCheckbox.setChecked(true);
			selectedToDate.setVisibility(View.VISIBLE);
			selectedFromDate.setVisibility(View.VISIBLE);
			selectedToDate.setText(surveyEditDetailsBean.getSchedule_to());
			selectedFromDate.setText(surveyEditDetailsBean.getSchedule_from());
		}else{
			scheduleSurveyCheckbox.setChecked(false);
			selectedToDate.setVisibility(View.GONE);
			selectedFromDate.setVisibility(View.GONE);
		}
	}
	

}
