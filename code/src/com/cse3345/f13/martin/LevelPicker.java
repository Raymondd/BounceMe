package com.cse3345.f13.martin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class LevelPicker extends Activity {
	int level;
	Typeface tf;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences prefs = this.getSharedPreferences("prefs", Context.MODE_PRIVATE);
		level = prefs.getInt("level", 1);
		tf = Typeface.createFromAsset(getAssets(), "fonts/shades.ttf");
		
		setContentView(R.layout.level_grid);
		
		//setting the type face for back/timeTrial buttons and giving it a click listener to go back to the previous activity
		Button back = (Button) findViewById(R.id.back);
		//Button time = (Button) findViewById(R.id.timeTrial);
		
		//time.setTypeface(tf);
		back.setTypeface(tf);
		
		/*time.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(LevelPicker.this, LevelGen.class);
            	i.putExtra("level", 0);
            	startActivity(i);
			}
		});*/
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(LevelPicker.this, MenuActivity.class);
				startActivity(i);
			}
		});
		
		//giving grid view an adapter and setting an onclick event listener for its children
		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new GridAdapter(this));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            if(position < level){
	            	Intent i = new Intent(LevelPicker.this, LevelGen.class);
	            	i.putExtra("level", (position + 1));
	            	startActivity(i);
	            }
	        }
	    });
	}
	
	public class GridAdapter extends BaseAdapter {
	    private Context mContext;
	    private String[] mNames;
	    
		public GridAdapter(Context c) {
			mContext = c;
			mNames = mContext.getResources().getStringArray(R.array.levelNames);
	    }

	    public int getCount() {
			return mNames.length;
	    }

	    // create a new ImageView for each item referenced by the Adapter
	    public View getView(int position, View convertView, ViewGroup parent) {
	    	View v;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	        	LayoutInflater i = getLayoutInflater();
	        	v = i.inflate(R.layout.grid_item, null);
	        	Log.d("OUTPUT", "" + position);
	        } else {
	            v = convertView;
	        }
	        
	        TextView name = (TextView) v.findViewById(R.id.name);
	        if(position < level){
	        	name.setText(mNames[position]);
	        	name.setTextColor(getResources().getColor(R.color.white));
	        }else{
	        	name.setText(mNames[position] + " *LOCKED*");
	        	name.setTextColor(getResources().getColor(R.color.blue));
	        }
	        
	        name.setTypeface(tf);
	        return v;
	    }
	    
	    public Object getItem(int arg0) {
	        return null;
	    }


	    public long getItemId(int arg0) {
	        return 0;
	    }


	}
}
