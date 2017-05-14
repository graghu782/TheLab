package Gameplay;

import processing.core.PApplet;

public class Bullet
{ 
	private double direction;
	private double xCoord, yCoord;
	private double length;
	private double speed;
	double xTemp;
	double yTemp;
	double xo;
	double yo;
	private boolean isRedTeam;//for when we get teams
	
	private int iters;
	
	public Bullet(double xCoord, double yCoord, double direction, double x, double y)
	{
		this.direction = direction;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		
		length = 200;
		speed = 100;
		iters = 0;
		xo = x;
		yo = y;
		xTemp = Math.cos(direction);
		yTemp = Math.sin(direction);
	}
	
	public void draw(PApplet drawer)
	{
		drawer.strokeWeight(3);
		double xTempr = xCoord + (length * xTemp);
		
		double yTempr = yCoord + (length * yTemp);
		
		drawer.line((float)xCoord, (float)yCoord, (float)(xTempr), (float)(yTempr));
		
		xCoord = xCoord + (speed * xTemp);
		yCoord = yCoord + (speed * yTemp);
		iters++;
	}
	
	public int getIs()
	{
		return iters;
	}
	
	public void update(double x, double y)
	{
		xCoord+=xo-x;
		xo = x;
		yCoord+=yo-y;
		yo = y;
	}
}
