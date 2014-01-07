package com.Service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

public class AppInfoProvider {
	private PackageManager packageManager;
	public Context mycontext;
	public ApplicationInfo appInfo;

	public AppInfoProvider(Context context) {
		// 鎷垮埌涓�釜鍖呯鐞嗗櫒
		packageManager = context.getPackageManager();
		mycontext = context;
	}

	public List<AppInfo> getAllApps() {
		List<AppInfo> list = new ArrayList<AppInfo>();
		AppInfo myAppInfo;
		// 鑾峰彇鍒版墍鏈夊畨瑁呬簡鐨勫簲鐢ㄧ▼搴忕殑淇℃伅锛屽寘鎷偅浜涘嵏杞戒簡鐨勶紝浣嗘病鏈夋竻闄ゆ暟鎹殑搴旂敤绋嬪�?
		List<PackageInfo> packageInfos = packageManager
				.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
		for (PackageInfo info : packageInfos) {

			myAppInfo = new AppInfo();
			// 鎷垮埌鍖呭悕
			String packageName = info.packageName;
			// 鎷垮埌搴旂敤绋嬪簭鐨勪俊鎭�
			ApplicationInfo appInfo = info.applicationInfo;
			if (filterApp(appInfo)) {// 鎷垮埌搴旂敤绋嬪簭鐨勫浘鏍�
				Drawable icon = appInfo.loadIcon(packageManager);
				// 鎷垮埌搴旂敤绋嬪簭鐨勭▼搴忓�?
				String appName = appInfo.loadLabel(packageManager).toString();
				myAppInfo.setAppName(appName);
				myAppInfo.setPackageName(packageName);
				myAppInfo.setIcon(icon);
				/*
				 * if(filterApp(appInfo)) { myAppInfo.setSystemApp(false); }
				 * else { myAppInfo.setSystemApp(true); }
				 */
				list.add(myAppInfo);
			}
		}
		return list;
	}

	// 鍒ゆ柇鏌愪竴涓簲鐢ㄧ▼搴忔槸涓嶆槸绯荤粺鐨勫簲鐢ㄧ▼搴忥紝濡傛灉鏄繑鍥�?rue锛屽惁鍒欒繑鍥�?alse
	public boolean filterApp(ApplicationInfo info) {
		System.out.println();
		// 鏈変簺绯荤粺搴旂敤鏄彲浠ユ洿鏂扮殑锛屽鏋滅敤鎴疯嚜宸变笅杞戒簡涓�釜绯荤粺鐨勫簲鐢ㄦ潵鏇存柊浜嗗師鏉ョ殑锛�
		// 瀹冭繕鏄郴缁熷簲鐢紝杩欎釜灏辨槸鍒ゆ柇杩欑鎯呭喌鐨�?
		if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
			return true;
		} else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0)// 鍒ゆ柇鏄笉鏄郴缁熷簲鐢�
		{
			return true;
		}
		return false;
	}

	public boolean test(List<AppInfo> apps, String packagename) {
		for (int i = 0; i < apps.size(); i++) {
			// System.out.println(packagename);
			AppInfo a = apps.get(i);
			String pack = a.getPackageName();
			// System.out.println(pack);

			if (pack.equals(packagename)) {
				return true;
			}
		}
		return false;
	}

	public boolean testself(String packagename) {
		String packa = "com.xuexiba";

		if ((packagename.equals(packa))) {
			return true;
		}

		return false;

	}
}