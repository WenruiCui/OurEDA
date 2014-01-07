package com.Service;

import java.util.Timer;






import com.xuexiba.Start_Learn;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.TextView;

public class LockService extends Service {

	private Timer mTimer;
	public static final int FOREGROUND_ID = 0;
	private long wtime;
	
	private void startTimer() {

		if (mTimer == null) {

			mTimer = new Timer();

			LockTask lockTask = new LockTask(this);

			mTimer.schedule(lockTask, 0L, 1000L);

		}

	}

	public IBinder onBind(Intent intent) {

		return null;

	}

	public void onCreate() {

		System.out.println("startServers===========================================");
		super.onCreate();
		System.out.println("倒计时开始！！！！！！！！！！！！！！！！！！！！");
		startForeground(FOREGROUND_ID, new Notification());
		Context ctx = LockService.this;       
        SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
    	System.out.println("到这了吧1");
        String wholetime=sp.getString("wholetime","");
        wtime =Long.parseLong(wholetime);
        
        System.out.println("到这了吧2");
		new CountDownTimer( wtime, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				System.out.println("到这了吧3");
				 Context ctx = LockService.this;       
			        SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
			        //存入数据
			        Editor editor = sp.edit();
			        editor.putString("nowtime", ""+millisUntilFinished);
			        editor.commit();	
			}

			@Override
			public void onFinish() {
				System.out
						.println("关闭服务@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				
				 Context ctx = LockService.this;       
			        SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
			        //存入数据
			        Editor editor = sp.edit();
			        editor.putString("wholetime", ""+0);
			        editor.commit();	
				
				stopSelf();
				Intent intent = new Intent();
				// intent.setClass(mContext,MAinActivity.class);
				intent.setClassName("com.xuexiba", "com.xuexiba.After_Learn_dialog");

				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				startActivity(intent);
			}
		}.start();
	}

	public int onStartCommand(Intent intent, int flags, int startId) {

		startTimer();

		return super.onStartCommand(intent, flags, startId);

	}

	public void onDestroy() {

		stopForeground(true);

		mTimer.cancel();

		mTimer.purge();

		mTimer = null;

		super.onDestroy();

	}

}
