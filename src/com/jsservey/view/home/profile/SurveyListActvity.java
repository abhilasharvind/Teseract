package com.jsservey.view.home.profile;

import java.util.ArrayList;

import com.abx.jsservey.R;
import com.adapters.ProfileListCustomAdapter;
import com.adapters.SurveyListAdapter;
import com.jsservey.Questions.QuestionListActivity;
import com.jsservey.database.SQLiteHelper;
import com.jsservey.model.Profile;
import com.jsservey.model.Survey;
import com.jsservey.utils.Utility;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class SurveyListActvity extends Activity {
	
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_main_list_layout);
		//handleHomeClick(this.findViewById(android.R.id.content));
		//menuHandler(this.findViewById(android.R.id.content));
		Bundle bundle =getIntent().getExtras();
		final String p_id=bundle.getString("pf_id");
		Log.d("abx", "(SurveyListActvity)pfrofile id"+p_id);
		//Toast.makeText(getApplicationContext(), "yy"+p_id, 1500).show();
		
		 lv = (ListView) findViewById(R.id.listView);
		 findViewById(R.id.add_profile_imbt).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//Utility.startActivity(SurveyListActvity.this, SurveyCreationActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("pf_id", p_id);
				Intent intent = new Intent(SurveyListActvity.this, SurveyCreationActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});;
		
		
		new DbAsyncTask_survey(p_id).execute("");	
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

public class DbAsyncTask extends AsyncTask<String, Void, ArrayList<Profile>>{

	@Override
	protected ArrayList<Profile> doInBackground(String... arg0) {
		Log.d("abx", "DbAsyncTask in do in back");
		SQLiteHelper db = 	SQLiteHelper.getInstance(getApplicationContext());	
		ArrayList<Profile> result;
		result=db. getProfileNames() ;
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
		lv.setAdapter(new ProfileListCustomAdapter(getApplicationContext(),result));
		super.onPostExecute(result);
	}
	
}
public class DbAsyncTask_survey extends AsyncTask<String, Void, ArrayList<Survey>>{
	String p_id;
	public DbAsyncTask_survey(String p_id) {
		this.p_id=p_id;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ArrayList<Survey> doInBackground(String... arg0) {
		Log.d("abx", "DbAsyncTask in do in back");
		SQLiteHelper db = 	SQLiteHelper.getInstance(getApplicationContext());	
		ArrayList<Survey> result;
		result=db. getSurveyList_bulk(p_id);
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
		lv.setAdapter(new SurveyListAdapter(getApplicationContext(),result));
		super.onPostExecute(result);
	}
	
}	
	
}

