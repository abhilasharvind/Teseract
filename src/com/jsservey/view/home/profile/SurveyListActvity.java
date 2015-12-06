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
import android.widget.Toast;

import com.abx.jsservey.R;
import com.adapters.SurveyListAdapter;
import com.interfaces.EditDeleteUpdate_listner;
import com.jsservey.database.SQLiteHelper;
import com.jsservey.model.Survey;
import com.jsservey.view.home.EditUpdateDelete;
import com.jsservey.view.home.survey.questions.QuestionListActivity;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;
import com.jsservey.webservices.SurveyRequestCreator;

public class SurveyListActvity extends Activity implements OnClickListener,ApiRequestListner,EditDeleteUpdate_listner {
	
	ListView lv;
	static String p_id="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.profile_main_list_layout);
		//handleHomeClick(this.findViewById(android.R.id.content));
		//menuHandler(this.findViewById(android.R.id.content));
		Bundle bundle =getIntent().getExtras();
		 p_id=bundle.getString("pf_id");
		Log.d("abx", "(SurveyListActvity) pfrofile id:"+p_id);
		
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
		fetchANDreload();	
		
	}

	private void fetchANDreload() {
		RequestCreator requestCreator = new RequestCreator(getApplicationContext());
		new ApiRequester(this, requestCreator.surveyFetch(p_id), this).execute("");
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
		lv.setAdapter(new SurveyListAdapter(getApplicationContext(),result,SurveyListActvity.this));
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
				survey.setActivated_survey_id(result.getString("activated_survey_id"));
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

@Override
public void onEditTaskStart(int type, String id) {
	// TODO Auto-generated method stub
	
}

@Override
public void onUpdtaeTaskStart(int type, String id) {
	// TODO Auto-generated method stub
	
}

@Override
public void onDeleteTaskStart(int type, String id) {
	EditUpdateDelete requestCreator = new EditUpdateDelete(getApplicationContext());
	new ApiRequester(this, requestCreator.surveyDelete(id), new ApiRequestListner() {
		
		@Override
		public String onSuccess(JSONObject result) {
			loddingIndicator(0);
			Toast.makeText(SurveyListActvity.this, "Profile has been Deleted", 1000).show();
			fetchANDreload();	
			return null;
		}
		
		@Override
		public String onStarted() {
			loddingIndicator(1);
			return null;
		}

		
		@Override
		public String onFailed() {
			loddingIndicator(0);
			return null;
		}
	}).execute("");
	
}

@Override
public void onActivateTaskStart(int type, final String id) {
	EditUpdateDelete requestCreator = new EditUpdateDelete(getApplicationContext());
	
new ApiRequester(this, requestCreator.surveyActivate(id), new ApiRequestListner() {
		
		@Override
		public String onSuccess(JSONObject result) {
			loddingIndicator(View.GONE);
			Toast.makeText(SurveyListActvity.this, "Survey has been Activated", 1500).show();
			SurveyRequestCreator requestCreator = new SurveyRequestCreator(getApplicationContext());
			new ApiRequester(SurveyListActvity.this,requestCreator.surveyQuesFetch(id), new ApiRequestListner() {
				
				@Override
				public String onSuccess(JSONObject result) {
					SQLiteHelper db = 	SQLiteHelper.getInstance(getApplicationContext());
					db.insertSurveyQuestions(result.toString());
					String d=db.getSurveyQuestions();
					Log.d("abx", d);
					return null;
				}
				
				@Override
				public String onStarted() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String onFailed() {
					// TODO Auto-generated method stub
					return null;
				}
			}).execute("");
			fetchANDreload();	
			return null;
		}
		
		@Override
		public String onStarted() {
			loddingIndicator(View.VISIBLE);
			return null;
		}

		
		@Override
		public String onFailed() {
			loddingIndicator(View.GONE);
			return null;
		}
	}).execute("");
	
}
private void loddingIndicator(int visibility) {
	findViewById(R.id.home_pg_rl).setVisibility(visibility);
}	
	
}
