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

    public ClientDrawing()
    {
	super();
    }

    public ClientDrawing(String IP, String playerName)
    {
	super();
	timeRemaining = 100;	

	bulletList = new ArrayList<Bullet>();
	this.IP = IP;
	this.playerName = playerName;
    }

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

    public void draw()
    {
	pushMatrix();

	super.draw();
	
	sendPlayerInfo();

	if (c.available() > 0)
	{
	    input = c.readString();

	    try
	    {
		// Process Player info
		playerData = input.substring(0, input.indexOf("#"));
		data = playerData.split(":");

		int i = 0;
		while (i * 6 + 5 < data.length)
		{
		    if(!data[i*6+3].equals(player.getName())){
			if(receivedPlayer != null) {
			    receivedPlayer.update(Double.parseDouble(data[i * 6 + 1]), Double.parseDouble(data[i * 6 + 2]));
			}
			else {
			    receivedPlayer = new Player(Double.parseDouble(data[i * 6 + 1]), Double.parseDouble(data[i * 6 + 2]), data[i*6+3], false);			    
			}
			receivedPlayer.setHealth((int) Double.parseDouble(data[i * 6 + 4]));
			receivedPlayer.setDirection(Double.parseDouble(data[i * 6 + 5]));
		    }
		    i++;
		}

		// Process Timer info
		input = input.substring(input.indexOf("#") + 1);
		timerData = input.substring(input.indexOf("timerinfo"), input.indexOf("#"));
		data = timerData.split(":");

		if (data[0].equals("timerinfo"))
		{
		    timeRemaining = (int) Double.parseDouble(data[1]);
		}

		// Process Bullet info
		input = input.substring(input.indexOf("#") + 1);
		if (input.indexOf("bulletinfo") >= 0 && input.indexOf("#") >= 0)
		{
		    bulletData = input.substring(input.indexOf("bulletinfo"), input.indexOf("#"));
		    data = bulletData.split(":");
		    bulletList.clear();
		    int j = 0;
		    while (j * 5 + 4 < data.length)
		    {
			Player temp = player;
			if(player.getName().equals(data[j*5+4]))
			{
			    temp = player;
			}
			else {
			    temp = receivedPlayer;
			}

			Bullet b = new Bullet(Double.parseDouble(data[j * 5 + 1]), Double.parseDouble(data[j * 5 + 2]), Double.parseDouble(data[j * 5 + 3]), temp);
			bulletList.add(b);
			j++;
		    }
		}

	    }
	    catch (Exception e)
	    {

	    }
	}

	text("Time remaining: " + timeRemaining, 675, 550);
	if (timeRemaining < 1)
	{
	    textSize(72);
	    text("GAME OVER", 200, 100);
	    textSize(11);
	    noLoop();
	}

	if(receivedPlayer != null) {
	    receivedPlayer.draw(this);
	}

	for (Bullet b : bulletList)
	{
	    if(!b.getPlayer().getName().equals(player.getName()))
		b.draw(this);
	}

	if (player.getBullets().size() != 0)
	    sendBulletInfo();

	popMatrix();
    }

    public void sendPlayerInfo()
    {
	c.write("playerinfo" + ":" + player.getX() + ":" + player.getY() + ":" + player.getName() + ":" + player.getHealth() + ":" + player.getDirection() + ":");
	c.write("#");
    }

    public void sendBulletInfo()
    {
	for (Bullet b : player.getBullets())
	{
	    c.write("bulletinfo" + ":" + b.getXCoord() + ":" + b.getYCoord() + ":" + b.getDirection() + ":");
	}

	c.write("#");
    }
}
