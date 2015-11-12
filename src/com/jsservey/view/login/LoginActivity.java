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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements ApiRequestListner{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login_view);
		
		
		final EditText username_ed =(EditText) findViewById(R.id.user_name);
		final EditText pass_ed =(EditText) findViewById(R.id.password);
		
		findViewById(R.id.signup_textview).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utility.startActivity(LoginActivity.this, AccountCreationActivity.class);				
				finish();
				
			}
		});;
		findViewById(R.id.login_button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RequestCreator requestCreator = new RequestCreator(getApplicationContext());
				new ApiRequester(LoginActivity.this,requestCreator.loginRequest( username_ed.getText().toString(), pass_ed.getText().toString(), "123456"),LoginActivity.this).execute("");
				
			}
		});;
		
		
		
		
		
	}

	@Override
	public String onSuccess(JSONObject result) {
		//Toast.makeText(this, "Login Success!", 1000).show();
		try {
			if (result.has("data")) {
				JSONObject data = result.getJSONObject("data");
				String isAdvacedUser=data.getString("is_advaced_user");
				
				String user_advaced_id=data.getString("user_advaced_id");
				String  survey_perform_only=data.getString("survey_perform_only");
				String  user_id=data.getString("user_id");
				Log.d("abx", user_advaced_id+" "+survey_perform_only+" "+user_id);
				
				Utility.startActivity(LoginActivity.this, HomeActivity.class);				
				finish();
			}else{
				Toast.makeText(this, "Login failed!", 1000).show();
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public String onFailed() {
		
		Toast.makeText(this, "Login Failed!", 1000).show();
		//Utility.startActivity(LoginActivity.this, HomeActivity.class);
		return null;
	}

	@Override
	public String onStarted() {
		Toast.makeText(this, "Login Initiated!", 1000).show();
		return null;
	}


}
