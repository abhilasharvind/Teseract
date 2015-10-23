package com.jsservey.view.login;

import org.json.JSONObject;

import com.abx.jsservey.R;
import com.jsservey.utils.Utility;
import com.jsservey.view.BaseActivity;
import com.jsservey.view.home.HomeActivity;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;

import android.os.Bundle;

public class SplashActivity extends BaseActivity implements ApiRequestListner{	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_layout);
		RequestCreator requestCreator = new RequestCreator();
		new ApiRequester(this,requestCreator.initLoad( "deviceId"),this).execute("");
		
		
	}

	@Override
	public String onSuccess(JSONObject result) {
		RequestCreator requestCreator = new RequestCreator();
		new ApiRequester(this,requestCreator.userLoad("dbname", "deviceId"),new ApiRequestListner() {
			
			@Override
			public String onSuccess(JSONObject result) {
				Utility.startActivity(SplashActivity.this, HomeActivity.class);				
				finish();
				return null;
			}
			
			@Override
			public String onStarted() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String onFailed() {
				Utility.startActivity(SplashActivity.this, LoginActivity.class);				
				finish();
				return null;
			}
		}).execute("");
		return null;
	}

	@Override
	public String onFailed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String onStarted() {
		// TODO Auto-generated method stub
		return null;
	}

}
