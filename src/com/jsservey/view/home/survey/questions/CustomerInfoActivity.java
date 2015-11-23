package com.jsservey.view.home.survey.questions;

import com.abx.jsservey.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class CustomerInfoActivity extends Activity {

@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.thankyou);
	
}

}
