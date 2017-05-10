package Gameplay;

import java.awt.Color;
import processing.core.PApplet;

public class Player 
{
	private double x, y, width, length;
	private Color color;
	private String name;
	private double xcenter, ycenter;
	
	
	public Player(double x, double y, String name) 
	{
		this.x = x;
		this.y = y;
		width = 30;
		length = 70;
		this.name = name;
		ycenter = y;
		xcenter = x;
	}
	
	public void move(double x, double y) 
	{
		this.x += x;
		this.y += y;
		
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getWidth(){
		return width;
	}
	public double getLength(){
		return length;
	}

	public void draw(PApplet drawer) 
	{
		drawer.rect((float)x, (float)y, (float)(width), (float)(length));
	}
	
}
