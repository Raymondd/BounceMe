package com.example.bounceme;

import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


class PlaySurfaceView extends SurfaceView implements Runnable {
	private Thread thread = null;
	private SurfaceHolder surfaceHolder;
	private volatile boolean running = false;
	private boolean touched = false;
	private int touchX = 0;
	private int levelNum;
	
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	Random random;

	private Level level;

	public PlaySurfaceView(Context context, int num) {
		super(context);
		levelNum = num;
		surfaceHolder = getHolder();
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

		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			touched = true;
			touchX = (int) event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			touched = true;
			break;
		case MotionEvent.ACTION_UP:
			touched = false;
			break;
		case MotionEvent.ACTION_CANCEL:
			touched = false;
			break;
		case MotionEvent.ACTION_OUTSIDE:
			touched = false;
			break;
		}

		return true;
	}

	@Override
	public void run() {
		paint.setColor(0xff000000);
		// TODO Auto-generated method stub
		while(true){
			if (surfaceHolder.getSurface().isValid()) {
				Canvas canvas = surfaceHolder.lockCanvas();
				level = new Level(levelNum, canvas.getWidth(), canvas.getHeight());
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
				 
				 //check if the screen is currently being touched
				 if(touched){
					level.moveBall(touchX);
				 }
				 // drawing a rectangle in the background to erase previous drawings 
				 canvas.drawRect(0, 0, w, h, paint);
			 
				 // updating our element's positions and drawing them
				 String result = level.animate(canvas, w, h);
			 
				 if(result == "reset"){
					 
					 
					 
				 }else if(result == "win"){
					 Context context = getContext(); // from MySurfaceView/Activity
					 Intent intent = new Intent(context, WinActivity.class);
					 context.startActivity(intent);
				 }
				 
				 // we now unlock the canvas and display the newly drawn items
				 surfaceHolder.unlockCanvasAndPost(canvas); }
			 
		}
	}
	
	public void reset(){
		level.reset();
	}
}