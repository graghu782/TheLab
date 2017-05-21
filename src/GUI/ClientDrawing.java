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

    private ArrayList<Player> playerList;
    
    public ClientDrawing()
    {
	super();
    }

    public ClientDrawing(String IP, String playerName)
    {
	super();
	playerList = new ArrayList<Player>();
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

    public void draw()
    {
	pushMatrix();

	super.draw();
	sendPlayerInfo();

	if (c.available() > 0)
	{
	    input = c.readString();

	    data = input.split(":");
	    
	    playerList.clear();
	    
	    int i = 0;
	    
	    while(i*5+4 < data.length) {
		if(!data[i*5+2].equals(player.getName())) {
		    Player temp = new Player(Double.parseDouble(data[i*5]), Double.parseDouble(data[i*5+1]), data[i*5+2], false);
		    temp.setHealth((int)Double.parseDouble(data[i*5+3]));
		    temp.setDirection(Double.parseDouble(data[i*5+4])); 
		    playerList.add(temp);
		}
		i++;
	    }
	    
	}

	for(int i = 0; i < playerList.size(); i++) {
	    playerList.get(i).draw(this);
	}

	popMatrix();
    }

    public void sendPlayerInfo()
    {	
	c.write(player.getX() + ":" + player.getY() + ":" + player.getName() + ":" + player.getHealth() + ":" + player.getDirection() + ":");
    }
}
