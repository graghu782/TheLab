package Gameplay;

import processing.core.PApplet;

public class Bullet
{ 
	private double direction;
	private double xCoord, yCoord;
	private double length;
	private double speed;
	
	public Bullet(double xCoord, double yCoord, double direction)
	{
		this.direction = direction;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		
		length = 20;
		speed = 25;
	}
	
	public void draw(PApplet drawer)
	{
		drawer.strokeWeight(3);
		drawer.line((float)xCoord, (float)yCoord, (float)(xCoord + length * Math.cos(direction)), (float)(yCoord + length * Math.sin(direction)));
		
		xCoord += speed * Math.cos(direction);
		yCoord += speed * Math.sin(direction);
	}
}
