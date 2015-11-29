package com.jsservey.view.home.survey;

import org.json.JSONObject;

import com.abx.jsservey.R;
import com.jsservey.database.SQLiteHelper;
import com.jsservey.utils.Utility;
import com.jsservey.view.home.survey.questions.QuestionsDisplayActivity;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class StartSurveyActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.survey_ll);
		
		Button prf_name = (Button) findViewById(R.id.start_survey_button);
		prf_name.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utility.startActivity(StartSurveyActivity.this, QuestionsDisplayActivity.class);				
			}
		});
		//AppPref appPref = new AppPref(getApplicationContext());
		//prf_name.setText(""+appPref.getString(PrefConstant.ACTIVATED_PROFILE));
		//Utility.startActivity(StartSurveyActivity.this, CreateQuestionTextActivity.class);
		
		fetchActivatedSurveyQues();
		
	}

	private void fetchActivatedSurveyQues() {
		SurveyRequestCreator requestCreator = new SurveyRequestCreator(getApplicationContext());
		new ApiRequester(StartSurveyActivity.this,requestCreator.surveyQuesFetch(""), new ApiRequestListner() {
			
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
				Toast.makeText(StartSurveyActivity.this, "Error in fetching survey", 1500).show();
				return null;
			}
		}).execute("");
	}

}
