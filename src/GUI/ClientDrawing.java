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
    
    private String playerData;
    private String timerData;
    private String bulletData;

    private ArrayList<Player> playerList;
    private ArrayList<Bullet> bulletList;
    private int timeRemaining;
    
    public ClientDrawing()
    {
	super();
    }

    public ClientDrawing(String IP, String playerName)
    {
	super();
	playerList = new ArrayList<Player>();
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
	
	if(player.getBullets().size() != 0)
	    sendBulletInfo();

	if (c.available() > 0)
	{
	    input = c.readString();
	    
	    playerData = input.substring(0, input.indexOf("#"));
	    data = playerData.split(":");
	    playerList.clear();
	    
	    int i = 0;
	    while(i*6+5 < data.length) 
	    {
		if(!data[i*6+3].equals(player.getName())) 
		{
		    Player temp = new Player(Double.parseDouble(data[i*6+1]), Double.parseDouble(data[i*6+2]), data[i*6+3], false);
		    temp.setHealth((int)Double.parseDouble(data[i*6+4]));
		    temp.setDirection(Double.parseDouble(data[i*6+5])); 
		    playerList.add(temp);
		}
		    
		  i++;
	    }
	    
	    input = input.substring(input.indexOf("#") + 1);
	    timerData = input.substring(input.indexOf("timerinfo"), input.indexOf("#"));
	    data = timerData.split(":");
	    
	    if(data[0].equals("timerinfo"))
	    {
		timeRemaining = (int)Double.parseDouble(data[1]);
	    }
	    
	    input = input.substring(input.indexOf("#") + 1);
	    if(input.indexOf("bulletinfo") >= 0)
	    {
		bulletData = input.substring(input.indexOf("bulletinfo"), input.indexOf("#"));
		data = bulletData.split(":");
		bulletList.clear();
	    
		i = 0;
		while(i*4+3 < data.length)
		{
		    Bullet b = new Bullet(Double.parseDouble(data[i*4+1]), Double.parseDouble(data[i*4+2]), Double.parseDouble(data[i*4+3]), player);
		    bulletList.add(b);
		
		    i++;
		}
	    }
	}

	for(int i = 0; i < playerList.size(); i++) 
	{
	    playerList.get(i).draw(this);
	}
	for(Bullet b : bulletList)
	{
	    b.draw(this);
	}
	text("Time remaining: " + timeRemaining, 675, 550);

	popMatrix();
    }

    public void sendPlayerInfo()
    {	
	c.write("playerinfo" + ":" + player.getX() + ":" + player.getY() + ":" + player.getName() + ":" + player.getHealth() + ":" + player.getDirection() + ":");
	c.write("#");
    }
    
    public void sendBulletInfo()
    {
	for(Bullet b : player.getBullets())
	{
	    c.write("bulletinfo" + ":" + b.getX() + ":" + b.getY() + ":" + b.getDirection() + ":");
	}
	
	c.write("#");
    }
}
