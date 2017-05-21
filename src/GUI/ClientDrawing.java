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
	    for(int i = 0;i < players.length && i*5 < data.length; i++) {
		if(!data[i*5+2].equals(player.getName())) {
		    try
		    {
			if(players[i] != null) {
			    players[i].update(Double.parseDouble(data[i*5]), Double.parseDouble(data[i*5+1]));
			    players[i].changeName(data[i*5+2]);
			}
			else {
			    players[i] = new Player(Double.parseDouble(data[i*5]), Double.parseDouble(data[i*5+1]), data[i*5+2], false);   
			}
			players[i].setHealth((int)Double.parseDouble(data[i*5+3]));
			players[i].setDirection(Double.parseDouble(data[i*5+4])); 
		    }
		    catch (Exception e)
		    {
		    }
		    finally {
			if(players[i] != null) {
			    players[i].draw(this);	
			}
		    }
		}
	    }

	}

	popMatrix();
    }

    public void sendPlayerInfo()
    {	
	c.write(player.getX() + ":" + player.getY() + ":" + player.getName() + ":" + player.getHealth() + ":" + player.getDirection() + ":");

	//	for(Bullet b : player.getBullets())
	//	{
	//	    if(c != null)
	//		c.write(b.getX() + ":" + b.getY() + ":" + b.getDirection() + ":");
	//	}
    }
}
