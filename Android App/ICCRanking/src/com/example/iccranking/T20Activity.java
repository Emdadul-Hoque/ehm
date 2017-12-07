package com.example.iccranking;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class T20Activity extends Activity {

	int arraySize;
	ListView list;
	String[] teamName;
	int[] images= {R.drawable.pakistan, R.drawable.newzealand, R.drawable.westindies, R.drawable.england,
			R.drawable.india, R.drawable.southafrica, R.drawable.australia, R.drawable.srilanka, R.drawable.afghanistan,
			R.drawable.bangladesh, R.drawable.scotland, R.drawable.zimbabwe, R.drawable.uae, R.drawable.netherlands,
			R.drawable.noimage, R.drawable.noimage, R.drawable.noimage, R.drawable.ireland};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_t20);
		
		Resources res = getResources();
		teamName = res.getStringArray(R.array.T20countryName);
		arraySize = images.length;
		
		list = (ListView) findViewById(R.id.listView1);
		
		myAdapter adapter = new myAdapter(this, teamName, images, arraySize);
		list.setAdapter(adapter);
	}
}