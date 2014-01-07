package com.xuexiba;

import com.Until.Until_Check;
import com.Until.Until_Save_Read;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class Xuexiba_Main extends Activity {
	Until_Check check = new Until_Check(Xuexiba_Main.this);
	Intent intent;
	Button bt_about;
	Button bt_data;
	Button bt_friend;
	Button bt_top;
	Button bt_startlearn;
	Button bt_seeting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_xuexiba__main);
		bt_about = (Button) findViewById(R.id.bt_about); // ��ʾ�����ĸ���ť
		bt_data = (Button) findViewById(R.id.bt_data); // ���Ͽ�
		bt_friend = (Button) findViewById(R.id.bt_friend);// ����Ȧ
		bt_top = (Button) findViewById(R.id.bt_top); // ���а�
		bt_startlearn = (Button) findViewById(R.id.bt_startlearn); // ��ʼѧϰ
		bt_seeting = (Button) findViewById(R.id.bt_seeting); // ����

		bt_about.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intent = new Intent(Xuexiba_Main.this, About.class);
				startActivity(intent);
			}
		});

		bt_data.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (check.isConnectingToInternet() == true) {
					intent = new Intent(Xuexiba_Main.this, Data_activity.class);
					startActivity(intent);
					// finish();
				} else {
					Toast.makeText(Xuexiba_Main.this, "����������������",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		bt_friend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (check.isConnectingToInternet() == true) {
					intent = new Intent(Xuexiba_Main.this, FriendCircle.class);
					startActivity(intent);
					// finish();
				} else {
					Toast.makeText(Xuexiba_Main.this, "����������������",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		bt_seeting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println(Until_Save_Read.IsCheck(Xuexiba_Main.this,
						"TAG"));
				// if (Until_Save_Read.IsCheck(Xuexiba_Main.this, "TAG") == 1) {
				intent = new Intent(Xuexiba_Main.this, Seeting.class);
				startActivity(intent);
				// finish();
				// } else {
				// intent = new Intent(Xuexiba_Main.this, Login.class);
				// startActivity(intent);
				// finish();

				// }
			}
		});
		bt_top.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (check.isConnectingToInternet() == true) {
					intent = new Intent(Xuexiba_Main.this,
							TopShow_activity.class);
					startActivity(intent);
					// finish();
				} else {
					Toast.makeText(Xuexiba_Main.this, "����������������",
							Toast.LENGTH_LONG).show();

				}
			}
		});
		bt_startlearn.setOnClickListener(new OnClickListener() { // ///////////////////////////////////////////////////////////

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Context ctx = Xuexiba_Main.this;
						SharedPreferences sp = ctx.getSharedPreferences("SP",
								MODE_PRIVATE);
						Editor editor = sp.edit();
						String etime = sp.getString("wholetime", "");
						long wtime = Long.parseLong(etime);
						System.out.println("�����������ʲô!!!!!!!!!!" + wtime);
						if (wtime == 0)// ///////////////////////��ѯѧϰʱ��
										// �������ѧϰ��ֱ������start_learn,��������set_learn_time
						{
							intent = new Intent(Xuexiba_Main.this,
									Set_learn_time_dialog.class);
							startActivity(intent);
						} else {
							intent = new Intent(Xuexiba_Main.this,
									Start_Learn.class);
							startActivity(intent);
						}
					}
				});
	}

}
