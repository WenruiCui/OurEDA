package com.xuexiba;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.Until.Until_FileName;
import com.Until.Until_HttpEntity_to_String;
import com.Until.Until_Json_Setting;
import com.Until.Until_Save_Read;
import com.Until.Until_URL;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Seeting extends Activity {

	int TAG = 0;
	Button bt_sure;
	Button bt_no;
	EditText et_name;
	EditText et_password;
	EditText et_class;
	EditText et_place;
	EditText et_sex;
	String name;
	String password;
	String classes;
	String place;
	String sex;
	JSONObject json;
	String UserID;
	private List<NameValuePair> param;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_seeting);

		bt_sure = (Button) findViewById(R.id.btn_setting_click);
		bt_no = (Button) findViewById(R.id.btn_setting_quit);

		et_name = (EditText) findViewById(R.id.et_setting_name);
		et_password = (EditText) findViewById(R.id.et_setting_password);
		et_class = (EditText) findViewById(R.id.et_setting_class);
		et_place = (EditText) findViewById(R.id.et_setting_room);
		et_sex = (EditText) findViewById(R.id.et_setting_sex);

		Typeface typeFace = Typeface.createFromAsset(getAssets(), "zsejw.TTF");

		et_name.setTypeface(typeFace);
		et_password.setTypeface(typeFace);
		et_class.setTypeface(typeFace);
		et_place.setTypeface(typeFace);
		et_sex.setTypeface(typeFace);

		System.out.println("the name is  "
				+ Until_Save_Read.read(Seeting.this, Until_FileName.FILE_INFO,
						"Name"));
		et_name.setText(Until_Save_Read.read(Seeting.this,
				Until_FileName.FILE_INFO, "Name"));
		et_class.setText(Until_Save_Read.read(Seeting.this,
				Until_FileName.FILE_INFO, "Class"));
		et_sex.setText(Until_Save_Read.read(Seeting.this,
				Until_FileName.FILE_INFO, "Sex"));
		et_place.setText(Until_Save_Read.read(Seeting.this,
				Until_FileName.FILE_INFO, "Name"));

		bt_sure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (TAG == 1) {
					name = et_name.getText().toString();
					password = et_password.getText().toString();
					classes = et_class.getText().toString();
					place = et_place.getText().toString();
					sex = et_sex.getText().toString();

					// TODO Auto-generated method stub
					if (name != null && password != null & classes != null
							&& sex != null && place != null) {
						json = Until_Json_Setting.Put_Setting_Json(password,
								name, classes, sex, place, UserID);
						param = new ArrayList<NameValuePair>();
						NameValuePair pair = new BasicNameValuePair("Json",
								json.toString());
						param.add(pair);
						new Thread() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								Sent();
							}
						}.start();
						System.out.println("resign json=" + json.toString());

						et_class.setFocusable(false);
						et_class.setFocusableInTouchMode(false);

						et_name.setFocusable(false);
						et_name.setFocusableInTouchMode(false);

						et_password.setFocusable(false);
						et_password.setFocusableInTouchMode(false);

						et_place.setFocusable(false);
						et_place.setFocusableInTouchMode(false);

						et_sex.setFocusable(false);
						et_sex.setFocusableInTouchMode(false);
					}
				} else {
					bt_sure.setBackgroundResource(R.drawable.btn_setting_sure);
					et_class.setFocusable(true);
					et_class.setFocusableInTouchMode(true);
					et_class.requestFocus();

					et_name.setFocusable(true);
					et_name.setFocusableInTouchMode(true);
					et_name.requestFocus();

					et_password.setFocusable(true);
					et_password.setFocusableInTouchMode(true);
					et_password.requestFocus();

					et_place.setFocusable(true);
					et_place.setFocusableInTouchMode(true);
					et_place.requestFocus();

					et_sex.setFocusable(true);
					et_sex.setFocusableInTouchMode(true);
					et_sex.requestFocus();

					TAG = 1;
				}
			}
		});

		bt_no.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Seeting.this, Xuexiba_Main.class);
				startActivity(intent);
				finish();
			}
		});

	}

	void Sent() {
		StringEntity stren;
		try {
			stren = new StringEntity(json.toString());
			HttpPost post = new HttpPost(Until_URL.SEETING_URL);
			HttpEntity httpentiy = new UrlEncodedFormEntity(param);

			System.out.println("Resign post json=" + stren);
			post.setEntity(httpentiy);
			System.out.println("the message has been sernted");
			HttpResponse responce = new DefaultHttpClient().execute(post);
			int code = responce.getStatusLine().getStatusCode();
			System.out.println("responce code=" + code);
			if (code == 1) {
				String input = Until_HttpEntity_to_String.Change(responce);
				System.out.println("responce inoput=" + input);
				if (input.equals(Until_URL.SUCCEED)) {

					if (input.equals(Until_URL.FAILED)) {
						Toast.makeText(this, "修改失败了", Toast.LENGTH_LONG).show();
					} else {
						Until_Json_Setting.save(Seeting.this, new JSONObject(
								input), Until_FileName.FILE_INFO, "y");
						Toast.makeText(this, "修改成功", Toast.LENGTH_LONG).show();
					}
				}

			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
