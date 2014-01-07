package com.xuexiba;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.Until.Until_HttpEntity_to_String;
import com.Until.Until_Json_Ask;
import com.Until.Until_URL;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class Friend_ask extends Activity {
	Button bt_submit;
	EditText et_content;
	JSONObject json;
	String content;
	String UserID;
	private List<NameValuePair> param;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_friend_ask);

		bt_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				content = et_content.getText().toString();
				String title = new String(content.toCharArray(), 0, 10);
				json = Until_Json_Ask.Put_Ask_Json(UserID, title, content);
				new Thread() {
					public void run() {
						Get();
					}
				}.start();
			}
		});
	}

	public void Get() {
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
				String input = Until_HttpEntity_to_String.Change(responce);
				System.out.println("responce inoput=" + input);
				if (input == "1") {
					Toast.makeText(this, "发送成功", Toast.LENGTH_LONG).show();

					Intent intent = new Intent(Friend_ask.this,
							FriendCircle.class);
					startActivity(intent);
					finish();
				} else if (input == "0") {
					Toast.makeText(this, "网络好像出问题了呢", Toast.LENGTH_LONG).show();

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
		}

	}
}
