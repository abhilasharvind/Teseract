package com.jsservey.view.home.profile;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.abx.jsservey.R;
import com.adapters.ProfileListCustomAdapter;
import com.adapters.SurveyListAdapter;
import com.jsservey.Questions.QuestionListActivity;
import com.jsservey.database.SQLiteHelper;
import com.jsservey.model.Profile;
import com.jsservey.model.Survey;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;

public class SurveyListActvity extends Activity implements OnClickListener,ApiRequestListner {
	
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
		final String p_id=bundle.getString("pf_id");
		Log.d("abx", "(SurveyListActvity) pfrofile id:"+p_id);
		RequestCreator requestCreator = new RequestCreator();
		new ApiRequester(this, requestCreator.surveyFetch("csfeedback", "123456",p_id), this).execute("");
		 lv = (ListView) findViewById(R.id.listView);
		 findViewById(R.id.add_profile_imbt).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Bundle bundle = new Bundle();
				bundle.putString("pf_id", p_id);
				Intent intent = new Intent(SurveyListActvity.this, SurveyCreationActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});;
		
		
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Survey survey =(Survey) arg1.getTag();
				//Toast.makeText(getApplicationContext(), "xx"+pro.getProfilr_name(), 1500).show();
				Bundle bundle = new Bundle();
				bundle.putString("survey_id", survey.getSurvey_id());
				Intent intent = new Intent(SurveyListActvity.this, QuestionListActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
		
		
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
			
		
	}
	
public class DbAsyncTask extends AsyncTask<String, Void, ArrayList<Survey>>{

	@Override
	protected ArrayList<Survey> doInBackground(String... arg0) {
		Log.d("abx", "DbAsyncTask in do in back");
		SQLiteHelper db = 	SQLiteHelper.getInstance(getApplicationContext());	
		ArrayList<Survey> result=db.getSurvey();//getProfileNames() ;
		Log.d("abx", "DbAsyncTask in do in back result size="+result.size());
		
		return result;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}
	@Override
	protected void onPostExecute(ArrayList<Survey> result) {
		Log.d("abx", "DbAsyncTask in onpost adapterset with size ="+result.size());
		lv.setAdapter(new SurveyListAdapter(getApplicationContext(),result));
		super.onPostExecute(result);
	}
	
}

@Override
public String onSuccess(JSONObject result) {
	Log.d("abx", "survey sucess");
	if(result.has("data"))
		
	{
		try {
			JSONArray data=result.getJSONArray("data");
			ArrayList<Survey> surveyArray= new ArrayList<Survey>();	
			Log.d("abx", "survey sucess"+data.length());
			for ( int i=0;i<data.length();i++) {
				Survey survey = new Survey();
				JSONObject innerobj = (JSONObject) data.get(i);
				survey.setSurvey_id(innerobj.getString("id"));
				Log.d("abx", "pf_name= "+innerobj.getString("survey_name"));
				survey.setSurvey_name(innerobj.getString("survey_name"));
				//profile.setIs_activated(false);
				
				surveyArray.add(survey);
			}
			SQLiteHelper db =SQLiteHelper.getInstance(this);
			db.insertSurvey(surveyArray);
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
