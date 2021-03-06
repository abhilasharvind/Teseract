package com.jsservey.view.login;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.abx.jsservey.R;
import com.jsservey.utils.Utility;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;

public class DeviceCreationActivity extends Activity {
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.company_registration);
		findViewById(R.id.progress_rl).setVisibility(View.GONE);
		findViewById(R.id.submit_registration).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				RequestCreator rq = new RequestCreator(getApplicationContext());
				EditText compnyKey_id = (EditText) findViewById(R.id.company_key_edittext);
				EditText device_name = (EditText) findViewById(R.id.company_name_edittext);
				final String cmpnyKey=compnyKey_id.getText().toString();
				final String devicename=device_name.getText().toString();
				Log.d("abx", "cmpnyKey="+cmpnyKey);
				new ApiRequester(DeviceCreationActivity.this, rq.devicereg(cmpnyKey,devicename), new ApiRequestListner() {
					
					@Override
					public String onSuccess(JSONObject result) {
						findViewById(R.id.progress_rl).setVisibility(View.GONE);
						if (result!=null && result.has("registration_sucess")) {
							try {
								int regid= result.getInt("registration_sucess");
								if(regid == 1){
									Utility.startActivity(DeviceCreationActivity.this, LoginActivity.class);
									finish();
								}
								else{
									Utility.startActivity(DeviceCreationActivity.this, LoginActivity.class);
									finish();
									Toast.makeText(DeviceCreationActivity.this, "Registration failed", 2000).show();
								}
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}else if(result!=null && result.has("invalid_company_key")){
							Toast.makeText(getApplicationContext(), "Invalid Company Key", 2000).show();
							
						}
						
						return null;
					}
					
					@Override
					public String onStarted() {
						// TODO Auto-generated method stub
						findViewById(R.id.progress_rl).setVisibility(View.VISIBLE);
						return null;
					}
					
					@Override
					public String onFailed() {
						findViewById(R.id.progress_rl).setVisibility(View.GONE);
						Toast.makeText(DeviceCreationActivity.this, "Registration failed", 2000).show();
						return null;
					}
				}).execute("");
				
			}
		});
	}

}
