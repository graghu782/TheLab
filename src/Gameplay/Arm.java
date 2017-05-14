package Gameplay;

import processing.core.PApplet;

public class Arm 
{
	private double x,y,mx,my;
	private double length;
	
	public Arm (double xx, double xy, double xmx, double xmy)
	{
		length = 50;
		
		x = xx;
		y = xy;
		mx = xmx;
		my = xmy;
	}
	
	public void setmx(double direction)
	{
		mx = length * Math.cos(direction);
	}
	
	public void setmy(double direction)
	{
		my = length * Math.sin(direction);
	}
	
	public void draw(PApplet drawer)
	{
		drawer.strokeWeight(5);
		drawer.line((float)drawer.width/2, (float)drawer.height/2, (float)(drawer.width/2 + mx), (float)(drawer.height/2 + my));
	}
}
