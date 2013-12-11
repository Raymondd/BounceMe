package com.example.bounceme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        */
        
		setContentView(R.layout.activity_menu);
		
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/shades.ttf");
		ViewGroup parent = ((ViewGroup)getWindow().getDecorView());
		setTypeFace(tf, parent);

		
		
		Button play_button = (Button) findViewById(R.id.playButton);
		
		play_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MenuActivity.this, levelPicker.class);
 				startActivity(i);
				
				
			}
		});
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
