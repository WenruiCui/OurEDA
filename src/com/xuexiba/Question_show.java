package com.xuexiba;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Question_show extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_show);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question_show, menu);
		return true;
	}

}
