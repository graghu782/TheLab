package Gameplay;

import java.awt.Color;
import processing.core.PApplet;
import processing.core.PImage;

public class Player 
{
	private double x, y, width, length;
	private Color color;
	private String name;
	private double xCenter, yCenter;
	private Arm arm;
	private PImage img;
	
	public Player(double x, double y, String name) 
	{
		this.x = x;
		this.y = y;
		
		yCenter = y;
		xCenter = x;
		
		width = 50;
		length = 70;
		this.name = name; 
		arm = new Arm(x,y,x,y);
		
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

	public void draw(PApplet drawer)
	{
		img = drawer.loadImage("Ghost.png");
		drawer.image(img, (float)(drawer.width/2 - width/2), (float)(drawer.height/2 - length/2), (float)width, (float)length);
		
		double mouseXChange = drawer.mouseX - drawer.width/2;
		double mouseYChange = drawer.mouseY - drawer.height/2;
		
		System.out.println(mouseXChange + " " + mouseYChange);
		
		double direction = Math.atan(mouseYChange/mouseXChange);
		
		if(mouseXChange < 0)
		{
			direction += Math.PI;
		}
		
		arm.setmx(direction);
		arm.setmy(direction);
		
		arm.draw(drawer);
	//	arm.setmx(mouseX);
	//	arm.setmy(mouseY);
	}
	
}
