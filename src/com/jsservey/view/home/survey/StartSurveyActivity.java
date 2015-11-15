package com.jsservey.view.home.survey;

import com.abx.jsservey.R;
import com.jsservey.utils.AppPref;
import com.jsservey.utils.PrefConstant;
import com.jsservey.utils.Utility;
import com.jsservey.view.home.survey.questions.CreateQuestionTextActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StartSurveyActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startsurvey_layout);
		TextView prf_name = (TextView) findViewById(R.id.profile_name_tv);
		AppPref appPref = new AppPref(getApplicationContext());
		prf_name.setText(""+appPref.getString(PrefConstant.ACTIVATED_PROFILE));
		Utility.startActivity(StartSurveyActivity.this, CreateQuestionTextActivity.class);
	}

}
