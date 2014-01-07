package com.Until;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Until_Json_Login extends Activity {

	static public JSONObject Put_Login_Json(String UserID, String Password) {
		JSONObject result = new JSONObject();
		try {
			result.put("UserID", UserID);
			result.put("Password", Password);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result.toString());
		return result;
	}

	public static void save(Context context, JSONObject json, String name,
			String TAG) {

		SharedPreferences sp = context.getSharedPreferences(name, MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("TAG", TAG);
		try {
			editor.putString("UserID", json.getString("UserID"));
			editor.putString("Password", json.getString("Password"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		editor.commit();

	}
}
