package com.jsservey.view.home.profile;

import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.abx.jsservey.R;
import com.jsservey.database.SQLiteHelper;
import com.jsservey.model.Profile;
import com.jsservey.model.ProfileDetails;
import com.jsservey.model.ProfileEditDetailsBean;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;

public class ProfileEditDetails extends Activity implements OnClickListener,ApiRequestListner{
	private DatePicker datePicker;
	private Calendar calendar;
	private TextView dateView;
	private int year, month, day;
	CheckBox childVisibleCheckbox;
	CheckBox validUpto;
	StringBuilder selectedDate;
	ProfileDetails profileDetails;
	EditText profile_name_ed;
	String profile_id;
	CheckBox childSelectionCheckbox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_creation_layout);
		
		calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		Bundle bundle= getIntent().getExtras();
		profile_id=bundle.getString("pf_id");
		requestProfileDetails(profile_id);
		initView();  
	}


	private void requestProfileDetails(String profile_id2) {
		Log.d("abx", "inside profilewditdetails profile id"+profile_id);
		RequestCreator requestCreator = new RequestCreator(getApplicationContext());
		new ApiRequester(ProfileEditDetails.this, requestCreator.editProfile(profile_id2), ProfileEditDetails.this).execute("");
	}


	private void initView() {
		dateView = (TextView) findViewById(R.id.selected_date);
		Button setDate = (Button) findViewById(R.id.set_date);
		 validUpto = (CheckBox) findViewById(R.id.valid_upto_checkbox);
		 childVisibleCheckbox = (CheckBox) findViewById(R.id.child_visible_checkBox);
		 childSelectionCheckbox = (CheckBox) findViewById(R.id.child_selection_checkBox);
		final LinearLayout datePickerLayout = (LinearLayout) findViewById(R.id.date_picker_layout);
		findViewById(R.id.home_pg_rl).setVisibility(View.GONE);
		datePickerLayout.setVisibility(View.GONE);
		setDate.setOnClickListener(this);
		
		//populateDetails(profileDetails);
		validUpto.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked == true){
					datePickerLayout.setVisibility(View.VISIBLE);	
				}else{
					datePickerLayout.setVisibility(View.GONE);
				}
			}
		});
		childVisibleCheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked == true){
					childSelectionCheckbox.setVisibility(View.VISIBLE);	
				}else{
					childSelectionCheckbox.setVisibility(View.GONE);
				}
			}
		});
		  profile_name_ed =(EditText)findViewById(R.id.profile_name);
		
		
		findViewById(R.id.create_profile_bt).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				RequestCreator requestCreator = new RequestCreator(getApplicationContext());
				String pf_name=profile_name_ed.getText().toString();
				ProfileDetails profileDetails = new ProfileDetails();
				profileDetails.setProfileName(profile_name_ed.getText().toString());
				profileDetails.setValidUpto(validUpto.isChecked());
				profileDetails.setChildVisible(childVisibleCheckbox.isChecked());
				profileDetails.setChildSelection(childSelectionCheckbox.isChecked());
				profileDetails.setSelectedDate(selectedDate.toString());
				new ApiRequester(ProfileEditDetails.this, requestCreator.createProfile(pf_name), ProfileEditDetails.this).execute("");
				//new DbInsertDbop(pf_name).execute("");
			}
		});
	}


	private void populateDetails(ProfileEditDetailsBean profileEditDetails) {
		profile_name_ed.setText(profileEditDetails.getProfile_name());
		if (profileEditDetails.getValid_upto_isactive().equalsIgnoreCase("1")){
			validUpto.setChecked(true);
			dateView.setVisibility(View.VISIBLE);
			dateView.setText(profileEditDetails.getValid_upto());
		} else {
			validUpto.setChecked(false);
			dateView.setVisibility(View.GONE);
		}
		

		if (profileEditDetails.getPermission_id().equalsIgnoreCase("1")){
			childVisibleCheckbox.setChecked(true);
		} else {
			childVisibleCheckbox.setChecked(true);
		}
		if (profileEditDetails.getMultiple_survey().equalsIgnoreCase("1")){
			childSelectionCheckbox.setChecked(true);
		} else {
			childSelectionCheckbox.setChecked(true);
		}

	}


	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		if (id == 999) {
			return new DatePickerDialog(this, myDateListener, year, month, day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker datePicker, int year, int month, int date) {
			showDate(year, month + 1, date);
		}
	};

	private void showDate(int year, int month, int day) {
		dateView.setVisibility(View.VISIBLE);
		selectedDate = new StringBuilder().append(day).append("/")
				.append(month).append("/").append(year);
		dateView.setText(selectedDate);
	}

	@Override
	public void onClick(View view) {
		if(view.getId() == R.id.set_date)
		showDialog(999);	
	}
	public class DbInsertDbop extends AsyncTask<String, Void, ArrayList<Profile>>{
		String prof_name;
		public DbInsertDbop(String prof_name) {
			this.prof_name=prof_name;
		}

		@Override
		protected ArrayList<Profile> doInBackground(String... arg0) {
			SQLiteHelper db = 	SQLiteHelper.getInstance(getApplicationContext());
			Profile profile= new Profile();
			
			profile.setProfile_id("123456");
			profile.setProfilr_name(""+prof_name);
			db.insertProfileNames(profile);
			
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			findViewById(R.id.home_pg_rl).setVisibility(View.VISIBLE);
		}
		@Override
		protected void onPostExecute(ArrayList<Profile> result) {
			
			super.onPostExecute(result);
			findViewById(R.id.home_pg_rl).setVisibility(View.GONE);
			Toast.makeText(getApplicationContext(), "Profile created", 1000).show();
			onBackPressed();
		}
		
	}
	@Override
	public String onSuccess(JSONObject result) {
		
		ProfileEditDetailsBean profileEditDetails = new ProfileEditDetailsBean();
		try {
			JSONObject data = result.getJSONObject("data");
			profileEditDetails.setProfile_name(data.getString("profile_name"));
			profileEditDetails.setPermission_id(data.getString("permission_id"));
			profileEditDetails.setValid_upto(data.getString("valid_upto"));
			profileEditDetails.setValid_upto_isactive(data.getString("valid_upto_isactive"));
			profileEditDetails.setUser_id(data.getString("user_id"));
			profileEditDetails.setMultiple_survey(data.getString("multiple_survey"));
			populateDetails(profileEditDetails);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public String onFailed() {
		return null;
	}


	@Override
	public String onStarted() {
		findViewById(R.id.home_pg_rl).setVisibility(View.VISIBLE);
		return null;
	}
}