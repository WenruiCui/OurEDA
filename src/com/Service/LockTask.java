package com.Service;

import java.util.List;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LockTask extends TimerTask {

	public static final String TAG = "LockTask";

	private Context mContext;

	public AppInfoProvider provider;
	public List<AppInfo> apps;
	String selfPackageName = "com.eexample.example.locktest";

	private ActivityManager mActivityManager;

	public LockTask(Context context) {

		mContext = context;

		mActivityManager = (ActivityManager) context
				.getSystemService("activity");
		provider = new AppInfoProvider(context);
		apps = provider.getAllApps();
	}

	@Override
	public void run() {
		System.out.println("run)_(-------------------------");

		ComponentName topActivity = mActivityManager.getRunningTasks(1).get(0).topActivity;

		String packageName = topActivity.getPackageName();

		String className = topActivity.getClassName();

		Log.v(TAG, "packageName" + packageName);

		Log.v(TAG, "className" + className);

		if (provider.test(apps, packageName) && !provider.testself(packageName)) {

			System.out.println(packageName);
			Intent intent = new Intent();

			intent.setClassName("com.xuexiba", "com.xuexiba.Start_Learn");

			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			mContext.startActivity(intent);

		} else {
			// System.out.println("娌℃壘鍒帮紒锛侊紒锛侊紒锛侊紒锛侊紒锛侊紒锛侊紒锛侊紒锛侊紒锛侊紒锛�?;

		}

	}

}
