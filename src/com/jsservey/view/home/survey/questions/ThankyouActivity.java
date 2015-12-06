package com.jsservey.view.home.survey.questions;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.abx.jsservey.R;
import com.jsservey.utils.Utility;

public class ThankyouActivity  extends Activity {

@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.thankyou);
	findViewById(R.id.thanku_done_bt).setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Utility.startActivity(ThankyouActivity.this, QuestionsDisplayActivity.class);
			
		}
	});
}

}
