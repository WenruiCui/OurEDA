package com.xuexiba;

/**
 /**
 * @author cwr
 * 排行榜的显示(有网络get方法)，地址在对应的Until_URL里面修改
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
		// 新建一个HttpGet对象
		new Thread() {
			public void run() {
				// TODO Auto-generated method stub
				get();
			}

		}.start();
	}

	void get() {
		HttpGet httpget = new HttpGet(Until_URL.TOP_URL);// 地址
		try {
			// 新建一个HttpResponce对象来接受服务器的返回值
			HttpResponse responce = new DefaultHttpClient().execute(httpget);
			// 接受服务器返回的响应值，如果为200表示连接正常
			if (responce.getStatusLine().getStatusCode() == 200) {
				// 返回的数据类型是HttpEntity类型，需要转换成InputStream再转换为String，具体方法在函数里面，这个可以直接拿来用
				top_string = Until_HttpEntity_to_String.Change(responce);
				System.out.println(top_string);

			} else {
				Toast.makeText(getApplicationContext(), "网络好像出了问题哦",
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
