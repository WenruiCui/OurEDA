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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Until.Until_Check;
import com.Until.Until_FileName;
import com.Until.Until_HttpEntity_to_String;
import com.Until.Until_Json_Login;
import com.Until.Until_Json_Setting;
import com.Until.Until_Save_Read;
import com.Until.Until_URL;

/**
 * 
 * 登陆
 * 
 * @author cwr
 * 
 */
public class Login extends Activity {
	public Until_Save_Read save = new Until_Save_Read();
	public Until_Json_Login until_json = new Until_Json_Login();
	private Until_Check check = new Until_Check(Login.this);
	private EditText et_UserID;
	private EditText et_password;
	private Button bt_submit;
	private Button bt_resign;
	private String UserID;
	private String Password;
	private List<NameValuePair> param;
	private JSONObject json;
	private String input;
	private int code = 0;
	private HttpResponse responce;
	JSONObject rj;

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		Typeface typeFace = Typeface.createFromAsset(getAssets(), "zsejw.TTF");

		et_UserID = (EditText) findViewById(R.id.et_login_id);
		et_password = (EditText) findViewById(R.id.et_login_password);

		et_UserID.setTypeface(typeFace);// 更改应用的字体
		et_password.setTypeface(typeFace);

		bt_submit = (Button) findViewById(R.id.bt_login_sure);

		bt_resign = (Button) findViewById(R.id.bt_login_resige);

		bt_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				UserID = et_UserID.getText().toString();
				Password = et_password.getText().toString();
				// TODO Auto-generated method stub
				if (check.isConnectingToInternet() == true) {
					if (UserID != null && Password != null) {

						json = Until_Json_Login.Put_Login_Json(
								UserID.toString(), Password.toString());
						param = new ArrayList<NameValuePair>();
						NameValuePair pair = new BasicNameValuePair("Json",
								json.toString());
						param.add(pair);

						new Thread() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								Get();
							}
						}.start();

						while (input == null) {
							Toast.makeText(Login.this, "请稍等",
									Toast.LENGTH_SHORT).show();
						}

						System.out.println("the responce is " + input);
						System.out.println("responce inoput=" + input);
						if (input.equals("\n" + "0" + "\n")) {
							Toast.makeText(Login.this, "请输入正确的密码",
									Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(Login.this, "登陆成功",
									Toast.LENGTH_LONG).show();
							try {
								rj = new JSONObject(input);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Until_Json_Login.save(Login.this, json,
									Until_FileName.FILE_LOGIN, "y");
							Until_Json_Setting.save(Login.this, rj,
									Until_FileName.FILE_INFO, "y");
							Intent intent = new Intent(Login.this,
									Xuexiba_Main.class);
							startActivity(intent);
							finish();
						}

						System.out.println("login json=" + json.toString());
					}
				} else
					Toast.makeText(Login.this, "您的网络连接未打开", Toast.LENGTH_LONG)
							.show();
			}

		});

		bt_resign.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Login.this, Registration.class);
				startActivity(intent);
				finish();
			}
		});
	}

	public void Get() {
		StringEntity stren;
		try {
			stren = new StringEntity(json.toString());
			HttpPost post = new HttpPost(Until_URL.LOGIN_URL);
			HttpEntity httpentiy = new UrlEncodedFormEntity(param);

			System.out.println("Resign post json=" + stren);
			post.setEntity(httpentiy);
			System.out.println("the message has been sernted");
			responce = new DefaultHttpClient().execute(post);
			code = responce.getStatusLine().getStatusCode();
			System.out.println("responce code=" + code);
			if (code == 200) {

				input = Until_HttpEntity_to_String.Change(responce);
				System.out.println("responce code=" + input + "end");
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
