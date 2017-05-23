package Map;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Map extends PApplet
{
	private ArrayList<Obstacle> obstacles;
	
	/**
	 * creates a map of obstacles obstacles
	 * 
	 */
	public Map()
	{
		obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Obstacle(400, 0, 420, 800));
	}
	/**
	 * draws the obstacles in the map
	 * @param drawer PApplet that draws the map
	 */
	public void draw(PApplet drawer)
	{		
		for(Obstacle o : obstacles)
		{
			o.draw(this);
		}
	}
	/**
	 * returns height of map
	 * @return height
	 */
	public double getHeight()
	{
		return height;
	}
	/**
	 * returns width of map
	 * @return width
	 */
	public double getWidth()
	{
		return width;
	}
}
