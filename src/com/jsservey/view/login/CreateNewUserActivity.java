package com.jsservey.view.login;

import org.json.JSONObject;

import com.abx.jsservey.R;
import com.jsservey.model.NewUser;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateNewUserActivity extends Activity implements ApiRequestListner{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_account);
		//findViewById(R.id.home_pg_rl).setVisibility(View.GONE);
		Button createUser=  (Button) findViewById(R.id.create_account_bt);
		createUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Log.d("abx", "in click");
				EditText username_tv =(EditText) findViewById(R.id.user_name);
				EditText fname_tv =(EditText) findViewById(R.id.first_name);
				EditText lname_tv =(EditText) findViewById(R.id.last_name);
				EditText phonenumb_tv =(EditText) findViewById(R.id.phone_number_ed);
				EditText address_tv =(EditText) findViewById(R.id.address_ed);
				EditText passwrd_tv =(EditText) findViewById(R.id.password);
				EditText paswwrd_dup_tv =(EditText) findViewById(R.id.confirm_password);
				EditText designation_ed =(EditText) findViewById(R.id.designation_ed);
				EditText department_ed =(EditText) findViewById(R.id.department_ed);
				
				
				String username=username_tv.getText().toString();
				String fname=fname_tv.getText().toString();;
				String lname=lname_tv.getText().toString();;
				String phonenumb=phonenumb_tv.getText().toString();;
				String address=address_tv.getText().toString();;
				String passwrd=passwrd_tv.getText().toString();;
				String paswwrd_dup=paswwrd_dup_tv.getText().toString();
				
				String designation=designation_ed.getText().toString();;
				String department=department_ed.getText().toString();;
				if(!paswwrd_dup.equals(passwrd)){//Add null check too bro
					paswwrd_dup_tv.setError("Password don't match");
				}else{
					NewUser user = new NewUser();
					user.setUsername(username);
					user.setAddress(address);
					user.setLname(lname);
					user.setPasswrd(passwrd);//ToDo need to check if the password is confirmed or not
					user.setDepartment(department);
					user.setFname(fname);
					user.setPhonenumb(phonenumb);
					user.setDesignation(designation);
					RequestCreator creator = new RequestCreator(getApplicationContext());
					new ApiRequester(getApplicationContext(), creator.createUser(user), CreateNewUserActivity.this).execute("");
				}
				
				
			}
		});
		
		

	}

	@Override
	public String onSuccess(JSONObject result) {
		findViewById(R.id.home_pg_rl).setVisibility(View.GONE);
		Toast.makeText(this, "User created", 1500).show();
		return null;
	}

	@Override
	public String onFailed() {
		findViewById(R.id.home_pg_rl).setVisibility(View.GONE);
		Toast.makeText(this, "Unable to create User", 1500).show();
		return null;
	}

	@Override
	public String onStarted() {
		findViewById(R.id.home_pg_rl).setVisibility(View.VISIBLE);
		//Toast.makeText(this, "", 1500).show();
		return null;
	
}
}