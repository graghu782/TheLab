package Gameplay;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;
import raghu.shapes.Line;

public class Player
{
    // private static double mx, my;
    private boolean isMain;
    private double x, y, width, length;
    private Color color;
    private String name;
    private double xCenter, yCenter;
    private Arm arm;
    double direction;
    private int health;
    
    private Line[] lines; //top, right ,bottom, left

    private static double mainX, mainY;

    public Player(double x, double y, String name, boolean isMain)
    {
	this.isMain = isMain;
	this.x = x;
	this.y = y;

	yCenter = y;
	xCenter = x;

	width = 50;
	length = 90;
	this.name = name;
	arm = new Arm(x, y, x, y);
	direction = 0;
	health = 100;
	
	lines = new Line[4];
	
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
    public boolean isHit(Line k1){
	
	return isMain;
	
    }

    public void decHealth(int num)
    {
	health += num;
    }

    public void draw(PApplet drawer)
    {
	if (isMain)
	{
	    mainX = x;
	    mainY = y;
	}
	if (name == "target")
	{
	    drawer.fill(255, 0, 0);
	}
	
	drawer.rect((float) (drawer.width / 2 - mainX + x - width / 2),
		(float) (drawer.height / 2 - mainY + y - length / 2), (float) width, (float) length);

	double mouseXChange = drawer.mouseX - drawer.width / 2;
	double mouseYChange = drawer.mouseY - drawer.height / 2;
	
	l1 = new Line(x,y,x+width,y);
	l2 = new Line(x+width,y,x+width,y+length);	
	l3 = new Line(x,y+length,x+width,y+length);	
	l4 = new Line(x,y,x,y+length);
	
	
	
	// System.out.println(mouseXChange + " " + mouseYChange);

	direction = Math.atan(mouseYChange / mouseXChange);
	if (mouseXChange < 0)
	{
	    direction += Math.PI;
	}

	arm.setmx(direction);
	arm.setmy(direction);
	arm.draw(drawer);

    }

}
