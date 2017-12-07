package com.example.iccranking;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;

public class TestActivity extends Activity {

	int arraySize;
	ListView list;
	String[] teamName;
	int[] images = {R.drawable.india, R.drawable.southafrica, R.drawable.england, R.drawable.newzealand,
			R.drawable.australia, R.drawable.srilanka, R.drawable.pakistan, R.drawable.westindies,
			R.drawable.bangladesh, R.drawable.zimbabwe};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_test);
		
		Resources res = getResources();
		teamName = res.getStringArray(R.array.TestcountryName);
		arraySize = images.length;
		
		list = (ListView) findViewById(R.id.listView1);
		
		myAdapter adapter = new myAdapter(this, teamName, images, arraySize);
		list.setAdapter(adapter);
		
	}
}
