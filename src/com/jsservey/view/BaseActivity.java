package com.jsservey.view;


import org.json.JSONObject;

import com.abx.jsservey.R;
import com.jsservey.utils.Utility;
import com.jsservey.view.home.HomeActivity;
import com.jsservey.view.login.LoginActivity;
import com.jsservey.webservices.ApiRequestListner;
import com.jsservey.webservices.ApiRequester;
import com.jsservey.webservices.RequestCreator;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

public class BaseActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		
	}
	
	
	public void menuHandler(View view){
		view.findViewById(R.id.user_popup).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				PopupMenu popupMenu = new PopupMenu(getApplicationContext(), arg0);
				popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());  
		           
		            //registering popup with OnMenuItemClickListener  
		  
				popupMenu.show();
				popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem arg0) {
						Toast.makeText(BaseActivity.this, "id="+arg0.getItemId(), 1500).show();
						
						int id=arg0.getItemId();
						switch(id){
						case R.id.Logout_popup:
							RequestCreator requestCreator = new RequestCreator(getApplicationContext());
							new ApiRequester(getApplicationContext(), requestCreator.logout(), new ApiRequestListner() {
								
								@Override
								public String onSuccess(JSONObject result) {
									Utility.startActivity(BaseActivity.this, LoginActivity.class);//should exit app as db name is diff for diff users
									finish();
									return null;
								}
								
								@Override
								public String onStarted() {
									// TODO Auto-generated method stub
									return null;
								}
								
								@Override
								public String onFailed() {
									// TODO Auto-generated method stub
									return null;
								}
							}).execute("");
							
							
							break;
						
						
						
						}
						return false;
					}
				});
			}
		});
		
		
	}
	public void handleHomeClick(View view){
		view.findViewById(R.id.home_im_button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utility.startActivity(BaseActivity.this, HomeActivity.class);
				
			}
		});
		
		
	}
	
	
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	//super.onBackPressed();
}
}
