package com.jsservey.view.home.profile;
import java.util.ArrayList;
import java.util.Calendar;

import com.abx.jsservey.R;
import com.jsservey.database.SQLiteHelper;
import com.jsservey.model.ProfileName;

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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileCreationActivity extends Activity implements OnClickListener{
	private DatePicker datePicker;
	private Calendar calendar;
	private TextView dateView;
	private int year, month, day;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_creation_layout);
		
		calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		initView();
	}


	private void initView() {
		dateView = (TextView) findViewById(R.id.selected_date);
		Button setDate = (Button) findViewById(R.id.set_date);
		CheckBox validUpto = (CheckBox) findViewById(R.id.valid_upto_checkbox);
		CheckBox childVisibleCheckbox = (CheckBox) findViewById(R.id.child_visible_checkBox);
		final CheckBox childSelectionCheckbox = (CheckBox) findViewById(R.id.child_selection_checkBox);
		final LinearLayout datePickerLayout = (LinearLayout) findViewById(R.id.date_picker_layout);
		findViewById(R.id.home_pg_rl).setVisibility(View.GONE);
		datePickerLayout.setVisibility(View.GONE);
		setDate.setOnClickListener(this);
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
		final EditText profile_name_ed =(EditText)findViewById(R.id.profile_name);
		findViewById(R.id.create_profile_bt).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				new DbInsertDbop(profile_name_ed.getText().toString()).execute("");
			}
		});
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
		dateView.setText(new StringBuilder().append(day).append("/")
				.append(month).append("/").append(year));
	}

	@Override
	public void onClick(View view) {
		if(view.getId() == R.id.set_date)
		showDialog(999);	
	}
	public class DbInsertDbop extends AsyncTask<String, Void, ArrayList<ProfileName>>{
		String prof_name;
		public DbInsertDbop(String prof_name) {
			this.prof_name=prof_name;
		}

		@Override
		protected ArrayList<ProfileName> doInBackground(String... arg0) {
			SQLiteHelper db = 	SQLiteHelper.getInstance(getApplicationContext());
			ProfileName profile= new ProfileName();
			
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
		protected void onPostExecute(ArrayList<ProfileName> result) {
			
			super.onPostExecute(result);
			findViewById(R.id.home_pg_rl).setVisibility(View.GONE);
			Toast.makeText(getApplicationContext(), "Profile created", 1000).show();
			onBackPressed();
		}
		
	}
}