package Gameplay;

import processing.core.PApplet;
import kim.shapes.Line;

public class Bullet extends Line
{
    // private double xCoord, yCoord;
    private final static double LENGTH = 90;
    private final double SPEED = 40;
    double xTemp;
    double yTemp;
    double direction;
    Player player;

    private int iters;
/**
 * creates a bullet with coordinates at x,y, with direction of direction, belonging to player p
 *  @param x x-coordinate of bullet
 *  @param y y-coordinate of bullet
 *  @param direction direction bullet is traveling
 *  @param p what player the bullet belongs to
 */
    public Bullet(double x, double y, double direction, Player p)
    {
	super(x,y,Math.cos(direction) *LENGTH+x, Math.sin(direction)*LENGTH+y);
	
	player = p;
	this.direction = direction;
	iters = 0;
	xTemp = Math.cos(direction);
	yTemp = Math.sin(direction);
    }
/**
 * draws the bullet
 * @param drawer the PApplet used to draw the bullet
 */
    public void draw(PApplet drawer)
    {
	drawer.stroke(51, 0, 153);
	double mn = -player.getMainX()+drawer.width/2;
	double mb = -player.getMainY()+drawer.height/2;
	
	double xTemp2 =  x + (LENGTH * xTemp);
	double yTemp2 =  y + (LENGTH * yTemp);
	
	super.setPoint2((float) xTemp2, (float) yTemp2);
	
	double xTemp1 = x + mn + (20 * xTemp);
	double yTemp1 = y + mb + (20 * yTemp);
	drawer.strokeWeight(2); 
	drawer.line((float)(x + mn), (float)(y +mb), (float) (xTemp1), (float) (yTemp1));

	//super.draw(drawer);
	{
	x = x + (SPEED * xTemp);
	y = y + (SPEED * yTemp);
	}
	iters++;
	drawer.stroke(0);
    }

    /**
     * returns the player the bullet belongs to
     * @return the player the bullet belongs to
     */
    public Player getPlayer()
    {
	return player;
    }
/**
 * returns the bullet's current iterations
 * @return the bullet's current iterations
 */
    public int getIs()
    {
	return iters;
    }

/**
 * returns the x coordinate
 * @return the x coordinate
 */
    public double getXCoord() 
    {
	return x;
    }
/**
 * returns the y coordinate
 * @return the y coordinate
 */
    public double getYCoord() 
    {
	return y;
    }
/**
 * returns the direction of the bullet
 * @return direction
 */
    public double getDirection() 
    {
	return direction;
    }
    
  
}
