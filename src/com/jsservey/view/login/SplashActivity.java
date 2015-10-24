package com.jsservey.view.login;

import org.json.JSONException;
import org.json.JSONObject;

import com.abx.jsservey.R;
import com.jsservey.utils.Utility;
import com.jsservey.view.BaseActivity;
import com.jsservey.view.home.HomeActivity;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;

import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends BaseActivity implements ApiRequestListner{	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_layout);
		RequestCreator requestCreator = new RequestCreator();
		Log.d("abx", "SplashActivity");
		new ApiRequester(this,requestCreator.initLoad( "deviceId"),this).execute("");
		
		
	}

	@Override
	public String onSuccess(JSONObject result) {
		if (result.has("db_name")) {
			try {
				String dbname=result.getString("db_name");
				if(dbname.equals("0")){
					Utility.startActivity(SplashActivity.this, DeviceCreationActivity.class);
					finish();
				}else{
					RequestCreator requestCreator = new RequestCreator();
					new ApiRequester(this,requestCreator.userLoad("dbname", "deviceId"),new ApiRequestListner() {
						
						@Override
						public String onSuccess(JSONObject result) {
							try {
								if (result.has("user_load")) {
									int userload_id=result.getInt("user_load");
									if(userload_id == 0){
										Utility.startActivity(SplashActivity.this, LoginActivity.class);
									}
									else{
										Utility.startActivity(SplashActivity.this, HomeActivity.class);
									}
													
									finish();	
								}else{
									Log.d("abx", "json error in splash");
								}
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							return null;
						}
						
						@Override
						public String onStarted() {
							// TODO Auto-generated method stub
							return null;
						}
						
						@Override
						public String onFailed() {
							
							return null;
						}
					}).execute("");
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
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
