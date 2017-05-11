package GUI;
import Gameplay.Player;
import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet 
{
	private Player player;
	private boolean[] keysPressed;
	private PImage img;
	
	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;
	public static final int MAP_WIDTH = 2400;
	public static final int MAP_HEIGHT = 1800;
	public double x,y;
	

	public DrawingSurface () 
	{
		keysPressed = new boolean[4];
		runSketch();
	}
	
	public void setup() 
	{
		background(255);
		img = loadImage("background.jpg");
		player = new Player(300, 100, "noob");
	}

	// The statements in draw() are executed 60 times a second until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.s


	public void draw() 
	{	
		x = player.getX();
		y = player.getY();
		
		background(255);
		image(img, (float)0, (float)0, (float)width, (float)height);
		fill(255);
				
		pushMatrix();
		
		
		float ratioX = (float)width/DRAWING_WIDTH;
		float ratioY = (float)height/DRAWING_HEIGHT;
		
		scale(ratioX, ratioY);
		

		for (int i = 0; i < 400; i += 10)
		{
			this.rect((float)(MAP_WIDTH - x + i), (float)(MAP_HEIGHT - y + i), (float)20, (float)20);
		}
		
		player.draw(this);
		
		if(keysPressed[0])
		{
			if (player.getY() > 1)
			{
				player.move(0, -10);
			}
		}
		if(keysPressed[1])
		{
			if (player.getX() > 1)
				player.move(-10, 0);
		}
		if(keysPressed[2]) 
		{
			if (player.getY() + player.getLength() < MAP_HEIGHT - 1)
				player.move(0, 10);
		}
		if(keysPressed[3])
		{
			if (player.getX() + player.getWidth() < MAP_WIDTH - 1)
				player.move(10, 0);
		}
		
		
		popMatrix();
	}
	
	public void keyPressed() 
	{
	
		if(key == 'w') 
		{
			keysPressed[0] = true;
		}
		if(key == 'a') 
		{
			keysPressed[1] = true;
		}
		if(key == 's')
		{ 
			keysPressed[2] = true;
		}
		if(key == 'd') 
		{
			keysPressed[3] = true;
		}
	}
	
	public void keyReleased()
	{
		if(key == 'w') 
		{
			keysPressed[0] = false;
		}
		if(key == 'a') 
		{
			keysPressed[1] = false;
		}
		if(key == 's')
		{ 
			keysPressed[2] = false;
		}
		if(key == 'd') 
		{
			keysPressed[3] = false;
		}
	}
}

