package com.jsservey.utils;

import android.content.Context;
import android.content.Intent;

public class Utility {
	public static void startActivity(final Context c, Class cl) {
		Intent intent = new Intent(c, cl);
		c.startActivity(intent);
}
	public static String getDeviceId(Context context){
		String unique_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
		return  "94465766201111";
	}
}
