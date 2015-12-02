package com.jsservey.view.home;



import com.abx.jsservey.R;
import com.jsservey.utils.Utility;
import com.jsservey.view.BaseActivity;
import com.jsservey.view.home.profile.ProfileListMainActivity;
import com.jsservey.view.home.survey.StartSurveyActivity;
import com.jsservey.view.reportandabout.AboutActivity;
import com.jsservey.view.reportandabout.ReportActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class HomeActivity extends BaseActivity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("abx","in Main Activity");
		setContentView(R.layout.home_layout);
		//findViewById(R.id.home_pg_rl).setVisibility(View.GONE);
		setListners();
		
		//RequestCreator requestCreator = new RequestCreator(getApplicationContext());
		//new ApiRequester(this, requestCreator.profileFetch("csfeedback", "123456"), this).execute("");
		
		
		
	}

	private void setListners() {
		
		//handleHomeClick(this.findViewById(android.R.id.content));
		menuHandler(this.findViewById(android.R.id.content));
		findViewById(R.id.report_bt).setOnClickListener(this);
		findViewById(R.id.user_bt).setOnClickListener(this);
		findViewById(R.id.help_bt).setOnClickListener(this);
		findViewById(R.id.settings_bt).setOnClickListener(this);
		findViewById(R.id.about_bt).setOnClickListener(this);
		findViewById(R.id.profile_bt).setOnClickListener(this);
		findViewById(R.id.start_srvey_bt).setOnClickListener(this);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		int id= arg0.getId();
		switch(id){
		case R.id.report_bt:
			Utility.startActivity(HomeActivity.this, ReportActivity.class);
			finish();
			break;
		case R.id.user_bt:
			Utility.startActivity(HomeActivity.this, ReportActivity.class);
			finish();
			break;
		case R.id.help_bt:
			Utility.startActivity(HomeActivity.this, ReportActivity.class);
			finish();
			break;
		case R.id.settings_bt:
			Utility.startActivity(HomeActivity.this, ReportActivity.class);
			finish();
			break;
		case R.id.about_bt:
			Utility.startActivity(HomeActivity.this, AboutActivity.class);
			finish();
			break;
			
		case R.id.profile_bt:
			Utility.startActivity(HomeActivity.this, ProfileListMainActivity.class);
			break;
		case R.id.start_srvey_bt:
			Utility.startActivity(HomeActivity.this, StartSurveyActivity.class);
			break;
		
		
		}
		
	}

}