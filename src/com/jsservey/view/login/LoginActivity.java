package com.jsservey.view.login;



import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.abx.jsservey.R;
import com.jsservey.utils.Utility;
import com.jsservey.view.BaseActivity;
import com.jsservey.view.home.HomeActivity;
import com.jsservey.view.home.survey.StartSurveyActivity;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;

public class LoginActivity extends BaseActivity implements ApiRequestListner{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.login_view);
		
		
		final EditText username_ed =(EditText) findViewById(R.id.user_name);
		final EditText pass_ed =(EditText) findViewById(R.id.password);
		final CheckBox svd =(CheckBox)findViewById(R.id.svdPwd);
		
		
		findViewById(R.id.signup_textview).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utility.startActivity(LoginActivity.this, CreateNewUserActivity.class);				
				finish();
				
			}
		});
		findViewById(R.id.login_button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final int svdStatus;
				if(svd.isChecked()){
					svdStatus=1;
				}else{
					svdStatus=0;
				}
				RequestCreator requestCreator = new RequestCreator(getApplicationContext());
				new ApiRequester(LoginActivity.this,requestCreator.loginRequest( username_ed.getText().toString(), pass_ed.getText().toString(),svdStatus),LoginActivity.this).execute("");
				
			}
		});;
		
		
		
		
		
	}

	@Override
	public String onSuccess(JSONObject result) {
		try {loddingIndicator(View.GONE);
			if (result!=null && result.has("data")) {
				JSONObject data = result.getJSONObject("data");
				String isAdvacedUser=data.getString("is_advaced_user");
				
				String user_advaced_id=data.getString("user_advaced_id");
				String  survey_perform_only=data.getString("survey_perform_only");
				String  user_id=data.getString("user_id");
				Log.d("abx", user_advaced_id+" "+survey_perform_only+" "+user_id);
				if(isAdvacedUser.equals("true") && survey_perform_only.equals("1")){
					Utility.startActivity(LoginActivity.this, StartSurveyActivity.class);
				
				}
				else{
				Utility.startActivity(LoginActivity.this, HomeActivity.class);
				}				
				finish();
			}else{
				Toast.makeText(this, "Login failed!", Toast.LENGTH_SHORT).show();
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public String onFailed() {
		
		Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
		loddingIndicator(View.GONE);
		return null;
	}

	@Override
	public String onStarted() {
		Toast.makeText(this, "Login Initiated!", Toast.LENGTH_SHORT).show();
		return null;
	}

	private void loddingIndicator(int visibility) {
		findViewById(R.id.home_pg_rl).setVisibility(visibility);;
	}
}
