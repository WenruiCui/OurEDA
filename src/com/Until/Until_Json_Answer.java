package com.Until;

import org.json.JSONException;
import org.json.JSONObject;

public class Until_Json_Answer {

	static public JSONObject Put_Answer_Json(String UesrID, String QID,
			String Acontent) {
		JSONObject result = new JSONObject();
		try {
			result.put("UserID", UesrID);
			result.put("QuestionID", QID);
			result.put("AnswerContent", Acontent);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

}
