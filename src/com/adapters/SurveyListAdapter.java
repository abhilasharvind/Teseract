package com.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
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

import com.abx.jsservey.R;
import com.interfaces.EditDeleteUpdate_listner;
import com.jsservey.model.Survey;

public class SurveyListAdapter extends BaseAdapter implements OnClickListener{

	//private String[] result;
	private Context context;
	private int[] imageId;
	private ArrayList<Survey> surveyArray;
	public static int[] prgmImages = { R.drawable.more_button_icon,R.drawable.more_button_icon,R.drawable.more_button_icon,R.drawable.more_button_icon };
	//public static String[] prgmNameList = { "user 1", "user 2", "user 3",	"user 4" };
	private static LayoutInflater inflater = null;
	EditDeleteUpdate_listner editDeleteUpdate_listner;
	public SurveyListAdapter(Context context,ArrayList<Survey> surveyArray,EditDeleteUpdate_listner editDeleteUpdate_listner) {
		// TODO Auto-generated constructor stub
		//this.result = prgmNameList;
		this.context = context;
		this.imageId = prgmImages;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.surveyArray=surveyArray;
		this.editDeleteUpdate_listner=editDeleteUpdate_listner;
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
		final View rowView;
		rowView = inflater.inflate(R.layout.user_list_raw_layout, null);
		holder.tv = (TextView) rowView.findViewById(R.id.textView1);
		holder.img = (ImageView) rowView.findViewById(R.id.pop_up);
		holder.popupMenu = (FrameLayout) rowView.findViewById(R.id.user_popup_menu);
		//holder.img.setOnClickListener(this);
		holder.tv.setText(surveyArray.get(position).getSurvey_name());
		holder.select_tick= (ImageView) rowView.findViewById(R.id.profile_tick);
		holder.img.setImageResource(R.drawable.more_button_icon);
		rowView.setTag(surveyArray.get(position));		
		if (surveyArray.get(position).getSurvey_id().equals(surveyArray.get(position).getActivated_survey_id())) {
			holder.select_tick.setVisibility(View.VISIBLE);	
			Log.d("abx", "activated profile hgerer");
		}else{
			holder.select_tick.setVisibility(View.GONE);	
		}
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
				Survey sur= (Survey) rowView.getTag();
				if (id == R.id.activate_profile) {					
					
					
					if (sur!= null) {
						
						editDeleteUpdate_listner.onActivateTaskStart(1,sur.getSurvey_id());
					}else{
						//Toast.makeText(context, "Unable to Activate profile", 1000).show();
					}
					
					//
				}else if(id == R.id.delete_popup){
					editDeleteUpdate_listner.onDeleteTaskStart(1, sur.getSurvey_id());
					
				}else if(id == R.id.edit_popup){
					editDeleteUpdate_listner.onEditTaskStart(1,sur.getSurvey_id());
				}
				return false;
			}
		});
          
       
        //registering popup with OnMenuItemClickListener
        

        popup.show();//showing popup menu  
	}

}

