package Map;

import processing.core.PApplet;
import processing.core.PImage;
import raghu.shapes.Rectangle;

public class Obstacle extends Rectangle
{
    private PImage img;
    
    public Obstacle(double x, double y, double x2, double y2)
    {
	super(x, y, x2, y2);
    }
    
    public void draw(PApplet drawer)
    {
	super.draw(drawer);
    }
}
