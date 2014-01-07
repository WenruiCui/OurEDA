package com.xuexiba;

/**
 /**
 * @author cwr
 * ���а����ʾ(������get����)����ַ�ڶ�Ӧ��Until_URL�����޸�
 */

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import com.Adapter.Adapter_Top;
import com.Until.Until_HttpEntity_to_String;
import com.Until.Until_URL;

import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

public class TopShow_activity extends Activity {

	ListView list;
	String top_string;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_top_show_activity);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		list = (ListView) findViewById(R.id.ls_top_show);
		// �½�һ��HttpGet����
		new Thread() {
			public void run() {
				// TODO Auto-generated method stub
				get();
			}

		}.start();
	}

	void get() {
		HttpGet httpget = new HttpGet(Until_URL.TOP_URL);// ��ַ
		try {
			// �½�һ��HttpResponce���������ܷ������ķ���ֵ
			HttpResponse responce = new DefaultHttpClient().execute(httpget);
			// ���ܷ��������ص���Ӧֵ�����Ϊ200��ʾ��������
			if (responce.getStatusLine().getStatusCode() == 200) {
				// ���ص�����������HttpEntity���ͣ���Ҫת����InputStream��ת��ΪString�����巽���ں������棬�������ֱ��������
				top_string = Until_HttpEntity_to_String.Change(responce);
				System.out.println(top_string);

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
