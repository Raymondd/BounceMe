package com.cse3345.f13.martin;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Goal {
	private int xpos, ypos;
	private final int radius = 10;
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	
	public Goal(int x, int y){
		xpos = x;
		ypos = y;
		paint.setColor(0xff119999);
		paint.setStrokeWidth(4);
	}
	
	public boolean update(Ball ball){
		int ballX = ball.getX();
		int ballY = ball.getY();
		int ballRadius = ball.getRadius();
		
		if(		xpos - radius < ballX + ballRadius && 
				xpos + radius > ballX - ballRadius && 
				ypos - radius < ballY + ballRadius && 
				ypos + radius > ballY - ballRadius){
			return false;
			
		}
		
		return true;
	}
	
	public void draw(Canvas can){
		can.drawCircle(xpos, ypos, radius, paint);
	}
	
	
}
