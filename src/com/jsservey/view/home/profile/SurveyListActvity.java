package com.jsservey.view.home.profile;

import java.util.ArrayList;

import com.abx.jsservey.R;
import com.jsservey.database.SQLiteHelper;
import com.jsservey.model.ProfileName;
import com.jsservey.model.Survey;
import com.jsservey.utils.Utility;

import android.app.Activity;
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
		String p_id=bundle.getString("pro_id");
		//Toast.makeText(getApplicationContext(), "yy"+p_id, 1500).show();
		
		 lv = (ListView) findViewById(R.id.listView);
		 findViewById(R.id.add_profile_imbt).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utility.startActivity(SurveyListActvity.this, ProfileCreationActivity.class);
				
			}
		});;
		
		
		new DbAsyncTask_survey(p_id).execute("");	
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				ProfileName pro =(ProfileName) arg1.getTag();
				//Toast.makeText(getApplicationContext(), "xx"+pro.getProfilr_name(), 1500).show();
				
			}
		});
		
		
		
	}

public class DbAsyncTask extends AsyncTask<String, Void, ArrayList<ProfileName>>{

	@Override
	protected ArrayList<ProfileName> doInBackground(String... arg0) {
		Log.d("abx", "DbAsyncTask in do in back");
		SQLiteHelper db = 	SQLiteHelper.getInstance(getApplicationContext());	
		ArrayList<ProfileName> result;
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
	protected void onPostExecute(ArrayList<ProfileName> result) {
		lv.setAdapter(new UsersListCustomAdapter(getApplicationContext(),result));
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

