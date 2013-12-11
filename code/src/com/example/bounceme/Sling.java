package com.example.bounceme;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Sling {
	private int size = 5;
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private int leftX, rightX, Y;
	private int midX, midY, midX2, midY2;
	private int x, y;
	private int width;
	private double bounce = .0025;
	boolean above = true;
	boolean forced = false;
	private final Type[] types = {new Type(200, .0025, 0xFF4989C8), new Type(500, .001, 0xFFF2642F)};
	private Type type;

	public Sling(int x, int y, int typeNum) {
		type = types[typeNum];
		width = type.width;
		bounce = type.bounce;
		mPaint.setColor(type.color);
		
		midX = midX2= this.x = x;
		Y = midY = midY2 = this.y =  y;
		leftX = midX - width / 2;
		rightX = midX + width / 2;
		
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
		/*x -= 1;
		midX -= 1;
		midX2 -= 1;
		leftX -= 1;
		rightX -= 1;*/
		
		int radius = ball.getRadius() - 2;
		int centerX = ball.getX();
		int centerY = ball.getY();
		double a1, a2, d1, d2;
		a1 =  a2 = d1 = d2 = 0;
		
		//detecting if the ball is above the sling
		if (centerY + radius < Y + 20){
			above = true;
		}else{
			above = false;
		}
		
		
		if(centerY + radius > Y){
			if((centerX > leftX && centerX < rightX) || forced){
				if(forced || above){
					forced = true;
					
					//Mathematical logic for physics on the sling can be found here - "http://www.emanueleferonato.com/2007/08/13/throw-a-ball-with-a-sling-physics-flash-tutorial/"
	                double x1 = centerX - leftX;
					double y1 = centerY - Y;
					double x2 = rightX - centerX;
					double y2 = Y - centerY;
					
					
					d1 = Math.sqrt(x1*x1 + y1*y1);
					d2 = Math.sqrt(x2*x2 + y2*y2);
					
					a1 = Math.atan2(y1, x1);
					a2 = Math.atan2(y2, x2);
					
					midX = (int) (centerX + Math.cos(a1 + Math.PI/2)*radius);
					midY = (int) (centerY + Math.sin(a1 + Math.PI/2)*radius);
					midX2 = (int) (centerX + Math.cos(a2 + Math.PI/2)*radius);
					midY2 = (int) (centerY + Math.sin(a2 + Math.PI/2)*radius);
					
					a1 += Math.sin(radius/d1);
					a2 += Math.sin(radius/d2)*-1.0;
				}
			}
		}else{
			forced = false;
			midX = midX2 = x;
			midY = midY2 = y;
		}
		
		double xacc = 0;
		double yacc = 0;
		
		if(forced){
			Log.d("OUPUT", "into acc change");
			xacc += d1*Math.sin(a2)*bounce;
	        yacc -= d1*Math.cos(a1)*bounce;
	        xacc += d2*Math.sin(a1)*bounce;
	        yacc -= d2*Math.cos(a2)*bounce;
	        
	        Log.d("OUPUT", "(" + xacc + "," + yacc + ")");
	        ball.setAcc(xacc, yacc);
		}
		
		return true;
	}
	
	public class Type{
		public int width;
		public double bounce;
		public int color;
		
		public Type(int w, double b, int green){
			width = w;
			bounce = b;
			color = green;
		}
	}
}
