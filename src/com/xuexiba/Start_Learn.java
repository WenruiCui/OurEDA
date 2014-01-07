package com.xuexiba;



import com.Service.LockService;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Start_Learn extends Activity {
	 private TimeCount time;
	 private TextView showtime;
	 private long wtime;
	 boolean flag;
	 TextView tv_study_task;
	 
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
		setContentView(R.layout.activity_start__learn);
	
		tv_study_task = (TextView)findViewById(R.id.tv_showtag);
		 
		
		Context ctx = Start_Learn.this;       
        SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
        Editor editor = sp.edit();
        String wholetime=sp.getString("wholetime","");
        String st_task = sp.getString("study_task","");
        tv_study_task.setText(st_task);
        System.out.println("start_learn========================================="+wholetime);
        showtime = (TextView)findViewById(R.id.chrome_time);
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"zsejw.TTF");
		// 应用字体
        //showtime.setTypeface(typeFace);	
        tv_study_task.setTypeface(typeFace);	
        wtime =Long.parseLong(wholetime);
        if(flag==false)
        {
        	flag=true;
        	time = new TimeCount(wtime, 1000);//构造CountDownTimer对象
   	 		time.start();
        }
   	 	
   	 	Button bt = (Button)findViewById(R.id.stop_serv);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
				Intent intent_service= new Intent(Start_Learn.this, LockService.class);
				 Context ctx = Start_Learn.this;       
			        SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
			        //存入数据
			        Editor editor = sp.edit();
			        editor.putString("wholetime", ""+0);
			        editor.commit();	
				stopService(intent_service);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start__learn, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.setClassName("com.xuexiba", "com.xuexiba.Xuexiba_Main");
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(intent);
	}
	
	 /* 定义一个倒计时的内部类 */
		class TimeCount extends CountDownTimer {
		 public TimeCount(long millisInFuture, long countDownInterval) {
		 super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
		}
		 @Override
		 public void onFinish() {//计时完毕时触发
		 flag =false;	
		 }
		 @Override
		 public void onTick(long millisUntilFinished){//计时过程显示
		
			 System.out.println("start_learn到这了1");
			 Context ctx = Start_Learn.this;       
		        SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
		        Editor editor = sp.edit();
		        String nowtime = sp.getString("nowtime","");
		        long nowTime = Long.parseLong(nowtime);
		                        
			 
	     long hour = (nowTime/1000)/3600;
		 long minute = ((nowTime/1000)%3600)/60;
		 long second = (nowTime/1000)%60;
		 System.out.println("start_learn到这了2");
		 showtime.setText(""+hour+":"+minute+":"+second);
		 System.out.println("start_learn到这了3");
		 }
		 }
		}

