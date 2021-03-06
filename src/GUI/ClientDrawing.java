package GUI;

import java.util.ArrayList;

import javax.xml.transform.ErrorListener;

import Gameplay.Border;
import Gameplay.Bullet;
import Gameplay.Player;
import processing.core.PApplet;
import processing.core.PImage;
import processing.net.*;

public class ClientDrawing extends DrawingSurface
{
    private Client c;

    private String input;
    private String data[];

    private String playerData;
    private String timerData;
    private String bulletData;

    private ArrayList<Bullet> bulletList;
    private int timeRemaining;
    private int eliminations;
    private int seliminations;
    
    private int prevHealth;

    private boolean sendEliminated;

    /**
     * Creates a new DrawingSurface for a client, which draws depending on the
     * client's point of view.
     */
    public ClientDrawing()
    {
	super();
    }

    /**
     * Creates a new DrawingSurface for a client, which draws depending on the
     * client's point of view. Connects to the server with the IP address of IP
     * 
     * @param IP name of IP
     * @param playerName name of player
     */
    public ClientDrawing(String IP, String playerName)
    {
	super();
	timeRemaining = 100;

	bulletList = new ArrayList<Bullet>();
	this.IP = IP;
	this.playerName = playerName;
    }

    /**
     * sets up the drawingSurface
     */
    public void setup()
    {
	super.setup();

	try
	{
	    c = new Client(this, IP, 4444);
	}
	catch (NullPointerException e)
	{
	    c.dispose();
	}
    }

    /**
     * draws the client onto the server
     */
    public void draw()
    {
	pushMatrix();

	player.getBullets().clear();
	
	super.draw();

	sendPlayerInfo();

	if (c.available() > 0)
	{
	    input = c.readString();

	    // Process Player info
	    if (input.indexOf("#") >= 0)
	    {
		
		playerData = input.substring(0, input.indexOf("#"));
		data = playerData.split(":");

		int i = 0;
		while (i * 8 + 7 < data.length)
		{
		    try
		    {
			if (!data[i * 8 + 3].equals(player.getName()))
			{
			    receivedPlayer = new Player(Double.parseDouble(data[i * 8 + 1]), Double.parseDouble(data[i * 8 + 2]), data[i * 8 + 3], false);
			    receivedPlayer.setHealth((int) Double.parseDouble(data[i * 8 + 4]));
			    receivedPlayer.setDirection(Double.parseDouble(data[i * 8 + 5]));
			    seliminations = (int) Double.parseDouble(data[i * 8 + 7]);

			    if (data[i * 8 + 6].equals("true"))
			    {
				eliminations++;
			    }
			}
			else
			{
			    if(player.getHealth() == prevHealth)
				player.setHealth((int) Double.parseDouble(data[i * 8 + 4]));
			    else
			    {
				prevHealth = player.getHealth();
			    }
			}
			i++;
		    }
		    catch (NumberFormatException e)
		    {
			for (int k = 0; k < data.length; k++)
			{
			    System.out.print(data[k] + " ");
			}
			break;
		    }
		}
		if (player.getHealth() < 1)
		{
		    player = new Player(1200 - player.getWidth(), 900 - player.getHeight(), playerName, true);
		    sendEliminated = true;
		}

		// Process Timer info
		input = input.substring(input.indexOf("#") + 1);

		if (input.indexOf("timerinfo") >= 0 && input.indexOf("#") >= 0)
		{
		    // timerData = input.substring(input.indexOf("timerinfo"),
		    // input.indexOf("#"));
		    timerData = input.substring(input.indexOf("timerinfo"));
		    data = timerData.split(":");

		    if (data[0].equals("timerinfo"))
		    {
			timeRemaining = (int) Double.parseDouble(data[1]);
		    }
		}

		// Process Bullet info
		input = input.substring(input.indexOf("#") + 1);
		if (input.indexOf("bulletinfo") >= 0 && input.indexOf("#") >= 0 && input.indexOf("#") > input.indexOf("bulletinfo"))
		{
		    bulletData = input.substring(input.indexOf("bulletinfo"), input.indexOf("#"));
		    data = bulletData.split(":");
		    bulletList.clear();
		    int j = 0;
		    while (j * 5 + 4 < data.length)
		    {
			Player temp = player;
			if (player.getName().equals(data[j * 5 + 4]))
			{
			    temp = player;
			}
			else
			{
			    temp = receivedPlayer;
			}

			Bullet b = new Bullet(Double.parseDouble(data[j * 5 + 1]), Double.parseDouble(data[j * 5 + 2]), Double.parseDouble(data[j * 5 + 3]), temp);
			bulletList.add(b);
			
			if(b.getPlayer().getName().equals(playerName))
			{
				player.addBullet(b);
			}

			j++;
		    }
		}
	    }
	}
	
	checkBullets();

	text("My Eliminations: " + eliminations, 675, 500);
	text("Enemy Eliminations: " + seliminations, 670, 525);
	text("Time remaining: " + timeRemaining, 675, 550);
	if (timeRemaining < 1)
	{
	    textSize(72);
	    text("GAME OVER", 200, 100);

	    if (seliminations < eliminations)
	    {
		text("YOU WON", 200, 300);
	    }
	    else if (seliminations > eliminations)
	    {
		text("YOU LOST", 200, 300);
	    }
	    else
	    {
		text("DRAW", 200, 300);
	    }

	    textSize(11);
	    noLoop();
	}

	if (receivedPlayer != null)
	{
	    receivedPlayer.draw(this);
	}

	for (Bullet b : bulletList)
	{
	    b.draw(this);
	}

	if (player.getBullets().size() != 0)
	    sendBulletInfo();

	popMatrix();
    }

    /**
     * sends the player info to the server
     */
    public void sendPlayerInfo()
    {
	c.write("playerinfo" + ":" + player.getX() + ":" + player.getY() + ":" + player.getName() + ":" + player.getHealth() + ":" + player.getDirection() + ":" + sendEliminated + ":" + eliminations + ":");

	if (sendEliminated)
	{
	    sendEliminated = false;
	}

	c.write("#");
    }

    /**
     * sends the bullet info to the server
     */
    public void sendBulletInfo()
    {
	for (Bullet b : player.getBullets())
	{
	    c.write("bulletinfo" + ":" + b.getXCoord() + ":" + b.getYCoord() + ":" + b.getDirection() + ":");
	}

	c.write("#");
    }
    
    public void checkBullets()
    {
	for(int i = 0; i < player.getBullets().size(); i++)
	{
	    if(receivedPlayer != null && receivedPlayer.isHit(player.getBullets().get(i)))
	    {
		player.getBullets().remove(player.getBullets().get(i));
	    }
	}
    }
}
