package GUI;

import java.util.Timer;
import java.util.TimerTask;

import Gameplay.Player;
import processing.net.Client;
import processing.net.Server;

public class ServerDrawing extends DrawingSurface
{
    private Server s;
    private Client c;

    private String input;
    private String data[];
    
    private Timer timeRemaining;
    private int interval;

    public ServerDrawing()
    {
	super();
    }

    public ServerDrawing(String IP, String playerName)
    {
	super();

	timeRemaining = new Timer();
	this.IP = IP;
	this.playerName = playerName;
    }

    public void setup()
    {
	super.setup();
	
	s = new Server(this, 4444);
	
	interval = 180;
	
	timeRemaining.scheduleAtFixedRate(new TimerTask()
	{
    		public void run()
    		{
    		    if(interval == 1)
    			timeRemaining.cancel();
    		    interval--;
    		}
	}, 1000, 1000);
    }

    public void draw()
    {
	
	pushMatrix();

	super.draw();
	
	fill(0);
	text("Time remaining: " + interval, 675, 550);
	if(interval < 1)
	{
	    textSize(72);
	    text("GAME OVER", 200, 100);
	    textSize(11);
	    noLoop();
	}

	fill(255);
	
	sendPlayerInfo();
	c = s.available();

	for(int i = 0; i < s.clientCount; i++) 
	{
	    if(s.clients[i] != null)
	    {
		input = s.clients[i].readString();
		if(input != null)
		{
		    data = input.split(":");
		    
		    if(data[0].equals("playerinfo"))
		    {
			try 
			{
			    players[i] = new Player(Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[3], false);   
			    players[i].setHealth((int)Double.parseDouble(data[4]));
			    players[i].setDirection(Double.parseDouble(data[5])); 
			} 
			catch(Exception e)
			{
			}
		    }
		}

	    }
	}

	for(int i = 0; i < players.length; i++) 
	{
	    if(players[i] != null) 
	    {
		players[i].draw(this);
	    }
	}

	popMatrix();
    }

    public void sendPlayerInfo()
    {
	s.write("playerinfo" + ":" + player.getX() + ":" + player.getY() + ":" + player.getName() + ":" + player.getHealth() + ":" + player.getDirection() + ":");

	for(int i = 0; i < s.clientCount; i++) 
	{
	    if(players[i] != null) 
	    {
		s.write("playerinfo" + ":" + players[i].getX() + ":" + players[i].getY() + ":" + players[i].getName() + ":" + players[i].getHealth() + ":" + players[i].getDirection() + ":");
	    }
	}
    }
    
    public void sendTimerInfo()
    {
	
    }
}
