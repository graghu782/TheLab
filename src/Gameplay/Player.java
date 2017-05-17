package Gameplay;

import java.awt.Color;
import java.util.ArrayList;

import org.w3c.dom.css.Rect;

import processing.core.PApplet;
import processing.core.PImage;
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
    private PImage player;
   // private rect[] spriteRects;

    private Line[] lines; // top, right ,bottom, left
    private ArrayList<Bullet> bullets;

    private static double mainX, mainY;

    public Player(double x, double y, String name, boolean isMain)
    {
	this.isMain = isMain;
	this.x = x;
	this.y = y;

	yCenter = y;
	xCenter = x;

	width = 50;
	length = 50;
	this.name = name;
	arm = new Arm(x, y, x, y);
	direction = 0;
	health = 100;

	lines = new Line[4];
	bullets = new ArrayList<Bullet>();
	
	//Sprites
	//spriteRects = new Rect[2];
	//spriteRects[0] = new rect(0,99,41,46);
    }

    public void move(double x, double y)
    {
	this.x += x;
	this.y += y;
    }
    
    public void fire()
    {
	bullets.add(new Bullet(400, 300, direction, x, y, this));
	
    }
    public ArrayList<Bullet> getBullets (){
	return bullets;
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

    public boolean isHit(Bullet b)
    {
	if(!b.getPlayer().equals(this))
	{
	    if(b.intersects(lines[0]) || b.intersects(lines[1]) || b.intersects(lines[2]) || b.intersects(lines[3]))
	    {
		return true;
	    }
	    else return false;
	}
	else return false;
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

	lines[0] = new Line(x, y, x + width, y);
	lines[1] = new Line(x + width, y, x + width, y + length);
	lines[2] = new Line(x, y + length, x + width, y + length);
	lines[3] = new Line(x, y, x, y + length);

	// System.out.println(mouseXChange + " " + mouseYChange);
	
	for (Bullet b : bullets)
	{
	    b.draw(drawer);
	    b.update(mainX,mainY);
	}
	if (bullets.size() > 0)
	{
	    if (bullets.get(0).getIs() >= 6)
		bullets.remove(0);
	}

	direction = Math.atan(mouseYChange / mouseXChange);
	if (mouseXChange < 0)
	{
	    direction += Math.PI;
	}

	arm.setmx(direction);
	arm.setmy(direction);
	arm.draw(drawer);
	
	System.out.println(health);
    }

}
