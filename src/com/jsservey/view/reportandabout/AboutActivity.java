package com.jsservey.view.reportandabout;

import com.abx.jsservey.R;
import com.jsservey.view.BaseActivity;

import android.os.Bundle;
import android.util.Log;

public class AboutActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("abx","in Main Activity");
		setContentView(R.layout.about_layout);
		handleHomeClick(this.findViewById(android.R.id.content));
	}
}

