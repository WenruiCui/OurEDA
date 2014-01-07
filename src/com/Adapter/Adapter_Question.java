package com.Adapter;

/**
 * 问题详细界面的Adapter类
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.widget.SimpleAdapter;

import com.Until.Until_Json_to_String;
import com.xuexiba.R;

public class Adapter_Question {
	@SuppressWarnings("null")
	static public SimpleAdapter Set_Adapter(Context context, String input) {
		String[] str = input.split("#");
		JSONObject[] json = null;
		for (int i = 0; i < str.length; i++) {
			try {
				json[i] = new JSONObject(str[i]);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SimpleAdapter adapter = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		adapter = new SimpleAdapter(context, list,
				R.layout.item_friendcircleshow, new String[] { "id", "title" },
				new int[] { R.id.ls_it_friendshow_id,
						R.id.ls_it_friendshow_content });
		for (int i = 0; i < 10; i++) {
			Map<String, Object> mapt = new HashMap<String, Object>();
			mapt.put("id",
					Until_Json_to_String.JSON_To_String(json[i], "UserID"));
			mapt.put("title", Until_Json_to_String.JSON_To_String(json[i],
					"QuestionContent"));
			list.add(mapt);
		}
		return adapter;
	}
}
