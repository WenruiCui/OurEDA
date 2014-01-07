package com.Until;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Until_Save_Read extends Activity {

	public static String read(Context context, String name, String key) {
		SharedPreferences sp = context.getSharedPreferences(name, MODE_PRIVATE);
		String result = sp.getString(key, "");
		System.out.println(result);
		return result;
	}

	public static int IsCheck(Context context, String name) {
		SharedPreferences sp = context.getSharedPreferences(name, MODE_PRIVATE);
		String tag = sp.getString("TAG", "");
		System.out.println(tag);
		if (tag.equals("y"))
			return 1;
		else
			return 0;
	}
}
