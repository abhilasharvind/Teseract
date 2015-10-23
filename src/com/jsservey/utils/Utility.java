package com.jsservey.utils;

import android.content.Context;
import android.content.Intent;

public class Utility {
	public static void startActivity(final Context c, Class cl) {
		Intent intent = new Intent(c, cl);
		c.startActivity(intent);
}
}
