package Gameplay;

import processing.core.PApplet;
import kim.shapes.Line;

public class Bullet extends Line
{
    private double direction;
    // private double xCoord, yCoord;
    private double length;
    private double speed;
    double xTemp;
    double yTemp;
    double xo;
    double yo;
    Player player;

    private Line l1;

    private int iters;

    public Bullet(double xCoord, double yCoord, double direction, double x, double y, Player p)
    {
	super(xCoord, yCoord, Math.cos(direction), Math.sin(direction));
	this.direction = direction;
	// this.xCoord = xCoord;
	// this.yCoord = yCoord;
	
	player = p;

	length = 50;
	speed = 40;
	iters = 0;
	xo = x;
	yo = y;
	xTemp = Math.cos(direction);
	yTemp = Math.sin(direction);
    }

    public void draw(PApplet drawer)
    {
	drawer.strokeWeight(3);
	double xTempr = x + (length * xTemp);

	double yTempr = y + (length * yTemp);
	drawer.strokeWeight(1);
	drawer.line((float) x, (float) y, (float) (xTempr), (float) (yTempr));

	// draw(drawer);

	x = x + (speed * xTemp);
	y = y + (speed * yTemp);
	iters= 0;
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
