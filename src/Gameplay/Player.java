package Gameplay;

import java.awt.Color;
import java.util.ArrayList;

import org.w3c.dom.css.Rect;

import GUI.EasySound2;
import processing.core.PApplet;
import processing.core.PImage;
import kim.shapes.Line;

public class Player
{
    // private static double mx, my;
    private boolean isMain;

    private final EasySound2 gunshot = new EasySound2("gunshots2.wav");
    private double x, y, width, height;
    private double xCenter, yCenter;
    private double direction;
    
    private Color color;
    private String name;
    
    private int health;
    private int jnum;
    private int ammo;
    
    double mouseXChange, mouseYChange;

    private PImage john;
    // private rect[] spriteRects;

    private Line[] lines; // top, right ,bottom, left
    private ArrayList<Bullet> bullets;
    private Hud hud;

    private static double mainX, mainY;

    public Player(double x, double y, String name, boolean isMain)
    {

	jnum = 1;
	this.isMain = isMain;
	this.x = x;
	this.y = y;

	yCenter = y;
	xCenter = x;

	width = 50;
	height = 50;
	this.name = name;
	direction = 0;
	health = 100;
	mouseXChange = 1;
	mouseYChange = 0;

	lines = new Line[4];
	bullets = new ArrayList<Bullet>();
	hud = new Hud(name);
	ammo = 16;

	// Sprites
	// spriteRects = new Rect[2];
	// spriteRects[0] = new rect(0,99,41,46);
    }

    public void move(double x, double y)
    {
	this.x += x;
	this.y += y;
	if (jnum != 2)
	{
	    jnum = 2;
	}
	else
	{
	    jnum = 3;
	}

    }
    public double getMainX(){
	return mainX;
    }
    public double getMainY(){
	return mainY;
    }

    public void fire()
    {
	//bullets.add(new Bullet(400, 300, direction, x, y, this));

	//bullets.add(new Bullet(mainX - x+width/2+400, mainY - y+height/2+300, direction, this));
	
	bullets.add(new Bullet(x,y,direction,this));
	
	//gunshot.play();
    }

    public ArrayList<Bullet> getBullets()
    {
	return bullets;
    }
    
    public String getName()
    {
	return name;
    }
    
    public void changeAmmo(int num)
    {
	ammo += num;
    }
    
    public int getAmmo()
    {
	return ammo;
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

    public double getHeight()
    {
	return height;
    }
    
    public void setDirection(double d)
    {
	direction = d;
    }

    public double getDirection()
    {
	return direction;
    }

    public boolean isHit(Bullet b)
    {
	if (!b.getPlayer().equals(this))
	{
	    if (b.intersects(lines[0]) || b.intersects(lines[1]) || b.intersects(lines[2]) || b.intersects(lines[3]))
	    {
		return true;
	    }
	    else
		return false;
	}
	else
	    return false;
    }

    public void decHealth(int num)
    {
	health -= num;
    }
    
    public void setHealth(int num)
    {
	health = num;
    }

    public int getHealth()
    {
	return health;
    }

    public void draw(PApplet drawer)
    {
	if (isMain)
	{
	    mainX = x;
	    mainY = y;

	    mouseXChange = drawer.mouseX - drawer.width / 2;
	    mouseYChange = drawer.mouseY - drawer.height / 2;
	}

	double xs = -mainX + drawer.width / 2 - width / 2;
	double ys = -mainY + drawer.height / 2 - height / 2;

	lines[0] = new Line(x + xs, y + ys, x + width + xs, y + ys);
	lines[1] = new Line(x + xs + width, y + ys, x + width + xs, y + height + ys);
	lines[2] = new Line(x + width + xs, y + ys + height, x + xs, y + height + ys);
	lines[3] = new Line(x + xs, y + ys, x + xs, y + ys + height);

	hud.updateHealth(health);
	hud.draw(drawer, (float)(x + xs), (float)(y + ys));

	drawer.pushMatrix();
	drawer.translate((float) (drawer.width / 2 - mainX + x), (float) (drawer.height / 2 - mainY + y));

	drawer.rotate((float) direction);
	if (jnum == 1)
	{
	    john = drawer.loadImage("john1.png");
	    drawer.image(john, (float) (-width / 2) + 5, (float) (-height / 2) + 2, (float) width - 6,
		    (float) height - 4);
	}
	else if (jnum == 2)
	{
	    john = drawer.loadImage("john2.png");
	    drawer.image(john, (float) (-width / 2), (float) (-height / 2), (float) width, (float) height);
	}
	else
	{
	    john = drawer.loadImage("john3.png");
	    drawer.image(john, (float) (-width / 2), (float) (-height / 2), (float) width, (float) height);
	}


	drawer.rotate((float) -direction);
	drawer.popMatrix();
	drawer.strokeWeight(10);

	for (Bullet b : bullets)
	{
	    b.draw(drawer);
	    b.update(mainX, mainY);
	}
	if (bullets.size() > 0)
	{
	    if (bullets.get(0).getIs() >= 16)
		bullets.remove(0);
	}

	direction = Math.atan(mouseYChange / mouseXChange);
	if (mouseXChange < 0)
	{
	    direction += Math.PI;
	}

	jnum = 1;
    }

}
