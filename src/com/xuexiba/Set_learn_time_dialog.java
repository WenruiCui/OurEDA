package com.xuexiba;

import com.Service.LockService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.format.Time;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Set_learn_time_dialog extends Activity {
	Button bt_settimedlg_sure;
	Intent intent;
	Intent intent_service;
	public EditText study_task;
	public Editable stu_task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ���ر���
		setContentView(R.layout.dlg_set_learntime);

		study_task = (EditText) findViewById(R.id.et_settag);
		Typeface typeFace = Typeface.createFromAsset(getAssets(), "zsejw.TTF");
		// Ӧ������
		study_task.setTypeface(typeFace);

		bt_settimedlg_sure = (Button) findViewById(R.id.bt_settimedlg_sure);
		bt_settimedlg_sure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				stu_task = study_task.getText();
				String s_task = stu_task.toString();
				Context ctx = Set_learn_time_dialog.this;
				SharedPreferences sp = ctx.getSharedPreferences("SP",
						MODE_PRIVATE);
				Editor editor = sp.edit();
				String etime = sp.getString("wholetime", "");
				editor.putString("study_task", s_task);
				long wtime = Long.parseLong(etime);
				System.out.println("�����������ʲô!!!!!!!!!!" + wtime);
				TimePicker tp = (TimePicker) findViewById(R.id.time_picker);
				int sethour = tp.getCurrentHour();
				int setminute = tp.getCurrentMinute();
				Time t = new Time(); // or Time t=new Time("GMT+8"); ����Time
										// Zone���ϡ�
				t.setToNow();
				int nowhour = t.hour; // 0-23
				int nowminute = t.minute;
				System.out.println("now hour=" + nowhour + "        minute="
						+ nowminute);
				int hour = sethour >= nowhour ? sethour - nowhour : sethour
						- nowhour + 12;
				int minute;
				if (setminute >= nowminute) // //////////////////////////////���ӽ�λ
					minute = setminute - nowminute;
				else {
					minute = setminute + 60 - nowminute;
					hour--;
					if (hour < 0)
						hour += 12;
				}

				// Context ctx = Set_learn_time_dialog.this;
				// SharedPreferences sp = ctx.getSharedPreferences("SP",
				// MODE_PRIVATE);
				// ��������
				int wholetime = (hour * 60 + minute) * 60000;
				System.out
						.println("set_learn_time========================================="
								+ wholetime);
				// Editor editor = sp.edit();
				editor.putString("wholetime", "" + wholetime);
				editor.putString("sumtime", "" + wholetime); // ///////////////////////////////////////////////////////////////������ʱ��
				editor.commit();

				System.out.println("set hour=" + sethour + "        minute="
						+ setminute);
				System.out.println("hour=" + hour + "        minute=" + minute);

				System.out.println("wholetime=" + wholetime + "       s_task="
						+ s_task);

				if (wholetime != 0 && !s_task.equals("")) {
					Intent intent_service = new Intent(
							Set_learn_time_dialog.this, LockService.class);
					startService(intent_service);
					System.out.println("startService");
					intent = new Intent(Set_learn_time_dialog.this,
							Start_Learn.class);
					startActivity(intent);
					finish();
				} else if (wholetime == 0) {
					Toast.makeText(getApplicationContext(), "������ѧϰʱ��", // ///////////////////������ʾ
							Toast.LENGTH_SHORT).show();
				} else if (s_task.equals("")) {
					Toast.makeText(getApplicationContext(), "������ѧϰ����",// ///////////////////������ʾ
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
