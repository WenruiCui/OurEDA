package com.xuexiba;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.Until.Until_FileName;
import com.Until.Until_Json_Learntime;
import com.Until.Until_Save_Read;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class After_Learn_dialog extends Activity {

	public TextView tv_learntime;
	public TextView tv_isFinish;
	TextView tv_study_time;
	TextView tv_study_task;
	Button bt_share;
	Button bt_no;
	int time;
	JSONObject json;
	String UserID;

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.setClassName("com.xuexiba", "com.xuexiba.Xuexiba_Main");
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
		setContentView(R.layout.dlg_afterlearn);

		tv_study_time = (TextView) findViewById(R.id.tv_afterlearn_showtime);
		tv_study_task = (TextView) findViewById(R.id.tv_timetask);
		// 将字体文件保存在assets/fonts/目录下，www.linuxidc.com创建Typeface对象
		Typeface typeFace = Typeface.createFromAsset(getAssets(), "zsejw.TTF");
		// 应用字体
		tv_study_time.setTypeface(typeFace);
		tv_study_task.setTypeface(typeFace);

		Context ctx = After_Learn_dialog.this;
		SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
		sp.edit();
		final String wholetime = sp.getString("sumtime", "");// //////////////////////////////输出总时间
		String st_task = sp.getString("study_task", "");
		tv_study_task.setText(st_task);
		tv_study_task.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线

		long nowTime = Long.parseLong(wholetime);
		long hour = (nowTime / 1000) / 3600;
		long minute = ((nowTime / 1000) % 3600) / 60;
		tv_study_time.setText(hour + "小时" + minute + "分钟");

		UserID = Until_Save_Read.read(After_Learn_dialog.this, Until_FileName.FILE_LOGIN,
				"UserID");
		tv_learntime.setText(time);
		bt_share = (Button) findViewById(R.id.bt_afterlearn_sure);
		bt_share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				json = Until_Json_Learntime.Put_Learntime_Json(
						UserID.toString(), wholetime);
				ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
				NameValuePair pair = new BasicNameValuePair("Json", json
						.toString());
				param.add(pair);
			}
		});

	}

	public void submit() {

	}
}
