package com.jsservey.view.home;



import com.abx.jsservey.R;
import com.jsservey.view.BaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("abx","in Main Activity");
		setContentView(R.layout.survey_home_layout);
		handleHomeClick(this.findViewById(android.R.id.content));
	}
}
