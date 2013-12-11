package com.example.bounceme;

import android.graphics.Canvas;

public class Level {
	private Sling[] slings;
	private Ball ball;
	private Goal goal;
	public boolean running = true;
	public boolean win = false;
	private int width;
	private int height;
	
	public Level(int levelNum, int w, int h){
		width = w;
		height = h;

		switch(levelNum){
			case 1:
				slings = new Sling[1];
				slings[0] = new Sling(width/2, (height/3)*2, 0); 
				ball = new Ball(width/2, height/3);
				goal = new Goal(width/2 + 100, height/3 + 100);
				break;
			case 2:
				slings = new Sling[2];
				slings[0] = new Sling(width/3, (height/3)*2, 1); 
				slings[1] = new Sling((width/3)*2, (int) ((height/3)*1.5), 0); 
				ball = new Ball(width/3, height/6);
				goal = new Goal((width/3)*2, height/10 + 100);
				break;
			case 3:
				slings = new Sling[1];
				slings[0] = new Sling(width/5, (height/3)*2, 1);  
				ball = new Ball(width/5, height/3);
				goal = new Goal((width/5)*4, height/3);
				break;
			case 4:
				slings = new Sling[2];
				slings[0] = new Sling(400, 400, 0); 
				slings[1] = new Sling(800, 400, 0);
				ball = new Ball(100, 100);
				goal = new Goal(500, 500);
				break;
			case 5:
				slings = new Sling[2];
				slings[0] = new Sling(400, 400, 0); 
				slings[1] = new Sling(800, 400, 0);
				ball = new Ball(100, 100);
				goal = new Goal(500, 500);
				break;
			case 6:
				slings = new Sling[2];
				slings[0] = new Sling(400, 400, 0); 
				slings[1] = new Sling(800, 400, 0);
				ball = new Ball(100, 100);
				goal = new Goal(500, 500);
				break;
			case 7:
				slings = new Sling[2];
				slings[0] = new Sling(400, 400, 0); 
				slings[1] = new Sling(800, 400, 0);
				ball = new Ball(100, 100);
				goal = new Goal(500, 500);
				break;
			case 8:
				slings = new Sling[2];
				slings[0] = new Sling(400, 400, 0); 
				slings[1] = new Sling(800, 400, 0);
				ball = new Ball(100, 100);
				goal = new Goal(500, 500);
				break;
			case 9:
				slings = new Sling[2];
				slings[0] = new Sling(400, 400, 0); 
				slings[1] = new Sling(800, 400, 0);
				ball = new Ball(100, 100);
				goal = new Goal(500, 500);
				break;
			case 10:
				slings = new Sling[2];
				slings[0] = new Sling(400, 400, 0); 
				slings[1] = new Sling(800, 400, 0);
				ball = new Ball(100, 100);
				goal = new Goal(500, 500);
				break;
		}
	}
	
	public String animate(Canvas canvas, int w, int h){
		for(int i = 0; i < slings.length; i++){
			if(slings[i].update(ball)){
				slings[i].draw(canvas);
			}
		}
		
		if(goal.update(ball)){
			goal.draw(canvas);
		}else{
			return "win";
		}
		
		if(ball.update(w, h)){
			ball.draw(canvas);
		}else{
			return "reset";
		}
		
		return "continue";
	}
	
	public void moveBall(int pos){
		ball.onTouch(pos);
	}
	
	public void reset(){
		ball.restart();
	}
}
