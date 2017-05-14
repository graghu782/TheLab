package GUI;
import Gameplay.Border;
import Gameplay.Bullet;
import Gameplay.Player;
import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet 
{
	private Player player;
	private Border border;
	private boolean[] keysPressed;
	private PImage img;
	
	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;
	public static final int MAP_WIDTH = 2400;
	public static final int MAP_HEIGHT = 1800;
	private double x,y;

	public DrawingSurface () 
	{
		keysPressed = new boolean[4];
		runSketch();
	}
	
	public void settings() 
	{
		size(800, 600);
	}
	
	public void setup() 
	{
		background(255);
		img = loadImage("background.bmp");
		player = new Player(0, 0, "noob");
		border = new Border(0,0,10,10);
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
		image(img, (float)(- x), (float)(-y), (float)(MAP_WIDTH+DRAWING_WIDTH), (float)(MAP_HEIGHT+DRAWING_HEIGHT));
		
		border = new Border(DRAWING_WIDTH/2 -x - 5 - player.getWidth()/2, DRAWING_HEIGHT/2 - y - 5-player.getLength()/2, MAP_WIDTH+10, MAP_HEIGHT+10);
		border.draw(this);
		fill(255);
				
		pushMatrix();
		
		float ratioX = (float)width/DRAWING_WIDTH;
		float ratioY = (float)height/DRAWING_HEIGHT;
		
		scale(ratioX, ratioY);
		
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
	
		if(key == 'w' || key == 'W') 
		{
			keysPressed[0] = true;
		}
		if(key == 'a' || key == 'A') 
		{
			keysPressed[1] = true;
		}
		if(key == 's' || key == 'S')
		{ 
			keysPressed[2] = true;
		}
		if(key == 'd' || key == 'D') 
		{
			keysPressed[3] = true;
		}
	}
	
	public void keyReleased()
	{
		if(key == 'w' || key == 'W') 
		{
			keysPressed[0] = false;
		}
		if(key == 'a' || key == 'A') 
		{
			keysPressed[1] = false;
		}
		if(key == 's' || key == 'S')
		{ 
			keysPressed[2] = false;
		}
		if(key == 'd' || key == 'D') 
		{
			keysPressed[3] = false;
		}
	}
	public void mouseClicked(){
		player.makeBullet(DRAWING_WIDTH, DRAWING_HEIGHT);
	}
}

