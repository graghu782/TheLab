package Gameplay;

import processing.core.PApplet;
import raghu.shapes.Line;

public class Bullet extends raghu.shapes.Line
{ 
	private double direction;
	//private double xCoord, yCoord;
	private double length;
	private double speed;
	double xTemp;
	double yTemp;
	double xo;
	double yo;
	private boolean isRedTeam;//for when we get teams
	
	private Line l1;
	
	private int iters;
	
	public Bullet(double xCoord, double yCoord, double direction, double x, double y)
	{
	    super(xCoord,yCoord,Math.cos(direction),Math.sin(direction));
		this.direction = direction;
		//this.xCoord = xCoord;
		//this.yCoord = yCoord;
		
		length = 100;
		speed = 90;
		iters = 0;
		xo = x;
		yo = y;
		xTemp = Math.cos(direction);
		yTemp = Math.sin(direction);
	}
	
	public void draw(PApplet drawer)
	{
		drawer.strokeWeight(3);
		double xTempr = x + (length * xTemp);
		
		double yTempr = y + (length * yTemp);
		drawer.strokeWeight(1);
		drawer.line((float)x, (float)y, (float)(xTempr), (float)(yTempr));
		
		//draw(drawer);
		
		x = x + (speed * xTemp);
		y = y + (speed * yTemp);
		iters++;
	}
	
	public int getIs()
	{
		return iters;
	}
	
	public void update(double x, double y)
	{
		x+=xo-x;
		xo = x;
		y+=yo-y;
		yo = y;
	}
}
