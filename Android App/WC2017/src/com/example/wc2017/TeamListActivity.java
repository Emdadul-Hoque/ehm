package com.example.wc2017;

import java.lang.reflect.Member;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class TeamListActivity extends Activity implements OnItemClickListener {

	GridView gridView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_team_list);
		
		gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setAdapter(new baseAdapter(this));
		gridView.setOnItemClickListener(this);
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(this, MyActivity.class);
		viewHolder holder = (viewHolder) view.getTag();
		member temp = (member) holder.myCountry.getTag();
		
		intent.putExtra("image",temp.imageID);
		intent.putExtra("country",temp.countryName);
		startActivity(intent);
		
	}
}

class member
{
	int imageID;
	String countryName;
	public member(int imageID, String countryName)
	{
		this.imageID = imageID;
		this.countryName = countryName;
	}
}

class viewHolder
{
	ImageView myCountry;
	viewHolder(View v)
	{
		myCountry = (ImageView) v.findViewById(R.id.imageView1);
	}
}


class baseAdapter extends BaseAdapter
{
	ArrayList<member> memberList;
	Context context;
	public baseAdapter(Context context) {
		this.context = context;
		memberList = new ArrayList<member>();
		Resources resources = context.getResources();
		String[] tempCountryName = resources.getStringArray(R.array.country);
		int[] images = {R.drawable.argentina, R.drawable.belgium, R.drawable.brazil, 
				R.drawable.colombia, R.drawable.costarica, R.drawable.croatia, R.drawable.denmark, 
				R.drawable.egy, R.drawable.england, R.drawable.france, R.drawable.germany, 
				R.drawable.iceland, R.drawable.iran, R.drawable.japan, R.drawable.korea, 
				R.drawable.mexico, R.drawable.morocco, R.drawable.nigeria, R.drawable.panama, 
				R.drawable.poland, R.drawable.portugal, R.drawable.russia, R.drawable.saudi, 
				R.drawable.senegal, R.drawable.serbia, R.drawable.spain, R.drawable.sweden, 
				R.drawable.switzerland, R.drawable.tunisia, R.drawable.uruguay};
		
		for(int i = 0; i < 30; i++) {
			member tempMember = new member(images[i], tempCountryName[i]);
			memberList.add(tempMember);
		}
	}
	
	@Override
	public int getCount() {
		return memberList.size();
	}

	@Override
	public Object getItem(int position) {
		return memberList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		viewHolder holder = null;
		if(row == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			row = layoutInflater.inflate(R.layout.single_item, parent, false);
			holder = new viewHolder(row);
			row.setTag(holder);
		} else {
			holder = (viewHolder) row.getTag();
		}
		member temp = memberList.get(position);
		holder.myCountry.setImageResource(temp.imageID);
		holder.myCountry.setTag(temp);
		
		return row;
	}	
}
