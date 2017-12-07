package com.example.wc2017;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyActivity extends Activity {
	
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my);
		btn = (Button) findViewById(R.id.buttonLast);
		Intent intent = getIntent();
		if(intent != null) {
			int imageID = intent.getIntExtra("image",R.drawable.argentina);
			String countryName = intent.getStringExtra("country");
			ImageView myImage = (ImageView) findViewById(R.id.imageViewLast);
			myImage.setImageResource(imageID);
			TextView textView = (TextView) findViewById(R.id.textViewName);
			textView.setText(countryName);
		}
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
