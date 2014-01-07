package com.Until;

import org.json.JSONException;
import org.json.JSONObject;

public class Until_Json_Learntime {

	@SuppressWarnings("null")
	static public JSONObject Put_Learntime_Json(String UserID, String time) {
		JSONObject result = null;
		try {
			result.put("UserID", UserID);
			result.put("Time", time);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

}
