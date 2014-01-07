package com.Adapter;

/**
 * @author cwr
 * …Ë÷√≈≈––∞ÒµƒAdapter
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.widget.SimpleAdapter;

import com.Until.Until_Json_to_String;
import com.xuexiba.R;

public class Adapter_Top {

	static public SimpleAdapter Set_Adapter(Context context, String input) throws JSONException {
		SimpleAdapter adapter = null;
		JSONObject json = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		adapter = new SimpleAdapter(context, list, R.layout.item_topshow,
				new String[] { "No", "name", "timeofweek" }, new int[] {
						R.id.tv_showtop_No, R.id.tv_showtop_userID,
						R.id.tv_showtop_time });
		JSONArray arr = new JSONArray(input);
		for (int i = 0; i < arr.length(); i++) {
			try {
				json = (JSONObject) arr.get(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Map<String, Object> mapt = new HashMap<String, Object>();
			mapt.put("No", i + 1);
			mapt.put("name",
					Until_Json_to_String.JSON_To_String(json, "UserID"));
			mapt.put("timeofweek",
					Until_Json_to_String.JSON_To_String(json, "TimeOfWeek"));
			list.add(mapt);
		}
		
		
		return adapter;
	}
}
