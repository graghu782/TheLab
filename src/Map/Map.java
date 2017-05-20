package Map;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Map extends PApplet
{
	private ArrayList<Obstacle> obstacles;
	
	public Map()
	{
		obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Obstacle(400, 0, 420, 800));
	}
	
	public void draw(PApplet drawer)
	{		
		for(Obstacle o : obstacles)
		{
			o.draw(this);
		}
	}
	
	public double getHeight()
	{
		return height;
	}
	
	public double getWidth()
	{
		return width;
	}
}
