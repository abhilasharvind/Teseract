package com.jsservey.view.home.survey.questions;

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
import android.widget.ListView;
import android.widget.Toast;

import com.abx.jsservey.R;
import com.adapters.QuestionListAdapter;
import com.interfaces.QuestionPopUpListner;
import com.jsservey.database.SQLiteHelper;
import com.jsservey.model.Profile;
import com.jsservey.model.Question;
import com.jsservey.view.home.EditUpdateDelete;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;

public class QuestionListActivity extends Activity implements OnClickListener,ApiRequestListner,QuestionPopUpListner {
	String survey_id="";
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
		survey_id=bundle.getString("survey_id");
		Log.d("abx", "(QuestionListActivity)survey id"+survey_id);
		
		 lv = (ListView) findViewById(R.id.listView);
		 findViewById(R.id.add_profile_imbt).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Bundle bundle = new Bundle();
				bundle.putString("survey_id", survey_id);
				Intent intent = new Intent(QuestionListActivity.this, CreateQuestionTextActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});;
		
		
		
		//lv.setOnItemClickListener(new OnItemClickListener() {

			//@Override
			//public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				//Profile pro =(Profile) arg1.getTag();
				//Toast.makeText(getApplicationContext(), "yy", 1500).show();
//				Bundle bundle = new Bundle();
//				bundle.putString("pf_id", pro.getProfile_id());
//				Intent intent = new Intent(QuestionListActivity.this, SurveyListActvity.class);
//				intent.putExtras(bundle);
//				startActivity(intent);
				//Utility.startActivity(ProfileMainActivity.this, SurveyListActvity.class);
				
			//}
		//});
		
		
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		fetchANDreload();	
		
	}

	private void fetchANDreload() {
		RequestCreator requestCreator = new RequestCreator(getApplicationContext());
		new ApiRequester(this, requestCreator.questionFetch(survey_id), this).execute("");
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
		//
		//lv.setAdapter(new QuestionListAdapter(getApplicationContext(),result,QuestionListActivity.this));
		super.onPostExecute(result);
	}
	
}

@Override
public String onSuccess(JSONObject result) {
	if(result.has("data"))
		
	{
		try {
			JSONArray data=result.getJSONArray("data");
			ArrayList<Question> questionArray= new ArrayList<Question>();	
			for ( int i=0;i<data.length();i++) {
				Question question = new Question();
				JSONObject innerobj = (JSONObject) data.get(i);
				question.setQuestion_id(innerobj.getString("id"));
				question.setQuestion(innerobj.getString("question_name"));
				
				
				questionArray.add(question);
			}
			Log.d("abx", "DbAsyncTask in onSuccess ="+questionArray.size());
			lv.setAdapter(new QuestionListAdapter(getApplicationContext(),questionArray,QuestionListActivity.this));
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
EditUpdateDelete editUpdateDelete= new EditUpdateDelete(getApplicationContext());
	
	new ApiRequester(this, editUpdateDelete.questionDelete(id), new ApiRequestListner() {
		
		@Override
		public String onSuccess(JSONObject result) {
			fetchANDreload();	
			loddingIndicator(0);
			Toast.makeText(QuestionListActivity.this, "Question has been Deleted", Toast.LENGTH_LONG).show();
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
public void onVisible(int type, String id) {
	EditUpdateDelete editUpdateDelete= new EditUpdateDelete(getApplicationContext());
new ApiRequester(this, editUpdateDelete.questionVisibility(id, 1), new ApiRequestListner() {
		
		@Override
		public String onSuccess(JSONObject result) {
			
			loddingIndicator(View.GONE);
			Toast.makeText(QuestionListActivity.this, "Question has been Deleted", Toast.LENGTH_LONG).show();
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

@Override
public void onInVisible(int type, String id) {
	EditUpdateDelete editUpdateDelete= new EditUpdateDelete(getApplicationContext());
	new ApiRequester(this, editUpdateDelete.questionVisibility(id, 3), new ApiRequestListner() {
			
			@Override
			public String onSuccess(JSONObject result) {
				loddingIndicator(0);
				Toast.makeText(QuestionListActivity.this, "Question has been Deleted", Toast.LENGTH_LONG).show();
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

private void loddingIndicator(int visibility) {
	findViewById(R.id.home_pg_rl).setVisibility(visibility);;
}

	
	
}
