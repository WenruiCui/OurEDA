package com.Until;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Until_Json_Registration extends Activity {

	// ����Json������Ϣ�Ĵ��������������,ע��Ĺ���
	public static JSONObject Put_Resign_Json(String User_ID, String password,
			String name, String classes, String sex, String place) {
		JSONObject resultJson = new JSONObject();
		try {
			resultJson.put("UserID", User_ID);// ����Ϣ��Ӧ����Json����\
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
