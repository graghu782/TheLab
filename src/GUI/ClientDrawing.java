package GUI;

import java.util.ArrayList;

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

    public ClientDrawing()
    {
	super();
    }

    public ClientDrawing(String IP, String playerName)
    {
	super();
	
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
	catch(NullPointerException e)
	{
	    c.dispose();
	}
    }

    // The statements in draw() are executed 60 times a second until the
    // program is stopped. Each statement is executed in
    // sequence and after the last line is read, the first
    // line is executed again.s

    public void draw()
    {
	pushMatrix();
	
	super.draw();
	sendPlayerInfo();

	if (c.available() > 0)
	{
	    input = c.readString();

	    data = input.split(":");

	    try
	    {
		receivedPlayer = new Player(Double.parseDouble(data[0]), Double.parseDouble(data[1]), data[3], false);
		receivedPlayer.setHealth((int) Double.parseDouble(data[2]));
		receivedPlayer.setDirection(Double.parseDouble(data[4]));
		
		if(data.length > 6)
		{
		    for(int i = 6; i < data.length - 2; i++)
		    {
			
		    }
        	}
	    }
	    catch (Exception e)
	    {
		if (receivedPlayer != null)
		    receivedPlayer.draw(this);
	    }
	}
	
	if(c != null)
	    if (receivedPlayer != null)
		receivedPlayer.draw(this);
	
	popMatrix();
    }

    public void sendPlayerInfo()
    {
	c.write(player.getX() + ":" + player.getY() + ":" + player.getHealth() + ":" + player.getName() + ":" + player.getDirection() + ":");
	
	for(Bullet b : player.getBullets())
	{
	    if(c != null)
		c.write(b.getX() + ":" + b.getY() + ":" + b.getDirection() + ":");
	}
    }
}
