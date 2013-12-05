package com.example.bounceme;

import android.graphics.Canvas;

public class Level {
	private Sling[] slings;
	private Ball ball;
	
	public Level(Sling[] s, Ball b){
		slings = s;
		ball = b;
	}
	
	public boolean animate(Canvas canvas, int w, int h){
		for(int i = 0; i < slings.length; i++){
			if(slings[i].update(ball)){
				slings[i].draw(canvas);
			}
		}
		
		if(ball.update(w, h)){
			ball.draw(canvas);
		}else{
			ball.restart();
		}
		
		return true;
	}
	
	public void moveBall(int pos){
		ball.onTouch(pos);
	}
}
