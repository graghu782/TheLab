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
    double xo;
    double yo;
    double direction;
    Player player;

    private int iters;

/*
    public Bullet(double xCoord, double yCoord, double direction, double x, double y, Player p)

    {
	super(xCoord, yCoord, Math.cos(direction) *LENGTH+xCoord, Math.sin(direction)*LENGTH+yCoord);
	player = p;
	this.direction = direction;
	iters = 0;
	xo = x;
	yo = y;
	xTemp = Math.cos(direction);
	yTemp = Math.sin(direction);
	//super.setPoint2((float)xTemp, (float)yTemp);

	//System.out.println(x + " " + y + " " + xTemp*LENGTH + " " + yTemp*LENGTH);
    }
    */

    public Bullet(double x, double y, double direction, Player p)

    {
//	super(x + 400-p.getX(),y + 300-p.getY(),  Math.cos(direction) *LENGTH+x, Math.sin(direction)*LENGTH+y);
	//super(400 - p.getMainX() + x , 300 -p.getMainY()+y,  Math.cos(direction) *LENGTH+400 - p.getMainX() + x, Math.sin(direction)*LENGTH+300 -p.getMainY()+y);

	super(x,y,Math.cos(direction) *LENGTH+x, Math.sin(direction)*LENGTH+y);
	
	player = p;
	this.direction = direction;
	iters = 0;
	xo = x;
	yo = y;
	xTemp = Math.cos(direction);
	yTemp = Math.sin(direction);
    }

    public void draw(PApplet drawer)
    {
	drawer.stroke(100, 255, 0);
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

    public Player getPlayer()
    {
	return player;
    }

    public int getIs()
    {
	return iters;
    }

    public double getX()
    {
	return xo;
    }

    public double getY()
    {
	return yo;
    }

    public double getXCoord() 
    {
	return x;
    }

    public double getYCoord() 
    {
	return y;
    }

    public double getDirection() 
    {
	return direction;
    }
    
    public void update(double mn, double mb)
    {
	x += xo - mn;
	xo = mn;
	y += yo - mb;
	yo = mb;
    }
}
