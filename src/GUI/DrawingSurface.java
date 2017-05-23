package GUI;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Gameplay.Border;
import Gameplay.Bullet;
import Map.Obstacle;
import Gameplay.Player;
import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet
{
    protected Player player;
    protected Player receivedPlayer;
    protected Border border;
    protected boolean[] keysPressed;
    protected PImage img;

    public static final int DRAWING_WIDTH = 800;
    public static final int DRAWING_HEIGHT = 600;
    public static final int MAP_WIDTH = 1200;
    public static final int MAP_HEIGHT = 900;
    protected int count;
    protected double x, y;

    protected String IP;
    protected String playerName;
    
    protected Obstacle[] obstacles;
    
    private Timer animationTimer;
    private int interval;

    /**
     * Creates a new DrawingSurface upon which the players, the bullets, and the game is drawn on.
     */
    public DrawingSurface()
    {
	animationTimer = new Timer();
	interval = 1;
	
	keysPressed = new boolean[4];
	runSketch();
    }

    /**
     * Sets the window size
     */
    public void settings()
    {
	size(800, 600);
    }

    /**
     * Sets the timer up, adds the background.
     */
    public void setup()
    {

	 background(255);
	 img = loadImage("background.png");
	 player = new Player(0, 0, playerName, true);
	border = new Border(0, 0, 10, 10);

	frameRate(180);
	
	obstacles = new Obstacle[1];
	obstacles[0] = new Obstacle(400, 0, 420, 800);
	
	animationTimer.scheduleAtFixedRate(new TimerTask()
	{
		public void run()
		{
		    if(interval == 2)
			interval = 3;
		    else
			interval = 2;
		}
	}, 500, 500);
	
    }
/**
 * runs the class
 */
    public void runMe()
    {
	super.setSize(800, 600);
	super.sketchPath();
	super.initSurface();
	super.surface.startThread();
    }
    
    /**
     * draws everything onto the server computer
     */
    public void draw()
    {
	x = player.getX();
	y = player.getY();

	background(255);
	image(img, (float) (-x), (float) (-y), (float) (MAP_WIDTH + DRAWING_WIDTH), (float) (MAP_HEIGHT + DRAWING_HEIGHT));
	border = new Border(DRAWING_WIDTH / 2 - x - 5 - player.getWidth() / 2, DRAWING_HEIGHT / 2 - y - 5 - player.getHeight() / 2, MAP_WIDTH + 10, MAP_HEIGHT + 10);
	border.draw(this);
	
	float ratioX = (float) width / DRAWING_WIDTH;
	float ratioY = (float) height / DRAWING_HEIGHT;
	
	if(isMoving())
	{
	    player.changeAnimation(interval);
	}
	else
	{
	    player.changeAnimation(1);
	}

	scale(ratioX, ratioY);

	player.draw(this);

	checkKeys();
    }
    
    /**
     * Checks for keys pressed, sets the corresponding value in the boolean array to true.
     */
    public void keyPressed()
    {

	if (key == 'w' || key == 'W')
	{
	    keysPressed[0] = true;
	}
	if (key == 'a' || key == 'A')
	{
	    keysPressed[1] = true;
	}
	if (key == 's' || key == 'S')
	{
	    keysPressed[2] = true;
	}
	if (key == 'd' || key == 'D')
	{
	    keysPressed[3] = true;
	}
    }

    /**
     * Checks for keys released, sets the corresponding value in the boolean array to false.
     */
    public void keyReleased()
    {
	if (key == 'w' || key == 'W')
	{
	    keysPressed[0] = false;
	}
	if (key == 'a' || key == 'A')
	{
	    keysPressed[1] = false;
	}
	if (key == 's' || key == 'S')
	{
	    keysPressed[2] = false;
	}
	if (key == 'd' || key == 'D')
	{
	    keysPressed[3] = false;
	}
    }
    
    /**
     * Checks to see if the player is moving or not.
     * @return true if player is moving, false if not.
     */
    public boolean isMoving()
    {
	if(keysPressed[0] || keysPressed[1] || keysPressed[2] || keysPressed[3])
	{
	    return true;
	}
	else return false;
    }

    /**
     * Checks and sees which keys are pressed, moves according to the keys pressed.
     */
    public void checkKeys()
    {
	if (keysPressed[0])
	{
	    if (player.getY() > 1)
	    {
		player.move(0, -10);
	    }
	}
	if (keysPressed[1])
	{
	    if (player.getX() > 1)
		player.move(-10, 0);
	}
	if (keysPressed[2])
	{
	    if (player.getY() + player.getHeight() < MAP_HEIGHT - 1)
		player.move(0, 10);
	}
	if (keysPressed[3])
	{
	    if (player.getX() + player.getWidth() < MAP_WIDTH - 1)
		player.move(10, 0);
	}

	if (mousePressed)
	{
	    count++;
	    if (count % 3 == 0)
	    {
		if (player.getAmmo() > 0)
		{
		    player.fire();
		    count = 0;
		    player.changeAmmo(-1);
		}
	    }
	}
	else
	{
	    count++;
	    if (count % 3 == 0)
	    {
		if (player.getAmmo() < 16)
		{
		    player.changeAmmo(1);
		}
	    }
	}
    }
}
