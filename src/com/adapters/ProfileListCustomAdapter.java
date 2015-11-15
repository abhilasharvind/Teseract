package com.adapters;

import java.util.ArrayList;

import com.abx.jsservey.R;
import com.interfaces.EditDeleteUpdate_listner;
import com.jsservey.model.Profile;
import com.jsservey.utils.AppPref;
import com.jsservey.utils.PrefConstant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileListCustomAdapter extends BaseAdapter implements OnClickListener{

	//private String[] result;
	private Context context;
	//private int[] imageId;
	private ArrayList<Profile> profileArray;
	//public static int[] prgmImages = { R.drawable.more_button_icon,R.drawable.more_button_icon,R.drawable.more_button_icon,R.drawable.more_button_icon };
	//public static String[] prgmNameList = { "user 1", "user 2", "user 3",	"user 4" };
	private static LayoutInflater inflater = null;
	EditDeleteUpdate_listner editDeleteUpdate_listner;

	public ProfileListCustomAdapter(Context context,ArrayList<Profile> profileArray,EditDeleteUpdate_listner editDeleteUpdate_listner) {
		// TODO Auto-generated constructor stub
		//this.result = prgmNameList;
		this.context = context;
		//this.imageId = prgmImages;
		inflater = (LayoutInflater) context	.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.profileArray=profileArray;
		this.editDeleteUpdate_listner=editDeleteUpdate_listner;
	}

	@Override
	public int getCount() {
		return profileArray.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public class Holder {
		TextView tv;
		ImageView img;
		//FrameLayout popupMenu;
		ImageView select_tick;
	}
	
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Holder holder = new Holder();
		final View rowView;
		rowView = inflater.inflate(R.layout.user_list_raw_layout, null);
		holder.tv = (TextView) rowView.findViewById(R.id.textView1);
		holder.img = (ImageView) rowView.findViewById(R.id.pop_up);
		//holder.popupMenu = (FrameLayout) rowView.findViewById(R.id.user_popup_menu);//why is it here
		holder.img.setOnClickListener(this);
		holder.tv.setText(profileArray.get(position).getProfilr_name());
		holder.select_tick= (ImageView) rowView.findViewById(R.id.profile_tick);
		if (profileArray.get(position).isIs_activated()) {
			holder.select_tick.setVisibility(View.VISIBLE);	
		}else{
			holder.select_tick.setVisibility(View.GONE);	
		}
		holder.img.setImageResource(R.drawable.more_button_icon);
		rowView.setTag(profileArray.get(position));		
		holder.img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View view) {
				
				popHandler(rowView, view);
				
			}

			
		});
		return rowView;
	}

	@Override
	public void onClick(View view) {
		
	}

	private void popHandler(final View rowView, final View view) {
		PopupMenu popup = new PopupMenu(context, view);  
		popup.getMenuInflater().inflate(R.menu.profile_edit_delete_activate_popup, popup.getMenu()); 
		popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem mt) {
				int id = mt.getItemId();
				Profile pf= (Profile) rowView.getTag();
				if (id == R.id.activate_profile) {
					
					AppPref appPref = new AppPref(context);
					
					if (pf!= null) {
						//Toast.makeText(context, "Profile Activated "+pf.getProfilr_name(), 1000).show();
						appPref.putString(PrefConstant.ACTIVATED_PROFILE,pf.getProfile_id());
						editDeleteUpdate_listner.onActivateTaskStart(1,pf.getProfile_id());
					}else{
						//Toast.makeText(context, "Unable to Activate profile", 1000).show();
					}
					
					//
				}else if(id == R.id.delete_popup){
					editDeleteUpdate_listner.onDeleteTaskStart(1, pf.getProfile_id());
					
				}else if(id == R.id.edit_popup){
					editDeleteUpdate_listner.onEditTaskStart(1, pf.getProfile_id());
				}
				return false;
			}
		});
          
       
        //registering popup with OnMenuItemClickListener
        

        popup.show();//showing popup menu  
	}

}
