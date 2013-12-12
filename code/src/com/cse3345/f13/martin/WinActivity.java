package com.cse3345.f13.martin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class WinActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.win);
		
		Intent i = getIntent();
		int levelNum = i.getIntExtra("level", 1);
		
		SharedPreferences prefs = this.getSharedPreferences("prefs", Context.MODE_PRIVATE);
		int mlevel = prefs.getInt("level", 1);
		
		//write to the new level only if the level beat is higher then the previously highest level beat
		if(mlevel < (levelNum + 1)){
			Editor editor = prefs.edit();
			editor.putInt("level", levelNum + 1);
			editor.commit();
		}
		
		Button back = (Button) findViewById(R.id.back);
		
		back.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(WinActivity.this, LevelPicker.class);
 				startActivity(i);
			}
		});
		
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/shades.ttf");
		ViewGroup parent = ((ViewGroup)getWindow().getDecorView());
		setTypeFace(tf, parent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

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