package com.example.bounceme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class levelPicker extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        */
        
		setContentView(R.layout.level_grid);
		
		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new GridAdapter(this));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            Toast.makeText(levelPicker.this, "" + position, Toast.LENGTH_SHORT).show();
				Intent i = new Intent(levelPicker.this, levelGen.class);
 				startActivity(i);
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
	        	TextView name = (TextView) v.findViewById(R.id.name);
	        	Log.d("OUTPUT", "" + position);
	        	name.setText(mNames[position]);
	        } else {
	            v = convertView;
	        }
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
