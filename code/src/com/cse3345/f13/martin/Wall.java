package com.cse3345.f13.martin;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Wall {
	private int top, bot, x, speed;
	private int width = 4;
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	
	public Wall(int top, int bot, int x, int speed){
		this.top = top;
		this.bot = bot;
		this.x = x;
		this.speed = speed;
		paint.setColor(0x55000000);
	}
	
	public void draw(Canvas canvas){
		canvas.drawRect(x-width/2, top, x + width/2, bot, paint);
	}
	
	public boolean update(Ball ball){
		int rad = ball.getRadius();
		int left = ball.getX() - rad;
		int right = ball.getX() + rad;
		int ballTop = ball.getY() - rad;
		int ballBot = ball.getY() + rad;
		
		if(ballTop < bot && ballBot > top){
			if(right > x && left < x){
				ball.wallBounce();
			}
		}
		
		
		return true;
		
	}
}
