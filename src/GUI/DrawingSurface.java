package GUI;
import Gameplay.Player;
import processing.core.PApplet;

public class DrawingSurface extends PApplet 
{
	private Player player;
	private boolean[] keysPressed;

	public DrawingSurface () 
	{
		keysPressed = new boolean[4];
		runSketch();
	}

	public void setup() 
	{
		background(255);
		player = new Player(100, 100, "noob");
	}

	// The statements in draw() are executed 60 times a second until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.


	public void draw() 
	{		
		background(255);
		fill(255);
		
		if(keysPressed[0])
		{
			player.move(0, -10);
		}
		if(keysPressed[1])
		{
			player.move(-10, 0);
		}
		if(keysPressed[2])
		{
			player.move(0, 10);
		}
		if(keysPressed[3])
		{
			player.move(10, 0);
		}
		
		player.draw(this);
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

