package com.jsservey.view.reportandabout;

import android.os.Bundle;
import android.util.Log;

import com.abx.jsservey.R;
import com.jsservey.view.BaseActivity;

public class AboutActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("abx","in Main Activity");
		setContentView(R.layout.about_layout);
		handleHomeClick(this.findViewById(android.R.id.content));
		menuHandler(this.findViewById(android.R.id.content));
	}
}

