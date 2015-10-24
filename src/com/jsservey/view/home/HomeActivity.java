package com.jsservey.view.home;



import com.abx.jsservey.R;
import com.jsservey.utils.Utility;
import com.jsservey.view.BaseActivity;
import com.jsservey.view.reportandabout.AboutActivity;
import com.jsservey.view.reportandabout.ReportActivity;

import android.app.Activity;
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
		//handleHomeClick(this.findViewById(android.R.id.content));
		menuHandler(this.findViewById(android.R.id.content));
		
		findViewById(R.id.report_bt).setOnClickListener(this);
		findViewById(R.id.user_bt).setOnClickListener(this);
		findViewById(R.id.help_bt).setOnClickListener(this);
		findViewById(R.id.settings_bt).setOnClickListener(this);
		findViewById(R.id.about_bt).setOnClickListener(this);
		findViewById(R.id.profile_bt).setOnClickListener(this);
		
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
			Utility.startActivity(HomeActivity.this, ProfileMainActivity.class);
			break;
		
		
		
		}
		
	}
}
