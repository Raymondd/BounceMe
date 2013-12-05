package com.example.bounceme;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Sling {
	private int size = 5;
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private final int leftX, rightX, Y;
	private int midX, midY, midX2, midY2;
	private int x, y;
	private int width;
	private final double bounce = .01;
	boolean above = true;
	boolean forced = false;

	public Sling(int x, int y, int w) {
		width = w;
		midX = midX2= this.x = x;
		Y = midY = midY2 = this.y =  y;
		leftX = midX - width / 2;
		rightX = midX + width / 2;

		mPaint.setColor(0xffff7722);
		mPaint.setStrokeWidth(size);
	}

	public void draw(Canvas can) {
		//Log.d("OUTPUT", "left:(" + midX + "," + midY + ")");
		//Log.d("OUTPUT", "right:(" + midX2 + "," + midY2 + ")");
		can.drawLine(leftX, Y, midX, midY, mPaint);
		can.drawLine(midX2, midY2, rightX, Y, mPaint);
		can.drawCircle(leftX, Y, 10, mPaint);
		can.drawCircle(rightX, Y, 10, mPaint);
	}

	public boolean update(Ball ball){
		int radius = ball.getRadius() - 5;
		int centerX = ball.getX();
		int centerY = ball.getY();
		double a1, a2, d1, d2;
		a1 =  a2 = d1 = d2 = 0;
		
		midX = midX2 = x;
		midY = midY2 = y;
		
		//detecting if the ball is above the sling
		if (centerY + radius < Y + 20){
			above = true;
		}else{
			above = false;
		}
		
		
		if(centerY + radius > Y - 2 && centerX > leftX && centerX < rightX){
			if(forced || above){
				Log.d("OUTPUT", "bottom: " + (centerY + radius) + "/ Y: " + Y);
				forced = true;
				
				//Mathematical logic for physics on the sling can be found here - "http://www.emanueleferonato.com/2007/08/13/throw-a-ball-with-a-sling-physics-flash-tutorial/"
                int x1 = centerX - leftX;
				int y1 = centerY - Y;
				int x2 = rightX - centerX;
				int y2 = Y - centerY;
				
				
				d1 = Math.sqrt(x1*x1 + y1*y1);
				d2 = Math.sqrt(x2*x2 + y2*y2);
				
				a1 = Math.atan2(y1, x1);
				a2 = Math.atan2(y2, x2);
				
				midX = (int) (centerX + Math.cos(a1 + 3.14/2)*radius);
				midY = (int) (centerY + Math.sin(a1 + 3.14/2)*radius);
				midX2 = (int) (centerX + Math.cos(a2 + 3.14/2)*radius);
				midY2 = (int) (centerY + Math.sin(a2 + 3.14/2)*radius);
				
				a1 += Math.sin(radius/d1);
				a2 += -(Math.sin(radius/d2));
			}
		}else{
			forced = false;
		}
		
		double xacc = 0;
		double yacc = 0;
		
		if(forced){
			xacc += d1*Math.sin(a2)*bounce;
	        yacc -= d1*Math.cos(a1)*bounce;
	        xacc += d2*Math.sin(a1)*bounce;
	        yacc -= d2*Math.cos(a2)*bounce;
	        
	        ball.setAcc((int) xacc, (int) yacc);
		}
		
		return true;
	}
}
