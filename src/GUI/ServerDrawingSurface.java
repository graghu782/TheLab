package GUI;

import java.util.ArrayList;

import Gameplay.Border;
import Gameplay.Bullet;
import Gameplay.Player;
import processing.core.PApplet;
import processing.core.PImage;
import processing.net.*;

public class ServerDrawingSurface extends PApplet
{
    private Player player;
    private Border border;
    private boolean[] keysPressed;
    private PImage img;

    public static final int DRAWING_WIDTH = 800;
    public static final int DRAWING_HEIGHT = 600;
    public static final int MAP_WIDTH = 2400;
    public static final int MAP_HEIGHT = 1800;
    private double x, y;

    private ArrayList<Player> players;

    private int count;

    private String IP;
    private String playerName;

    private Server s;
    private Client c;

    private String input;
    private String data[];

    public ServerDrawingSurface()
    {
	keysPressed = new boolean[4];
	players = new ArrayList<Player>();
	runSketch();
    }

    public ServerDrawingSurface(String IP, String playerName)
    {
	this.IP = IP;
	this.playerName = playerName;

	keysPressed = new boolean[4];
	players = new ArrayList<Player>();
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
	player = new Player(0, 0, playerName, true);
	border = new Border(0, 0, 10, 10);

	s = new Server(this, 4444);
    }

    public void runMe()
    {
	super.setSize(800, 600);
	super.sketchPath();
	super.initSurface();
	super.surface.startThread();
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
	image(img, (float) (-x), (float) (-y), (float) (MAP_WIDTH + DRAWING_WIDTH),
		(float) (MAP_HEIGHT + DRAWING_HEIGHT));
	border = new Border(DRAWING_WIDTH / 2 - x - 5 - player.getWidth() / 2,
		DRAWING_HEIGHT / 2 - y - 5 - player.getLength() / 2, MAP_WIDTH + 10, MAP_HEIGHT + 10);
	border.draw(this);
	fill(255);

	pushMatrix();

	float ratioX = (float) width / DRAWING_WIDTH;
	float ratioY = (float) height / DRAWING_HEIGHT;

	scale(ratioX, ratioY);
	checkBullets();

	player.draw(this);

	sendPlayerInfo();

	c = s.available();
	if (c != null)
	{
	    input = c.readString();
//	    if(input.indexOf("\n") >= 0);
//	    	input = input.substring(0, input.indexOf("\n"));

	    data = input.split(":");

	    if(data.length > 4 && data[0].equals("playerInfo")) {
		Player receivedPlayer = new Player(Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[4], false);
		receivedPlayer.setHealth((int)Double.parseDouble(data[3]));
		receivedPlayer.setDirection(Double.parseDouble(data[5]));
		if(data.length > 6) {
		    if(data[6].equals("bulletInfo")) {
			for(int x = 6; x < data.length-5; x++) {
			    receivedPlayer.getBullets().add(new Bullet(Double.parseDouble(data[x]), Double.parseDouble(data[x+1]), Double.parseDouble(data[x+2]), Double.parseDouble(data[x+3]), Double.parseDouble(data[x+4]), receivedPlayer));
			}
		    }
		}
		receivedPlayer.draw(this);
	    }

	}

	checkKeys();
	popMatrix();
    }

    public void sendPlayerInfo()
    {
	s.write("playerInfo:");
	s.write(player.getX() + ":" + player.getY() + ":" + player.getHealth() + ":" + player.getName() + ":"
		+ player.getDirection() + ":");
	for (Bullet b : player.getBullets())
	{
	    s.write("bulletInfo");
	    s.write(b.getXCoord() + ":" + b.getYCoord() + ":" + b.getDirection() + ":" + b.getX() + ":" + b.getY());
	}
    }

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
	    if (player.getY() + player.getLength() < MAP_HEIGHT - 1)
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

    public void checkBullets()
    {
	for (int i = 0; i < player.getBullets().size(); i++)
	{
	    ArrayList<Bullet> bas = player.getBullets();
	    for (Player p : players)
	    {
		if (p.isHit(bas.get(i)))
		{
		    bas.remove(i);
		    p.decHealth(10);
		}
	    }
	}

    }
}
