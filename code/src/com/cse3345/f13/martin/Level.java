package com.cse3345.f13.martin;


import android.graphics.Canvas;

public class Level {
	private Sling[] slings = new Sling[0];
	private Wall[] walls = new Wall[0];
	private Ball ball;
	private Goal goal;
	public boolean running = true;
	public boolean win = false;
	private int canvasWidth;
	private int canvasHeight;
	
	public Level(int levelNum, int width, int height){
		//setting the canvas canvasWidth and canvasHeight
		canvasWidth = width;
		canvasHeight = height;

		Sling.canvasWidth = width;
		
		//a switch to populate the level members based on the level number that was passed in
		switch(levelNum){
			case 0:
				slings = new Sling[5];
				slings[0] = new Sling(canvasWidth/1, (canvasHeight/1)*2, 3);
				slings[1] = new Sling(canvasWidth/2, (canvasHeight/2)*2, 3); 
				slings[2] = new Sling(canvasWidth/3, (canvasHeight/3)*2, 3); 
				slings[3] = new Sling(canvasWidth/4, (canvasHeight/4)*2, 3); 
				slings[4] = new Sling(canvasWidth/5, (canvasHeight/6)*2, 3); 
				ball = new Ball(canvasWidth/2, canvasHeight/3);
				goal = new Goal(10000, 10000);
				break;
			case 1:
				slings = new Sling[1];
				slings[0] = new Sling(canvasWidth/2, (canvasHeight/3)*2, 0); 
				ball = new Ball(canvasWidth/2, canvasHeight/3);
				goal = new Goal(canvasWidth/2 + 100, canvasHeight/3 + 100);
				break;
			case 2:
				slings = new Sling[2];
				slings[0] = new Sling(canvasWidth/3, (canvasHeight/3)*2, 1); 
				slings[1] = new Sling((canvasWidth/3)*2, (int) ((canvasHeight/3)*1.5), 0); 
				ball = new Ball(canvasWidth/3, canvasHeight/6);
				goal = new Goal((canvasWidth/3)*2, canvasHeight/10 + 100);
				break;
			case 3:
				slings = new Sling[1];
				slings[0] = new Sling(canvasWidth/3, (canvasHeight/3)*2, 1);  
				ball = new Ball(canvasWidth/5, canvasHeight/3);
				goal = new Goal((canvasWidth/5)*4, canvasHeight/3);
				break;
			case 4:
				slings = new Sling[1];
				slings[0] = new Sling(200, 500, 0);
				ball = new Ball(200, 100);
				goal = new Goal(1000, 100);
				break;
			case 5:
				slings = new Sling[2];
				slings[0] = new Sling(200, canvasHeight/2 + 50, 0); 
				slings[1] = new Sling(canvasWidth/2, canvasHeight/2 + 100, 2);
				walls = new Wall[1];
				walls[0] = new Wall(0, canvasHeight/2, canvasWidth/2 - 100, 0);
				ball = new Ball(200, 200);
				goal = new Goal(1150, 300);
				break;
			case 6:
				slings = new Sling[2];
				slings[0] = new Sling(400, 400, 0); 
				slings[1] = new Sling(800, 400, 0);
				walls = new Wall[2];
				walls[0] = new Wall (0, canvasHeight/2 - 200, canvasWidth/2, 0);
				walls[1] = new Wall(canvasHeight/2 - 100, canvasHeight, canvasWidth/2, 0);
				ball = new Ball(400, 100);
				goal = new Goal(800, 300);
				break;
			case 7:
				slings = new Sling[2];
				slings[0] = new Sling(300, 400, 2); 
				slings[1] = new Sling(950, 400, 2);
				walls = new Wall[2];
				walls[0] = new Wall (0, canvasHeight/2 - 200, canvasWidth/2, 0);
				walls[1] = new Wall(canvasHeight/2 - 100, canvasHeight, canvasWidth/2, 0);
				ball = new Ball(300, 100);
				goal = new Goal(950, 300);
				break;
			case 8:
				slings = new Sling[4];
				slings[0] = new Sling(canvasWidth*5/6, canvasHeight - 400, 2); 
				slings[1] = new Sling(canvasWidth*4/6, canvasHeight - 300, 2);
				slings[2] = new Sling(canvasWidth*3/6, canvasHeight - 200, 2);
				slings[3] = new Sling(canvasWidth*1/5, canvasHeight - 100, 2);
				ball = new Ball(canvasWidth*1/5, canvasHeight - 400);
				goal = new Goal(canvasWidth*4/5, canvasHeight - 500);
				break;
			case 9:
				slings = new Sling[3];
				slings[0] = new Sling(canvasWidth*1/8, canvasHeight - 100, 0); 
				slings[1] = new Sling(canvasWidth*3/8, canvasHeight - 100, 0);
				slings[2] = new Sling(canvasWidth*6/8, canvasHeight/2 - 100, 2);
				walls = new Wall[2];
				walls[0] = new Wall(canvasHeight/2 - 100, canvasHeight, canvasWidth*2/4, 0);
				walls[1] = new Wall(canvasHeight/2 - 100, canvasHeight, canvasWidth*1/4, 0);
				ball = new Ball(canvasWidth*1/8, canvasHeight/2 - 200);
				goal = new Goal(canvasWidth*7/8, canvasHeight - 200);
				break;
			case 10:
				slings = new Sling[2];
				slings[0] = new Sling(200, canvasHeight - 100, 0); 
				slings[1] = new Sling(canvasWidth - 200, canvasHeight - 100, 2);
				walls = new Wall[1];
				walls[0] = new Wall(0, canvasHeight/2, canvasWidth/2 - 200, 0);
				ball = new Ball(200, 100);
				goal = new Goal(900, 500);
				break;
		}
		
		ball.setSize(canvasHeight/30);
	}
	
	
	
	//updates and draws all the elements in the level
	public String animate(Canvas canvas){
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
		
		if(ball.update()){
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
