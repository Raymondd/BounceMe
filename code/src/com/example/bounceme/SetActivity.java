package com.example.bounceme;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SetActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		
		//adding the system wide font to this activity
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/shades.ttf");
		ViewGroup parent = ((ViewGroup)getWindow().getDecorView());
		setTypeFace(tf, parent);
		
		
		
		//we read from prefs whether the last choice included acc or not then set the acc button to reflect this choice
		SharedPreferences prefs = this.getSharedPreferences("prefs", Context.MODE_PRIVATE);
		boolean accOn = prefs.getBoolean("acc", false);
		
		ToggleButton acc = (ToggleButton) findViewById(R.id.action);
		acc.setChecked(accOn);
		
		//we write to our prefs each time the choice of accelometer is turned on or off
		acc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) {
		    		SharedPreferences prefs = getBaseContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
		    		Editor editor = prefs.edit();
		    		editor.putBoolean("acc", true);
		    		editor.commit();
		        } else {
		    		SharedPreferences prefs = getBaseContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
		    		Editor editor = prefs.edit();
		    		editor.putBoolean("acc", false);
		    		editor.commit();
		        }
		    }
		});
		
		
		Button reset = (Button) findViewById(R.id.reset);
		
		reset.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SharedPreferences prefs = SetActivity.this.getSharedPreferences("prefs", Context.MODE_PRIVATE);
				Editor editor = prefs.edit();
				editor.putInt("level", 1);
				editor.commit();
			}
		});
		
		
		//back button finishes the activity and goes back to the previous one
		Button back = (Button) findViewById(R.id.back);
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
	}
	
	
	//setting the typeface/font for all items with text in this activity
	public static void setTypeFace(Typeface custom, ViewGroup parent){
		for(int i = 0; i < parent.getChildCount(); i++){
			View v = parent.getChildAt(i);
			if(v instanceof ViewGroup){
				setTypeFace(custom, (ViewGroup) v);
			}else if(v instanceof TextView){
				TextView tv = (TextView) v;
				tv.setTypeface(custom);
				tv.setPaintFlags(tv.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
			}else if(v instanceof Button){
				Button b = (Button) v;
				b.setTypeface(custom);
				b.setPaintFlags(b.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);				
			}
		}
	}
}
