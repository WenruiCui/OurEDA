package com.xuexiba;

import com.Until.Until_URL;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;

public class Data_activity extends Activity {

	WebView wv;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_data_activity);
		wv = (WebView) findViewById(R.id.web_showData);
		// ����URL
		wv.loadUrl(Until_URL.GET_DATA_URL);
		// ����JS֧��
		wv.getSettings().setJavaScriptEnabled(true);
		// ���Ĺ�������ʽ
		wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		// �Զ�����ͼƬ
		wv.getSettings().setLoadsImagesAutomatically(true);
	}

}
