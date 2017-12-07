package com.example.iccranking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class StartActivity extends Activity {

	Button odiRanking, t20ranking, testRanking;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_start);
		
		t20ranking = (Button) findViewById(R.id.t20ranking);
		odiRanking = (Button) findViewById(R.id.odiRanking);
		testRanking = (Button) findViewById(R.id.testRanking);
		
		odiRanking.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(StartActivity.this, ODIActivity.class);
				startActivity(intent1);
			}
		});
		
		t20ranking.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent(StartActivity.this, T20Activity.class);
				startActivity(intent2);
			}
		});
		
		testRanking.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent3 = new Intent(StartActivity.this, TestActivity.class);
				startActivity(intent3);
			}
		});
	}
}
