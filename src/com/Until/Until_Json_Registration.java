package com.Until;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Until_Json_Registration extends Activity {

	// 构造Json个人信息的串，与服务器交互,注册的过程
	public static JSONObject Put_Resign_Json(String User_ID, String password,
			String name, String classes, String sex, String place) {
		JSONObject resultJson = new JSONObject();
		try {
			resultJson.put("UserID", User_ID);// 把信息对应放入Json串中\
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

	
}
