package com.example.bounceme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class levelGen extends Activity {
	PlaySurfaceView mySurfaceView;

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
		
		
		Button restart = (Button) findViewById(R.id.restartButton);
		restart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mySurfaceView.reset();
				
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
}
