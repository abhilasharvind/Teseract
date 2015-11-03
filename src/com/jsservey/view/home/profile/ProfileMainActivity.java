package com.jsservey.view.home.profile;

import java.util.ArrayList;

import com.abx.jsservey.R;
import com.jsservey.database.SQLiteHelper;
import com.jsservey.model.ProfileName;
import com.jsservey.utils.Utility;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ProfileMainActivity extends Activity {
	
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.profile_main_list_layout);
		//handleHomeClick(this.findViewById(android.R.id.content));
		//menuHandler(this.findViewById(android.R.id.content));
		
		 lv = (ListView) findViewById(R.id.listView);
		 findViewById(R.id.add_profile_imbt).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utility.startActivity(ProfileMainActivity.this, ProfileCreationActivity.class);
				
			}
		});;
		
		
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				ProfileName pro =(ProfileName) arg1.getTag();
				//Toast.makeText(getApplicationContext(), "yy"+pro.getProfile_id(), 1500).show();
				Bundle bundle = new Bundle();
				bundle.putString("pf_id", pro.getProfile_id());
				Intent intent = new Intent(ProfileMainActivity.this, SurveyListActvity.class);
				intent.putExtras(bundle);
				startActivity(intent);
				//Utility.startActivity(ProfileMainActivity.this, SurveyListActvity.class);
				
			}
		});
		
		
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		new DbAsyncTask().execute("");	
		
	}
	
public class DbAsyncTask extends AsyncTask<String, Void, ArrayList<ProfileName>>{

	@Override
	protected ArrayList<ProfileName> doInBackground(String... arg0) {
		//Log.d("abx", "DbAsyncTask in do in back");
		SQLiteHelper db = 	SQLiteHelper.getInstance(getApplicationContext());	
		ArrayList<ProfileName> result=db. getProfileNames_bulk();//getProfileNames() ;
		//Log.d("abx", "DbAsyncTask in do in back result size="+result.size());
		
		return result;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}
	@Override
	protected void onPostExecute(ArrayList<ProfileName> result) {
		lv.setAdapter(new ProfileListCustomAdapter(getApplicationContext(),result));
		super.onPostExecute(result);
	}
	
}
	
	
}
