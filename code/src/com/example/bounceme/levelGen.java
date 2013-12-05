package com.example.bounceme;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class levelGen extends Activity {

	MySurfaceView mySurfaceView;
	int i;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mySurfaceView = new MySurfaceView(this);
		setContentView(mySurfaceView);
		i = 0;
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

	class MySurfaceView extends SurfaceView implements Runnable {

		Thread thread = null;
		SurfaceHolder surfaceHolder;
		volatile boolean running = false;
		
		private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		Random random;

		private Level[] levels = new Level[10];
		
		public MySurfaceView(Context context) {
			super(context);
			popLevels();
			surfaceHolder = getHolder();
		}

		public void onResumeMySurfaceView() {
			running = true;
			thread = new Thread(this);
			thread.start();
		}

		public void onPauseMySurfaceView() {
			boolean retry = true;
			running = false;
			while (retry) {
				try {
					thread.join();
					retry = false;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public boolean onTouchEvent(MotionEvent event) {
			levels[i].moveBall(((int) event.getX()));
		    return true;
		}
		
		@Override
		public void run() {
			paint.setColor(0xff000000);
			// TODO Auto-generated method stub
			while (running) {
				if (surfaceHolder.getSurface().isValid()) {
					Canvas canvas = surfaceHolder.lockCanvas();
					
					// we lock the canvas so that we can draw our new items on it

					int w = canvas.getWidth();
					int h = canvas.getHeight();
					
					//drawing a rectangle in the background to erase previous drawings
					canvas.drawRect(0, 0, w, h, paint);
					
					//updating our element's positions and drawing them
					levels[i].animate(canvas, w, h);
					
					

					//we now unlock the canvas and display the newly drawn items
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
		
		private void popLevels(){
			//Populating Level1
			Sling slings[] = { new Sling(400, 400, 200), new Sling(800, 400, 200) };
			Ball ball = new Ball(100, 100);
			levels[0] = new Level(slings, ball);
		}
	}
}
