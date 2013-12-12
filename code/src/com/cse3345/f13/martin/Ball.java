package com.cse3345.f13.martin;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball {
	private int radius = 20;
	private final double gravity = .2;
	private int startX;
	private int startY;
	private double yacc = gravity;
	private double xacc = 0;
	private double xspeed = 0;
	private double yspeed = 0;
	private int xpos;
	private int ypos;
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Paint mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
	private int canvasWidth;
	private int canvasHeight;
	
	
	public Ball(int x , int y){
		xpos = startX = x;
		ypos = startY = y;
		mPaint.setColor(0xFF66BD4A);
		mPaint2.setColor(0xFFF2642F);
	}
	
	public void draw(Canvas can){
		can.drawCircle(xpos, ypos, radius + 5, mPaint2);
		can.drawCircle(xpos, ypos, radius, mPaint);
		xacc = 0;
		yacc = gravity;
	}
	
	public boolean update(int width, int height){
		//update the speeed based on the velocity
		xspeed += xacc;
		yspeed += yacc;
		
		//update the position based on the velocity
		xpos += xspeed;
		ypos += yspeed;
		
		
		
		
		canvasWidth = width;
		canvasHeight = height;
		
		if (xpos > canvasWidth || ypos > canvasHeight){
			return false;
		}
		
		/*//setting terminal velocity in the x and y planes
		if (xspeed > 20){ 
			xspeed = 20;
		}else if(xspeed < -20){
			xspeed = -10;
		}
		
		if (yspeed > 20){ 
			yspeed = 20;
		}else if(yspeed < -20){
			yspeed = -20;
		}*/
		
		
		return true;
	}
	
	public void wallBounce(){
		xspeed = -xspeed;
	}
	
	//how to ball moves when it is touched
	public void onTouch(int x){
		if(x == -1){
			xacc = -.05;
		}else if(x == -2){
			xacc = -.1;
		}else if(x == 1){
			xacc = .05;
		}else if(x == 2){
			xacc = .1;
		}else{
			xacc = 0;
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
		xacc += xAdd;
		yacc += yAdd;
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

