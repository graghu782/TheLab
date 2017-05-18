package Gameplay;

import processing.core.PApplet;
import kim.shapes.Line;

public class Bullet extends Line
{
    private double direction;
    // private double xCoord, yCoord;
    private final static double LENGTH = 50;
    private final double SPEED = 30;
    double xTemp;
    double yTemp;
    double xo;
    double yo;
    Player player;

    private Line l1;

    private int iters;

    public Bullet(double xCoord, double yCoord, double direction, double x, double y, Player p)
    {
	super(xCoord, yCoord, Math.cos(direction) *LENGTH+xCoord, Math.sin(direction)*LENGTH+yCoord);
	this.direction = direction;
	// this.xCoord = xCoord;
	// this.yCoord = yCoord;
	
	
	
	player = p;

	iters = 0;
	xo = x;
	yo = y;
	xTemp = Math.cos(direction);
	yTemp = Math.sin(direction);
	//super.setPoint2((float)xTemp, (float)yTemp);

	//System.out.println(x + " " + y + " " + xTemp*LENGTH + " " + yTemp*LENGTH);
    }

    public void draw(PApplet drawer)
    {
	double xTempr = x + (LENGTH * xTemp);

	double yTempr = y + (LENGTH * yTemp);
	super.setPoint2((float) xTempr, (float) yTempr);
	
	//drawer.strokeWeight(10);
	//drawer.line((float) x, (float) y, (float) (xTempr), (float) (yTempr));

	super.draw(drawer);

	x = x + (SPEED * xTemp);
	y = y + (SPEED * yTemp);
	iters++;
    }
    
    public Player getPlayer()
    {
	return player;
    }

    public int getIs()
    {
	return iters;
    }

    public void update(double mn, double mb)
    {
	x += xo - mn;
	xo = mn;
	y += yo - mb;
	yo = mb;
    }
}
