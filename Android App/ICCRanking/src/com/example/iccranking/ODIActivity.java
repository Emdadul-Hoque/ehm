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

public class ODIActivity extends Activity {

	int arraySize;
	ListView list;
	String[] teamName;
	int[] images = {R.drawable.southafrica, R.drawable.india, R.drawable.australia, R.drawable.england,
			R.drawable.newzealand, R.drawable.pakistan, R.drawable.bangladesh, R.drawable.srilanka,
			R.drawable.westindies, R.drawable.afghanistan, R.drawable.zimbabwe, R.drawable.ireland};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_odi);
		
		Resources res = getResources();
		teamName = res.getStringArray(R.array.countryName);
		arraySize = images.length;
		
		list = (ListView) findViewById(R.id.listView1);
		
		myAdapter adapter = new myAdapter(this, teamName, images, arraySize);
		list.setAdapter(adapter);
	}
}


class myAdapter extends ArrayAdapter<String>
{
	Context context;
	int flags[];
	int rankPrint;
	String[] nameArray;
	public myAdapter(Context c, String[] teamN, int flgs[], int decider) {
		super(c, R.layout.single_row, R.id.textView1, teamN);
		this.context = c;
		this.flags = flgs;
		this.nameArray = teamN;
		this.rankPrint = decider;
	}
	
	class myViewHolder
	{
		ImageView flag;
		TextView tName;
		TextView ranking;
		public myViewHolder(View v) {
			flag = (ImageView) v.findViewById(R.id.imageView1);
			tName = (TextView) v.findViewById(R.id.textView1);
			ranking = (TextView) v.findViewById(R.id.textView2);
		}
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = convertView;
		myViewHolder holder = null;
		
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.single_row, parent, false);
			holder = new myViewHolder(row);
			row.setTag(holder);
		} else {
			holder = (myViewHolder) row.getTag();
		}
		
		int pos = position + 1;
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append(pos);
		String str = sb.toString();
		
		holder.flag.setImageResource(flags[position]);
		holder.tName.setText(nameArray[position]);
		if(rankPrint == 18) {
			holder.ranking.setText("ICC T20 Ranking: " + str);
		} else if(rankPrint == 10) {
			holder.ranking.setText("ICC TEST Ranking: " + str);
		} else if(rankPrint == 12) {
			holder.ranking.setText("ICC ODI Ranking: " + str);
		}
		
		return row;
	}
}
