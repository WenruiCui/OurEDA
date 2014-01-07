package com.xuexiba;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class Welcome extends Activity {

	Button bt_login;
	Button bt_resign;
	Button bt_try;

	Intent intent1;
	Intent intent2;
	Intent intent3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Context ctx = Welcome.this;       /////////////////////////////////////////////////////////
        SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
        //¥Ê»Î ˝æ›
        Editor editor = sp.edit();
        editor.putString("wholetime", "0");
        editor.commit();
		bt_login = (Button) findViewById(R.id.btn_welcome_login);
		bt_resign = (Button) findViewById(R.id.btn_welcome_resign);
		bt_try = (Button) findViewById(R.id.btn_welcome_try);

		intent1 = new Intent(Welcome.this, Xuexiba_Main.class);
		intent2 = new Intent(Welcome.this, Login.class);
		intent3 = new Intent(Welcome.this, Registration.class);

		bt_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(intent2);
				//finish();
			}
		});
		bt_resign.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(intent3);
				//finish();
			}
		});
		bt_try.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(intent1);
				//finish();
			}
		});
	}
}
