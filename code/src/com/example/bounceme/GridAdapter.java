package com.example.bounceme;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    public String[] mNames;
    
	public GridAdapter(Context c) {
        mContext = c;
        mNames = mContext.getResources().getStringArray(R.array.levelNames);
    }

    public int getCount() {
		return mNames.length;
    }
    
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position){
		// TODO Auto-generated method stub
		return 0;
	}

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	TextView text;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            text = new TextView(mContext);
            //text.setLayoutParams(new GridView.LayoutParams(85, 85));
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(8, 8, 8, 8);
        } else {
            text = (TextView) convertView;
        }

        Log.d("OUTPUT",mNames[position]);
        text.setText(mNames[position]);
        return text;
    }


}
