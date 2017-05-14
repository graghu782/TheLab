package Gameplay;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;

public class Player 
{
	private double x, y, width, length;
	private Color color;
	private String name;
	private double xCenter, yCenter;
	private Arm arm;
	double direction;
	
	
	public Player(double x, double y, String name) 
	{
		this.x = x;
		this.y = y;
		
		yCenter = y;
		xCenter = x;
		
		width = 50;
		length = 90;
		this.name = name; 
		arm = new Arm(x,y,x,y);
		direction = 0;
		
	}
	
	public void move(double x, double y) 
	{
		this.x += x;
		this.y += y;	
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getWidth()
	{
		return width;
	}
	
	public double getLength()
	{
		return length;
	}
	
	public double getDir()
	{
		return direction;
	}

	public void draw(PApplet drawer)
	{
		
		drawer.rect((float)(drawer.width/2 - width/2), (float)(drawer.height/2 - length/2), (float)width, (float)length);
		
		double mouseXChange = drawer.mouseX - drawer.width/2;
		double mouseYChange = drawer.mouseY - drawer.height/2;
		
		//System.out.println(mouseXChange + " " + mouseYChange);
		
		direction = Math.atan(mouseYChange/mouseXChange);
		if(mouseXChange < 0)
		{
			direction += Math.PI;
		}
		
		arm.setmx(direction);
		arm.setmy(direction);
		arm.draw(drawer);
		
	}
	
}
