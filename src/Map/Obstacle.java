package Map;

import processing.core.PApplet;
import processing.core.PImage;
import kim.shapes.Line;
import kim.shapes.Rectangle;

public class Obstacle extends Rectangle
{
   // private PImage img;
    
    private Line[] borders = new Line[4];
    /**
     * creates a new obstacle with opposite corners at x,y, x2,y2
     * @param x x coord of corner 1
     * @param y y coord of corner 1
     * @param x2 x coord of corner 2
     * @param y2 y coord of corner 2
     */
    public Obstacle(double x, double y, double x2, double y2)
    {
	super(x, y, x2, y2);
	borders[0] = new Line(x,y,x2,y);
	borders[1] = new Line(x,y,x,y2);
	borders[2] = new Line(x,y2,x2,y2);
	borders[3] = new Line(x,y,x,y2);
    }
    /**
     * draws the obstacle with PApplet 
     */
    public void draw(PApplet drawer)
    {
	/*
	borders[0] = new Line(x,y,x2,y);
	borders[1] = new Line(x,y,x,y2);
	borders[2] = new Line(x,y2,x2,y2);
	borders[3] = new Line(x,y,x,y2);
	*/
	super.draw(drawer);
    }
}
