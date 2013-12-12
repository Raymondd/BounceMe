package com.cse3345.f13.martin;

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

public class LevelGen extends Activity {
	PlaySurfaceView mySurfaceView;
	Button restart;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//gets the level number to load
		Intent i = getIntent();
		int levelNum = i.getIntExtra("level", 1);
		
		setContentView(R.layout.play_screen);
		
		//adds the sruface view dynamically into the first framlayout of the content view
		FrameLayout surface = (FrameLayout) findViewById(R.id.frame);
		mySurfaceView = new PlaySurfaceView(this, levelNum);
		surface.addView(mySurfaceView);
		
		//makes up buttons in the bottom right expandable
		restart = (Button) findViewById(R.id.restartButton);
		final Button back = (Button) findViewById(R.id.menuButton);
		final Button pop = (Button) findViewById(R.id.popoutButton);
		pop.setText("^");
		pop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//if the button is not expanded which clicked then it does expand
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
		
		
		
		//restart button calls the ball to be reset through the surfaceView
		restart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mySurfaceView.reset();
				//((Button)findViewById(R.id.restartButton)).setXXX
				
			}
		});
		
		//back button finishes the view and goes back to the menu
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		
		//typeface if defined and applied to all text
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/shades.ttf");
		ViewGroup parent = ((ViewGroup)getWindow().getDecorView());
		setTypeFace(tf, parent);
	}
	
	@Override
	protected void onResume() {
		// resumes the srufaeView
		super.onResume();
		mySurfaceView.onResumeMySurfaceView();
	}

	@Override
	protected void onPause() {
		//pauses the surfaceView
		super.onPause();
		mySurfaceView.onPauseMySurfaceView();
	}
	
	//function to set the typeFace for all text in the View
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
