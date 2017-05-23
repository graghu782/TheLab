package Gameplay;

import java.awt.Color;
import java.util.ArrayList;

import org.w3c.dom.css.Rect;

import GUI.Sound;
import processing.core.PApplet;
import processing.core.PImage;
import kim.shapes.Line;
/**
 * a player for the game
 * @author david
 * @param x x coord
 * @param y y coord
 * @param width width of player
 * @param height height of player
 * @param xCenter x center of player
 * @param yCenter y center of player
 * @param direction direction of player
 * @param name name of player
 * @param health health of player
 * @param jNum animation number
 * @param ammo overheat of the gun
 * @param eliminations number of kills
 * @param mouseXChange change of mouse x coord
 * @param mouseYChange change of mouse y coord
 * @param john player image
 * @param lines hitbox of player
 * @param bullets bullets player shot
 * @param hud player hud
 * @param mainX main player x
 * @param mainY main player y
 */
public class Player
{
    // private static double mx, my;
    private boolean isMain;

    private final Sound gunshot = new Sound("gunshots2.wav");
    private double x, y, width, height;
    private double xCenter, yCenter;
    private double direction;
    
    private String name;
    
    private int health;
    private int jnum;
    private int ammo;
    private int eliminations;
    
    double mouseXChange, mouseYChange;

    private PImage john;
    // private rect[] spriteRects;

    private Line[] lines; // top, right ,bottom, left
    private ArrayList<Bullet> bullets;
    private Hud hud;

    private static double mainX, mainY;
/**
 * creates a player at x,y, with name name and checks to see if is main character
 * @param x x coord of player
 * @param y y coord of player
 * @param name name of player
 * @param isMain is Main Character
 */
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
	lines[0] = new Line(0-x,0-y,10-x,10-y);
	lines[1] = new Line(0-x,0-y,10-x,10-y);
	lines[2] = new Line(0-x,0-y,10-x,10-y);
	lines[3] = new Line(0-x,0-y,10-x,10-y);
	bullets = new ArrayList<Bullet>();
	hud = new Hud(name);
	ammo = 16;

	// Sprites
	// spriteRects = new Rect[2];
	// spriteRects[0] = new rect(0,99,41,46);
    }
    /**
     * moves the player by x,y
     * @param x x-change
     * @param y y-change
     */
    public void move(double x, double y)
    {
	this.x += x;
	this.y += y;
    }
    /**
     * moves the player to x,y
     * @param x x-change
     * @param y y-change
     */
    public void moveTo(double x, double y)
    {
	this.x = x;
	this.y = y;
    }
    /**
     * returns main character x
     * @return mainX
     */
    public double getMainX()
    {
	return mainX;
    }
    /**
     * returns main character y
     * @return mainY
     */
    public double getMainY()
    {
	return mainY;
    }
    /**
     * creates a new bullet firing at direction of player
     */
    public void fire()
    {
	//bullets.add(new Bullet(400, 300, direction, x, y, this));

	updateMain();
	bullets.add(new Bullet(x,y,direction,this));
	
	gunshot.play();
    }
    /**
     * changes animation to num
     * @param num animation number
     */
    public void changeAnimation(int num)
    {
	jnum = num;
    }
/**
 * returns the bullets array
 * @return bullets
 */
    public ArrayList<Bullet> getBullets()
    {
	return bullets;
    }
    /**
     * adds a bullet to bullets
     * @param b bullet to add to bullets
     */
    public void addBullet(Bullet b)
    {
	bullets.add(b);
    }
    /**
     * returns name of character	
     * @return name
     */
    public String getName()
    {
	return name;
    }
/**
 *  number of ammo
 * @param num ammo
 */
    public void changeAmmo(int num)
    {
	ammo += num;
    }
    /**
     * ammo of player
     * @return ammo
     */
    public int getAmmo()
    {
	return ammo;
    }
/**
 * returns x coord of player
 * @return x
 */
    public double getX()
    {
	return x;
    }
/**
 * returns y coord of player
 * @return y
 */
    public double getY()
    {
	return y;
    }
/**
 * returns width of player
 * @return width
 */
    public double getWidth()
    {
	return width;
    }
/**
 * returns height of player
 * @return height
 */
    public double getHeight()
    {
	return height;
    }
    /**
     * sets direction to d
     * @param d direction
     */
    public void setDirection(double d)
    {
	direction = d;
    }
/**
 * returns direction of person
 * @return direction
 */
    public double getDirection()
    {
	return direction;
    }
/**
 * checks to see if bullet is hitting player
 * @param b bullet
 * @return true if bullet hits player
 */
    public boolean isHit(Bullet b)
    {
	if (!b.getPlayer().getName().equals(getName()))
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
/**
 * decreases health of player by num
 * @param num num to decrease health by
 */
    public void decHealth(int num)
    {
	health -= num;
    }
    /**
     * sets health to num
     * @param num num to set health to
     */
    public void setHealth(int num)
    {
	health = num;
    }
/**
 * returns health of player
 * @return health
 */
    public int getHealth()
    {
	return health;
    }
    /**
     * updates main characters coordinates
     */
    public void updateMain()
    {
	if (isMain)
	{
	    mainX = x;
	    mainY = y;
	}
    }
/**
 * draws the player
 * @param drawer pApplet used to draw the player
 */
    public void draw(PApplet drawer)
    {
	if (isMain)
	{
	    mouseXChange = drawer.mouseX - drawer.width / 2;
	    mouseYChange = drawer.mouseY - drawer.height / 2;
	}
	
	updateMain();
	double xs = -mainX + drawer.width / 2 - width / 2;
	double ys = -mainY + drawer.height / 2 - height / 2;

	lines[0] = new Line(x, y , x + width , y);
	lines[1] = new Line(x +  width, y, x + width, y + height);
	lines[2] = new Line(x + width, y + height, x, y + height);
	lines[3] = new Line(x , y, x , y  + height);

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
	}
	
	if (bullets.size() > 0)
	{
	    if (bullets.get(0).getIs() >= 12)
		bullets.remove(0);
	}

	direction = Math.atan(mouseYChange / mouseXChange);
	if (mouseXChange < 0)
	{
	    direction += Math.PI;
	}
    }

}
