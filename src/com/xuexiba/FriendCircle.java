package com.xuexiba;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.Adapter.Adapter_FriendCircle;
import com.Until.Until_HttpEntity_to_String;
import com.Until.Until_URL;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class FriendCircle extends Activity {
	String result;
	Button bt_ask;
	ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_friend_circle_main);
		bt_ask = (Button) findViewById(R.id.btn_friendcircle_ask);
		list = (ListView) findViewById(R.id.ls_show_friendcircle);
		new Thread() {
			public void run() {
				get();
			}

		}.start();
		//list.setAdapter(Adapter_FriendCircle.Set_Adapter(this, result));

		bt_ask.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FriendCircle.this, Friend_ask.class);
				startActivity(intent);

			}
		});
	}

	void get() {
		HttpGet httpget = new HttpGet(Until_URL.ALL_QUESTION_URL);// ��ַ
		try {
			// �½�һ��HttpResponce���������ܷ������ķ���ֵ
			HttpResponse responce = new DefaultHttpClient().execute(httpget);
			// ���ܷ��������ص���Ӧֵ�����Ϊ200��ʾ��������
			if (responce.getStatusLine().getStatusCode() == 200) {
				// ���ص�����������HttpEntity���ͣ���Ҫת����InputStream��ת��ΪString�����巽���ں������棬�������ֱ��������
				result = Until_HttpEntity_to_String.Change(responce);
				System.out.println(result);

			} else {
				Toast.makeText(getApplicationContext(), "��������������Ŷ",
						Toast.LENGTH_LONG).show();
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
