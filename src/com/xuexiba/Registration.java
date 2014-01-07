package com.xuexiba;

/**
 * 注册
 */

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
import org.json.JSONObject;

import com.Until.Until_FileName;
import com.Until.Until_HttpEntity_to_String;
import com.Until.Until_Json_Registration;
import com.Until.Until_URL;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;

public class Registration extends Activity {
	private EditText et_id;
	private EditText et_password_1;
	private EditText et_password_2;
	private EditText et_name;
	private EditText et_class;
	private EditText et_place;
	private EditText et_sex;
	private Editable id;
	private Editable password_1;
	private Editable password_2;
	private Editable name;
	private Editable classes;
	private Editable place;
	private Editable sex;
	private Button bt_sure;

	private List<NameValuePair> param;

	private String input;
	private JSONObject json;
	String SUCCESS = new String("0");

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_registration);

		Typeface typeface = Typeface.createFromAsset(getAssets(), "zsejw.TTF");

		et_id = (EditText) findViewById(R.id.et_resgin_id);
		et_password_1 = (EditText) findViewById(R.id.et_resgin_password_1);
		et_password_2 = (EditText) findViewById(R.id.et_resgin_password_2);
		et_name = (EditText) findViewById(R.id.et_resgin_name);
		et_class = (EditText) findViewById(R.id.et_resgin_class);
		et_place = (EditText) findViewById(R.id.et_resgin_place);
		et_sex = (EditText) findViewById(R.id.et_resgin_sex);
		bt_sure = (Button) findViewById(R.id.bt_resgin_sure);

		et_id.setTypeface(typeface);
		et_password_1.setTypeface(typeface);
		et_password_2.setTypeface(typeface);
		et_name.setTypeface(typeface);
		et_class.setTypeface(typeface);
		et_place.setTypeface(typeface);

		bt_sure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				id = et_id.getEditableText();
				password_1 = et_password_1.getEditableText();
				password_2 = et_password_2.getEditableText();
				name = et_name.getEditableText();
				classes = et_class.getEditableText();
				place = et_place.getEditableText();
				sex = et_sex.getEditableText();

				if (password_1.toString().equals(password_2.toString()) == false)

				{
					Toast.makeText(Registration.this, "您输入的密码不一致",
							Toast.LENGTH_LONG).show();
					password_1 = null;
					et_password_1.setText(null);
					et_password_2.setText(null);
				}
				if (id != null && password_1 != null & name != null
						&& classes != null && sex != null && place != null) {
					json = Until_Json_Registration.Put_Resign_Json(
							id.toString(), password_1.toString(),
							name.toString(), classes.toString(),
							sex.toString(), place.toString());

					param = new ArrayList<NameValuePair>();
					NameValuePair pair = new BasicNameValuePair("Json", json
							.toString());
					param.add(pair);
					new Thread() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							get();
						}
					}.start();
					while (input == null) {
						Toast.makeText(Registration.this, "请稍等",
								Toast.LENGTH_LONG).show();
					}
					System.out.println(input);
					if (input.equals(Until_URL.SUCCEED)) {
						Toast.makeText(Registration.this, "注册成功",
								Toast.LENGTH_LONG).show();
						Intent intent = new Intent(Registration.this,
								Login.class);
						startActivity(intent);
						finish();
					} else if (input.equals(Until_URL.FAILED)) {

						Toast.makeText(Registration.this, "用户名已存在,请重新输入",
								Toast.LENGTH_LONG).show();
					} else if (input.equals("-1")) {
						Toast.makeText(Registration.this, "好像除了奇怪的问题呢，稍后再尝试吧",
								Toast.LENGTH_LONG).show();
					} else {
						System.out.println("has error");

					}

					System.out.println("resign json=" + json.toString());

				} else {

					Toast.makeText(Registration.this, "您的信息好像木有输入完全呢，清检查一下下",
							Toast.LENGTH_LONG).show();
				}
			}

		});

	}

	public void get() {
		StringEntity stren;
		try {
			stren = new StringEntity(json.toString());
			HttpPost post = new HttpPost(Until_URL.RESIGTOR_URL);
			HttpEntity httpentiy = new UrlEncodedFormEntity(param);

			System.out.println("Resign post json=" + stren);
			post.setEntity(httpentiy);
			System.out.println("the message has been sernted");
			HttpResponse responce = new DefaultHttpClient().execute(post);
			int code = responce.getStatusLine().getStatusCode();
			System.out.println("responce code=" + code);
			if (code == 200) {
				input = Until_HttpEntity_to_String.Change(responce);
				System.out.println("responce inoput=" + input + "end");

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
		}
	}

}
