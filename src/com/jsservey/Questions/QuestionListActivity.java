package com.jsservey.Questions;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.abx.jsservey.R;
import com.adapters.ProfileListCustomAdapter;
import com.jsservey.database.SQLiteHelper;
import com.jsservey.model.Profile;
import com.jsservey.utils.Utility;
import com.jsservey.view.home.profile.ProfileCreationActivity;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;

public class QuestionListActivity extends Activity implements OnClickListener,ApiRequestListner {
	
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.profile_main_list_layout);
		//handleHomeClick(this.findViewById(android.R.id.content));
		//menuHandler(this.findViewById(android.R.id.content));
		Bundle bundle =getIntent().getExtras();
		final String survey_id=bundle.getString("survey_id");
		Log.d("abx", "(QuestionListActivity)survey id"+survey_id);
		RequestCreator requestCreator = new RequestCreator();
		new ApiRequester(this, requestCreator.questionFetch("csfeedback", "123456",survey_id), this).execute("");
		 lv = (ListView) findViewById(R.id.listView);
		 findViewById(R.id.add_profile_imbt).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Utility.startActivity(QuestionListActivity.this, ProfileCreationActivity.class);
				
			}
		});;
		
		
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				//Profile pro =(Profile) arg1.getTag();
				Toast.makeText(getApplicationContext(), "yy", 1500).show();
//				Bundle bundle = new Bundle();
//				bundle.putString("pf_id", pro.getProfile_id());
//				Intent intent = new Intent(QuestionListActivity.this, SurveyListActvity.class);
//				intent.putExtras(bundle);
//				startActivity(intent);
				//Utility.startActivity(ProfileMainActivity.this, SurveyListActvity.class);
				
			}
		});
		
		
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
			
		
	}
	
public class DbAsyncTask extends AsyncTask<String, Void, ArrayList<Profile>>{

	@Override
	protected ArrayList<Profile> doInBackground(String... arg0) {
		Log.d("abx", "DbAsyncTask in do in back");
		SQLiteHelper db = 	SQLiteHelper.getInstance(getApplicationContext());	
		ArrayList<Profile> result=db.getProfiles();//getProfileNames() ;
		Log.d("abx", "DbAsyncTask in do in back result size="+result.size());
		
		return result;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}
	@Override
	protected void onPostExecute(ArrayList<Profile> result) {
		Log.d("abx", "DbAsyncTask in onpost adapterset with size ="+result.size());
		lv.setAdapter(new ProfileListCustomAdapter(getApplicationContext(),result));
		super.onPostExecute(result);
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
				profile.setProfile_id(innerobj.getString("id"));
				Log.d("abx", "pf_name= "+innerobj.getString("profile_name"));
				profile.setProfilr_name(innerobj.getString("profile_name"));
				profile.setIs_activated(false);
				
				profileArray.add(profile);
			}
			SQLiteHelper db =SQLiteHelper.getInstance(this);
			db.insertProfiles(profileArray);
			new DbAsyncTask().execute("");
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
	// TODO Auto-generated method stub
	return null;
}

@Override
public String onStarted() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void onClick(View arg0) {
	// TODO Auto-generated method stub
	
}
	
	
}
