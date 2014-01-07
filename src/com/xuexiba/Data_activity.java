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
		// 加载URL
		wv.loadUrl(Until_URL.GET_DATA_URL);
		// 开启JS支持
		wv.getSettings().setJavaScriptEnabled(true);
		// 更改滚动条样式
		wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		// 自动加载图片
		wv.getSettings().setLoadsImagesAutomatically(true);
	}

}
