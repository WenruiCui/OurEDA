package com.Until;

import org.json.JSONException;
import org.json.JSONObject;

public class Until_Json_Zan {
	@SuppressWarnings("null")
	static public JSONObject Put_Zan_Json(String AnswerID) {
		JSONObject result = null;
		try {
			result.put("AnswerID", AnswerID);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
