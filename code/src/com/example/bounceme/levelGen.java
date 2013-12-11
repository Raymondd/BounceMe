package com.example.bounceme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class levelGen extends Activity {
	PlaySurfaceView mySurfaceView;
	Button restart;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		int levelNum = i.getIntExtra("level", 1);
		
		setContentView(R.layout.play_screen);
		
		FrameLayout surface = (FrameLayout) findViewById(R.id.frame);
		mySurfaceView = new PlaySurfaceView(this, levelNum);
		surface.addView(mySurfaceView);
		
		restart = (Button) findViewById(R.id.restartButton);
		final Button back = (Button) findViewById(R.id.menuButton);
		final Button pop = (Button) findViewById(R.id.popoutButton);
		pop.setText("^");
		pop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Log.d("OUTPUT", "clicked");
				if(pop.getText() == "^"){
					Log.d("OUTPUT", "IN2");
					pop.setText("-");
					back.setVisibility(0);
					restart.setVisibility(0);
				}else{
					pop.setText("^");
					back.setVisibility(4);
					restart.setVisibility(4);
				}
			}
		});
		
		
		
		restart = (Button) findViewById(R.id.restartButton);
		restart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mySurfaceView.reset();
				//((Button)findViewById(R.id.restartButton)).setXXX
				
			}
		});
		
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/shades.ttf");
		ViewGroup parent = ((ViewGroup)getWindow().getDecorView());
		setTypeFace(tf, parent);
		
		Button menu = (Button) findViewById(R.id.menuButton);
		
		menu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}

	public void restart(){
		levelGen.this.runOnUiThread(new Runnable(){
			public void run(){
				if(restart.getVisibility() == 4){
					//restart.setText("HEllO WORLD");
				}
			}
		});
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mySurfaceView.onResumeMySurfaceView();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mySurfaceView.onPauseMySurfaceView();
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
