package com.jsservey.view.home;

import java.util.ArrayList;

import com.abx.jsservey.R;
import com.jsservey.model.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class UsersListCustomAdapter extends BaseAdapter implements OnClickListener{

	//private String[] result;
	private Context context;
	private int[] imageId;
	private ArrayList<Profile> profileArray;
	public static int[] prgmImages = { R.drawable.more_button_icon,R.drawable.more_button_icon,R.drawable.more_button_icon,R.drawable.more_button_icon };
	public static String[] prgmNameList = { "user 1", "user 2", "user 3",
			"user 4" };
	private static LayoutInflater inflater = null;

	public UsersListCustomAdapter(Context context,ArrayList<Profile> profileArray) {
		// TODO Auto-generated constructor stub
		//this.result = prgmNameList;
		this.context = context;
		this.imageId = prgmImages;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.profileArray=profileArray;
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
		FrameLayout popupMenu;
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
		holder.tv.setText(profileArray.get(position).getProname());
		holder.img.setImageResource(R.drawable.more_button_icon);
		holder.img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				PopupMenu popup = new PopupMenu(context, view);  
	            //Inflating the Popup using xml file  
	            popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());  
	           
	            //registering popup with OnMenuItemClickListener  
	  
	            popup.show();//showing popup menu  
				
			}
		});
		return rowView;
	}

	@Override
	public void onClick(View view) {
		if(view.getId() == R.id.imageView1){
			
		}
	}

}
