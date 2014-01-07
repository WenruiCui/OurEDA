package com.Until;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Until_Json_Setting extends Activity {

	// 构造Json个人信息的串，与服务器交互,注册的过程
	public static JSONObject Put_Setting_Json(String password, String name,
			String classes, String sex, String place, String UserID) {
		JSONObject resultJson = new JSONObject();
		try {
			resultJson.put("UserID", UserID);
			resultJson.put("Password", password);
			resultJson.put("Name", name);
			resultJson.put("Class", classes);
			resultJson.put("Sex", sex);
			resultJson.put("Place", place);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(resultJson.toString());
		return resultJson;
	}

	public static void save(Context context, JSONObject json, String name,
			String TAG) {

		SharedPreferences sp = context.getSharedPreferences(name, MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("TAG", TAG);
		try {
			editor.putString("Name", json.getString("Name"));
			editor.putString("Class", json.getString("Class"));
			editor.putString("Sex", json.getString("Sex"));
			editor.putString("Place", json.getString("Place"));
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		editor.commit();
		System.out.println("the NAME is" + sp.getString("Name", ""));
	}

}
