package com.example.bounceme;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Ball {
	private int radius = 20;
	private final double gravity = .1;
	private int startX;
	private int startY;
	private double yacc = gravity;
	private double xacc = 0;
	private double xspeed = 0;
	private double yspeed = 0;
	private int xpos;
	private int ypos;
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private int canvasWidth;
	private int canvasHeight;
	
	
	public Ball(int x , int y){
		xpos = startX = x;
		ypos = startY = y;
		mPaint.setColor(0xff00ff00);
		mPaint.setStrokeWidth(4);
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
		
		/*if(ypos > canvasHeight){
			ypos = 0;
		}*/
		
		xpos += xspeed;
		ypos += yspeed;
		
		xacc = 0;
		yacc = gravity;
		
		
		//setting terminal velocity in the x and y planes
		if (xspeed > 10){ 
			xspeed = 10;
		}else if(xspeed < -10){
			xspeed = -10;
		}
		
		if (yspeed > 10){ 
			yspeed = 10;
		}else if(yspeed < -10){
			yspeed = -10;
		}
		
		
		return true;
	}
	
	public void onTouch(float x){
		if(x < (canvasWidth/2)){
			xacc = -.2;
		}else{
			xacc = .2;
		}
	}
	
	public void restart(){
		xpos = startX;
		ypos = startY;
		yspeed = 0;
		xspeed = 0;
		yacc = 0;
		xacc = 0;
	}
	
	public void setAcc(double xAdd, double yAdd){
		xacc = xAdd;
		yacc = yAdd;
	}
	
	public int getX(){
		return xpos;
	}
	
	public int getY(){
		return ypos;
	}
	
	public int getRadius(){
		return radius;
	}
}

