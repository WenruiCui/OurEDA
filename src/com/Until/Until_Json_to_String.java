package com.Until;

import org.json.JSONException;
import org.json.JSONObject;
 
public class Until_Json_to_String {

	// 解析Json串,依照标签返回该标签对应的String
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
