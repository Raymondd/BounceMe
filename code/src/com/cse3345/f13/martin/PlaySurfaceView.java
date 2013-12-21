package com.cse3345.f13.martin;

import java.util.Random;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class PlaySurfaceView extends SurfaceView implements Runnable,
		SensorEventListener {
	private int canvasWidth, canvasHeight;
	private Thread thread = null;
	private SurfaceHolder surfaceHolder;
	private volatile boolean running = false;
	private int touchX = 0;
	private int levelNum;
	private SensorManager sensorManager;
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	Random random;
	private Level level;
	private boolean acc;

	public PlaySurfaceView(Context context, int num) {
		super(context);
		levelNum = num;
		surfaceHolder = getHolder();

		// reading for prefs whether the user wants to use the accelerometer for
		// movement or not
		SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
		acc = prefs.getBoolean("acc", false);

		if (acc) {
			sensorManager = (SensorManager) context.getSystemService("sensor");
		}
	}

	public PlaySurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PlaySurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void onResumeMySurfaceView() {
		running = true;
		thread = new Thread(this);
		thread.start();
		if(acc){
			sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		}
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
		//if we are not using the accelerometer then we check if the screen is being touched
		if (!acc) {
			
			//we get the location of the and the status of it and send to ball class for calculating movement
			int action = event.getAction();
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				//if the touch was on the left we give a negative else we give a poitive indicating movement direction
				if (event.getX() < canvasWidth / 2) {
					touchX = -2;
				} else {
					touchX = 2;
				}
				break;
			case MotionEvent.ACTION_MOVE:
				break;
			case MotionEvent.ACTION_UP:
				touchX = 0;
				break;
			case MotionEvent.ACTION_CANCEL:
				touchX = 0;
				break;
			case MotionEvent.ACTION_OUTSIDE:
				touchX = 0;
				break;
				
			}
		}
		return true;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float x = event.values[1];
		if (x < -2) {
			if (x > -5) {
				touchX = -1;
			} else {
				touchX = -2;
			}
		} else if (x > 2) {
			if (x < 5) {
				touchX = 1;
			} else {
				touchX = 2;
			}
		} else {
			touchX = 0;
		}
	}

	// the physics thread where the magic happens and all objects are drawn and
	// updated
	@Override
	public void run() {
		paint.setColor(getResources().getColor(R.color.orange));

		// TODO Auto-generated method stub
		while (true) {
			if (surfaceHolder.getSurface().isValid()) {
				Canvas canvas = surfaceHolder.lockCanvas();
				level = new Level(levelNum, canvasWidth, canvasHeight);
				surfaceHolder.unlockCanvasAndPost(canvas);
				break;
			}
		}

		while (running) {
			if (surfaceHolder.getSurface().isValid()) {
				Canvas canvas = surfaceHolder.lockCanvas();
				// we lock the canvas so that we can draw our new items on it
				int w = canvas.getWidth();
				int h = canvas.getHeight();

				// check if the screen is currently being touched
				level.moveBall(touchX);

				// drawing a rectangle in the background to erase previous
				// drawings
				canvas.drawRect(0, 0, w, h, paint);

				// updating our element's positions and drawing them
				String result = level.animate(canvas);

				if(result == "win") {
					Context context = getContext(); // from
													// MySurfaceView/Activity
					Intent intent = new Intent(context, WinActivity.class);
					intent.putExtra("level", levelNum);
					context.startActivity(intent);
				}

				// we now unlock the canvas and display the newly drawn items
				surfaceHolder.unlockCanvasAndPost(canvas);
			}

		}
	}

	
	// resets the ball on the level
	public void reset() {
		level.reset();
	}

	
	// accellerometer accuracy change listener
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	
	// sets the initial width and height for the view (also sets the width and
	// height if there is every a change to them)
	public void onSizeChanged(int w, int h, int ow, int oh) {
		canvasWidth = w;
		canvasHeight = h;
		Log.d("OUTPUT", "(" + canvasWidth + "," + canvasHeight + ")");
	}
}