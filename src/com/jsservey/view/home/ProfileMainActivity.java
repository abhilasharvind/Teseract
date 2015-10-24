package com.jsservey.view.home;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.abx.jsservey.R;
import com.jsservey.model.Profile;
import com.jsservey.view.BaseActivity;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;

import android.os.Bundle;
import android.widget.ListView;

public class ProfileMainActivity extends BaseActivity implements ApiRequestListner{
	
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_main_list_layout);
		handleHomeClick(this.findViewById(android.R.id.content));
		menuHandler(this.findViewById(android.R.id.content));
		
		 lv = (ListView) findViewById(R.id.listView);
		
		RequestCreator requestCreator = new RequestCreator();
		new ApiRequester(this, requestCreator.profileFetch("csfeedback", "123456"), this).execute("");
		
		
		
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
							profile.setId(innerobj.getString("id"));
							profile.setUid(innerobj.getString("uid"));
							profile.setProid(innerobj.getString("proid"));
							profile.setProname(innerobj.getString("proname"));
							profile.setSurid(innerobj.getString("surid"));
							profile.setSurveyName(innerobj.getString("survey_name"));
							profile.setSid(innerobj.getString("sid"));
							profile.setQuestion(innerobj.getString("question"));
							profile.setAnswid(innerobj.getString("answid"));
							profile.setAnswerName(innerobj.getString("answer_name"));
							profileArray.add(profile);
						}
						
						lv.setAdapter(new UsersListCustomAdapter(this,profileArray));
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
