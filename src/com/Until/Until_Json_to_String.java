package com.Until;

import org.json.JSONException;
import org.json.JSONObject;
 
public class Until_Json_to_String {

	// ����Json��,���ձ�ǩ���ظñ�ǩ��Ӧ��String
	public static String JSON_To_String(JSONObject jsonObject, String tag) {
		String result = null;
		try {
			result = jsonObject.getString(tag);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
