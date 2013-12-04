package com.example.bounceme;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Ball {
	private int radius = 12;
	private final double gravity = .1;
	private final int startX = 145;
	private final int startY = 100;
	private double yacc = gravity;
	private double xacc = 0;
	private double xspeed = 0;
	private double yspeed = 0;
	private int xpos = startX;
	private int ypos = startY;
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private boolean draw = true;
	private int canvasWidth;
	private int canvasHeight;
	
	
	public Ball(){
		mPaint.setColor(0xff00ff00);
	}
	
	public void draw(Canvas can){
		can.drawCircle(xpos, ypos, radius, mPaint);
	}
	
	public boolean update(int width, int height){
		xspeed += xacc;
		yspeed += yacc;
		
		canvasWidth = width;
		canvasHeight = height;
		
		if (xpos > canvasWidth || ypos > canvasHeight){
			return false;
		}
		
		xpos += xspeed;
		ypos += yspeed;
		
		xacc = 0;
		yacc = gravity;
		
		
		if (xspeed > 7){ 
			xspeed = 7;
		}else if(xspeed < -7){
			xspeed = -7;
		}
		
		return true;
	}
	
	public void onTouch(float x){
		if(x < (canvasWidth/2)){
			Log.d("OUTPUT", "LEFT");
			xacc = -1;
			
		}else{
			Log.d("OUTPUT", "RIGHT");
			xacc = 1;
		}
	}
}

