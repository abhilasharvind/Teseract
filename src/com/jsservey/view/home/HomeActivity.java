package com.jsservey.view.home;



import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.abx.jsservey.R;
import com.jsservey.database.SQLiteHelper;
import com.jsservey.model.Profile;
import com.jsservey.utils.Utility;
import com.jsservey.view.BaseActivity;
import com.jsservey.view.home.profile.ProfileMainActivity;
import com.jsservey.view.reportandabout.AboutActivity;
import com.jsservey.view.reportandabout.ReportActivity;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class HomeActivity extends BaseActivity implements OnClickListener,ApiRequestListner{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("abx","in Main Activity");
		setContentView(R.layout.home_layout);
		findViewById(R.id.home_pg_rl).setVisibility(View.GONE);
		setListners();
		
		RequestCreator requestCreator = new RequestCreator();
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

	@Override
	public String onSuccess(JSONObject result) {
		
		if(result.has("data"))
			
		{
			try {
				JSONArray data=result.getJSONArray("data");
				ArrayList<Profile> profileArray= new ArrayList<Profile>();	
				for ( int i=0;i<data.length();i++) {
					Profile profile = new Profile();
					JSONObject innerobj = (JSONObject) data.get(i);
					profile.setUserId(innerobj.getString("user_id"));
					profile.setUserName(innerobj.getString("user_name"));
					profile.setProfileId(innerobj.getString("profile_id"));
					profile.setProfileName(innerobj.getString("profile_name"));
					Log.d("abx", "profile_name "+innerobj.getString("profile_name"));
					profile.setSurveyId(innerobj.getString("survey_id"));
					profile.setSurveyName(innerobj.getString("survey_name"));
					profile.setQuestionId(innerobj.getString("question_id"));
					profile.setQuestionName(innerobj.getString("question_name"));
					profile.setAnswerId(innerobj.getString("answer_id"));
					profile.setAnswerName(innerobj.getString("answer_name"));
					profileArray.add(profile);
				}
				SQLiteHelper db =SQLiteHelper.getInstance(this);
				db.insertProfileDetails_bulk(profileArray);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			Log.d("abx", "elseeeeeee");
		}
		findViewById(R.id.home_pg_rl).setVisibility(View.GONE);
		return null;
	}

	@Override
	public String onFailed() {
		findViewById(R.id.home_pg_rl).setVisibility(View.GONE);
		return null;
	}

	@Override
	public String onStarted() {
		findViewById(R.id.home_pg_rl).setVisibility(View.VISIBLE);
		return null;
	}
}
