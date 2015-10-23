package com.jsservey.view;


import com.abx.jsservey.R;
import com.jsservey.utils.Utility;
import com.jsservey.view.home.MainActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

public class BaseActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	
	public void handleHomeClick(View view){
		view.findViewById(R.id.home_im_button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utility.startActivity(BaseActivity.this, MainActivity.class);
				
			}
		});
		
		
	}

}
