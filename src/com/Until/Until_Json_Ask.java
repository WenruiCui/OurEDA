 package com.Until;

import org.json.JSONException;
import org.json.JSONObject;

public class Until_Json_Ask {

	static public JSONObject Put_Ask_Json(String UesrID, String Qtitle,
			String Qcontent) {
		JSONObject result = new JSONObject();
		try {
			result.put("UserID", UesrID);
			result.put("QuestionTitle", Qtitle);
			result.put("QuestionContent", Qcontent);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

}
