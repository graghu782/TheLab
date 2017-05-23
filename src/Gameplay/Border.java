package Gameplay;

import java.awt.Color;
import processing.core.PApplet;

public class Border
{
    double x, y, x2, y2;

    /**
     * creates a border that wraps around the playing field
     * 
     * @param x x coord of top left
     * @param y y coord of top left
     * @param x2 x coord of bottom right
     * @param y2 y coord of bottom right
     */
    public Border(double x, double y, double x2, double y2)
    {
	this.x = x;
	this.y = y;
	this.x2 = x2;
	this.y2 = y2;
    }

    /**
     * draws the border surrounding points at x,y and x2,y2
     * 
     * @param drawer the PApplet to be used to draw the border
     */
    public void draw(PApplet drawer)
    {
	drawer.noFill();
	drawer.strokeWeight(10);
	drawer.rect((float) x, (float) y, (float) x2, (float) y2);
	drawer.fill(255);
	drawer.strokeWeight(1);
    }
}
