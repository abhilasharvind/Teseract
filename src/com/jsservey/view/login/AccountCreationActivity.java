package com.jsservey.view.login;

import com.abx.jsservey.R;
import com.jsservey.model.RegistrationDetailsBean;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AccountCreationActivity extends Activity implements
		OnClickListener {
	EditText userName, firstName, lastName, password,confirmPassword;
	Button createAccount;
	RegistrationDetailsBean registrationDetailsBean;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_account);
		userName = (EditText) findViewById(R.id.user_name);
		firstName = (EditText) findViewById(R.id.first_name);
		lastName = (EditText) findViewById(R.id.last_name);
		password = (EditText) findViewById(R.id.password);
		confirmPassword = (EditText) findViewById(R.id.confirm_password);
		createAccount = (Button) findViewById(R.id.create_account);
		createAccount.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.create_account) {
			if (TextUtils.isEmpty(userName.getText())) {
				userName.setError("please enter a user name");
			} else if (TextUtils.isEmpty(firstName.getText())) {
				firstName.setError("please enter a firstname");
			} else if (TextUtils.isEmpty(lastName.getText())) {
				firstName.setError("please enter a lastname");
			} else if (TextUtils.isEmpty(password.getText())) {
				firstName.setError("please enter a password");
			} else if (TextUtils.isEmpty(confirmPassword.getText())) {
				firstName.setError("please confirm password");
			}else{
				saveRegistrationDetails();
			}
		}
	}

	private void saveRegistrationDetails() {
		registrationDetailsBean = new RegistrationDetailsBean();
		registrationDetailsBean.setUserName(userName.getText().toString());
		registrationDetailsBean.setFirstName(firstName.getText().toString());
		registrationDetailsBean.setLastName(lastName.getText().toString());
		registrationDetailsBean.setPassword(password.getText().toString());
		registrationDetailsBean.setConfirmPassword(confirmPassword.getText().toString());
		
	}
}
