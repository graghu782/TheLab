package Gameplay;

import java.awt.Color;
import processing.core.PApplet;

/**
 * This class prints out a visible border that surrounds the walkable map
 * 
 * @author david
 *
 * @param x x-coord of first point
 * @param y y-coord of first point
 * @param x2 x-coord of second point
 * @param y2 y-coord of second point
 */
public class Border 
{
    double x, y, x2, y2;
    public Border(double x, double y, double x2, double y2)
    {
	this.x = x;
	this.y = y;
	this.x2 = x2;
	this.y2 = y2;
    }
    /**
	 * draws the border surrounding points at x,y and x2,y2
	 * @param drawer the PApplet to be used to draw the border
	 */
    public void draw(PApplet drawer)
    {
	drawer.noFill();
	drawer.strokeWeight(10);
	drawer.rect((float)x,(float)y,(float)x2,(float)y2);
	drawer.fill(255);
	drawer.strokeWeight(1);
    }
}
