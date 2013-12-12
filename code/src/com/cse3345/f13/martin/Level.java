package com.cse3345.f13.martin;


import android.graphics.Canvas;

public class Level {
	private Sling[] slings = new Sling[0];
	private Wall[] walls = new Wall[0];
	private Ball ball;
	private Goal goal;
	public boolean running = true;
	public boolean win = false;
	private int width;
	private int height;
	
	public Level(int levelNum, int w, int h){
		width = w;
		height = h;

		//a switch to populate the level members based on the level number that was passed in
		switch(levelNum){
			case 0:
				slings = new Sling[5];
				slings[0] = new Sling(width/1, (height/1)*2, 3);
				slings[1] = new Sling(width/2, (height/2)*2, 3); 
				slings[2] = new Sling(width/3, (height/3)*2, 3); 
				slings[3] = new Sling(width/4, (height/4)*2, 3); 
				slings[4] = new Sling(width/5, (height/6)*2, 3); 
				ball = new Ball(width/2, height/3);
				goal = new Goal(10000, 10000);
				break;
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
				slings[0] = new Sling(width/4, (height/3)*2, 1);  
				ball = new Ball(width/5, height/3);
				goal = new Goal((width/5)*4, height/3);
				break;
			case 4:
				slings = new Sling[1];
				slings[0] = new Sling(200, 500, 0);
				ball = new Ball(200, 100);
				goal = new Goal(1000, 100);
				break;
			case 5:
				slings = new Sling[2];
				slings[0] = new Sling(200, 500, 0); 
				slings[1] = new Sling(600, 400, 2);
				ball = new Ball(200, 200);
				goal = new Goal(1150, 300);
				break;
			case 6:
				slings = new Sling[2];
				slings[0] = new Sling(400, 400, 0); 
				slings[1] = new Sling(800, 400, 0);
				walls = new Wall[2];
				walls[0] = new Wall (0, height/2 - 200, width/2, 0);
				walls[1] = new Wall(height/2 - 100, height, width/2, 0);
				ball = new Ball(400, 100);
				goal = new Goal(800, 300);
				break;
			case 7:
				slings = new Sling[2];
				slings[0] = new Sling(300, 400, 2); 
				slings[1] = new Sling(950, 400, 2);
				walls = new Wall[2];
				walls[0] = new Wall (0, height/2 - 200, width/2, 0);
				walls[1] = new Wall(height/2 - 100, height, width/2, 0);
				ball = new Ball(300, 100);
				goal = new Goal(950, 300);
				break;
			case 8:
				slings = new Sling[2];
				slings[0] = new Sling(400, 400, 0); 
				slings[1] = new Sling(800, 400, 0);
				ball = new Ball(200, 100);
				goal = new Goal(500, 500);
				break;
			case 9:
				slings = new Sling[2];
				slings[0] = new Sling(400, 400, 0); 
				slings[1] = new Sling(800, 400, 0);
				ball = new Ball(100, 100);
				goal = new Goal(100, 500);
				break;
			case 10:
				slings = new Sling[4];
				slings[0] = new Sling(300, 700, 2); 
				slings[1] = new Sling(1000, 600, 2);
				slings[2] = new Sling(800, 500, 2);
				slings[3] = new Sling(700, 300, 2);
				ball = new Ball(100, 100);
				goal = new Goal(700, 500);
				break;
		}
	}
	
	public String animate(Canvas canvas, int w, int h){
		for(int i = 0; i < slings.length; i++){
			if(slings[i].update(ball)){
				slings[i].draw(canvas);
			}
		}
		
		for(int i = 0; i < walls.length; i++){
			if(walls[i].update(ball)){
				walls[i].draw(canvas);
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
