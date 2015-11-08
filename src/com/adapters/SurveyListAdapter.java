package com.adapters;

import java.util.ArrayList;

import com.abx.jsservey.R;
import com.jsservey.model.Profile;
import com.jsservey.model.Survey;
import com.jsservey.utils.AppPref;

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

public class SurveyListAdapter extends BaseAdapter implements OnClickListener{

	//private String[] result;
	private Context context;
	private int[] imageId;
	private ArrayList<Survey> surveyArray;
	public static int[] prgmImages = { R.drawable.more_button_icon,R.drawable.more_button_icon,R.drawable.more_button_icon,R.drawable.more_button_icon };
	//public static String[] prgmNameList = { "user 1", "user 2", "user 3",	"user 4" };
	private static LayoutInflater inflater = null;

	public SurveyListAdapter(Context context,ArrayList<Survey> surveyArray) {
		// TODO Auto-generated constructor stub
		//this.result = prgmNameList;
		this.context = context;
		this.imageId = prgmImages;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.surveyArray=surveyArray;
	}

	@Override
	public int getCount() {
		return surveyArray.size();
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
		FrameLayout popupMenu;
		ImageView select_tick;
	}
	
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Holder holder = new Holder();
		View rowView;
		rowView = inflater.inflate(R.layout.user_list_raw_layout, null);
		holder.tv = (TextView) rowView.findViewById(R.id.textView1);
		holder.img = (ImageView) rowView.findViewById(R.id.imageView1);
		holder.popupMenu = (FrameLayout) rowView.findViewById(R.id.user_popup_menu);
		holder.img.setOnClickListener(this);
		holder.tv.setText(surveyArray.get(position).getSurvey_name());
		holder.select_tick= (ImageView) rowView.findViewById(R.id.profile_tick);
		holder.img.setImageResource(R.drawable.more_button_icon);
		rowView.setTag(surveyArray.get(position));		
		holder.img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View view) {
				
				PopupMenu popup = new PopupMenu(context, view);  
	            //Inflating the Popup using xml file  
				popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem mt) {
						int id = mt.getItemId();
						if (id == R.id.activate_profile) {
							
							AppPref appPref = new AppPref(null);
							Profile pf= (Profile) view.getTag();
							//Toast.makeText(context, "Profile Activated "+pf.getProfilr_name(), 1000).show();
							//appPref.putString(PrefConstant.ACTIVATED_PROFILE,profileArray.get() );
						}
						return false;
					}
				});
	            popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());  
	           
	            //registering popup with OnMenuItemClickListener
	            
	  
	            popup.show();//showing popup menu  
				
			}
		});
		return rowView;
	}

	@Override
	public void onClick(View view) {
		
	}

	

}

