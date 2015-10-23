package com.jsservey.view.home;



import com.abx.jsservey.R;
import com.jsservey.view.BaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends BaseActivity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("abx","in Main Activity");
		setContentView(R.layout.home_layout);
		handleHomeClick(this.findViewById(android.R.id.content));
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
