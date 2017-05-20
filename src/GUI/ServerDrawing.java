package GUI;

import java.util.ArrayList;

import Gameplay.Border;
import Gameplay.Bullet;
import Gameplay.Player;
import processing.core.PApplet;
import processing.core.PImage;
import processing.net.*;

public class ServerDrawing extends DrawingSurface
{
    private Server s;
    private Client c;

    private String input;
    private String data[];

    public ServerDrawing()
    {
	super();
    }

    public ServerDrawing(String IP, String playerName)
    {
	super();
	
	this.IP = IP;
	this.playerName = playerName;
    }

    public void setup()
    {
	super.setup();
	
	s = new Server(this, 4444);
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

	c = s.available();
	if (c != null)
	{
	    input = c.readString();

	    data = input.split(":");

	    if (data.length > 5)
	    {
		try
		{
		    receivedPlayer = new Player(Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[4], false);
		    receivedPlayer.setHealth((int) Double.parseDouble(data[3]));
		    receivedPlayer.setDirection(Double.parseDouble(data[5]));
		    
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
	}

	if (receivedPlayer != null)
	    receivedPlayer.draw(this);
	
	popMatrix();
    }

    public void sendPlayerInfo()
    {
	s.write("playerInfo:");
	s.write(player.getX() + ":" + player.getY() + ":" + player.getHealth() + ":" + player.getName() + ":" + player.getDirection() + ":");
	
	for(Bullet b : player.getBullets())
	{
	    c.write(b.getX() + ":" + b.getY() + ":" + b.getDirection() + ":");
	}
    }
}
