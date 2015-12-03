package com.jsservey.view.home.survey.questions;

import org.json.JSONObject;

import com.abx.jsservey.R;
import com.jsservey.utils.Utility;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class CustomerInfoActivity extends Activity implements ApiRequestListner{

@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	//requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.customer_info_layout);
	final EditText name_ed = (EditText) findViewById(R.id.name_ed);
	final EditText mobile_ed = (EditText) findViewById(R.id.mobile_ed);
	final EditText address_ed = (EditText) findViewById(R.id.address_ed);
	final EditText email_ed = (EditText) findViewById(R.id.email_ed);
	final EditText personaldesc_ed = (EditText) findViewById(R.id.personaldesc_ed);
	findViewById(R.id.Done_bt).setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			RequestCreator requestCreator = new RequestCreator(getApplicationContext());
			new ApiRequester(CustomerInfoActivity.this, requestCreator.createCustomerInfo(name_ed.getText().toString(), mobile_ed.getText().toString(), email_ed.getText().toString(), address_ed.getText().toString(), personaldesc_ed.getText().toString()), CustomerInfoActivity.this).execute("");
			Utility.startActivity(CustomerInfoActivity.this, ThankyouActivity.class);
		}
	});
	
findViewById(R.id.skip_bt).setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			Utility.startActivity(CustomerInfoActivity.this, ThankyouActivity.class);
			
		}
	});
	
}

@Override
public String onSuccess(JSONObject result) {
	// TODO Auto-generated method stub
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

}
