package GUI;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Gameplay.Bullet;
import Gameplay.Player;
import processing.net.Client;
import processing.net.Server;

public class ServerDrawing extends DrawingSurface
{
    private Server s;
    private Client c;

    private String input;
    private String data[];
    private String bulletData;
    private String playerData;
    
    private Timer timeRemaining;
    private int interval;
    private int eliminations;

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
	
	c = s.available();

	for(int i = 0; i < s.clientCount; i++) 
	{
	    if(s.clients[i] != null)
	    {
		input = s.clients[i].readString();
		
		try 
		{
		    if(input != null)
		    {
			playerData = input.substring(0, input.indexOf("#"));
			data = playerData.split(":");
			
			players[i] = new Player(Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[3], false);   
			players[i].setHealth((int)Double.parseDouble(data[4]));
			players[i].setDirection(Double.parseDouble(data[5])); 
			
			input = input.substring(input.indexOf("#") + 1);
			bulletData = input.substring(input.indexOf("bulletinfo"), input.indexOf("#"));
			data = bulletData.split(":");
			
			int j = 0;
			while(j*4+3 < data.length)
			{
			    Bullet b = new Bullet(Double.parseDouble(data[j*4+1]), Double.parseDouble(data[j*4+2]), Double.parseDouble(data[j*4+3]), players[i]);
			    players[i].addBullet(b);
			    
			    j++;
			}
		    }
		}
		catch(Exception e)
		{
		}
	    }
	    
	    checkBullets();
	    
	    sendPlayerInfo();
	    sendTimerInfo();
	    sendBulletInfo();
	}

	for(int i = 0; i < players.length; i++) 
	{
	    if(players[i] != null) 
	    {
		players[i].draw(this);
		for(Bullet b : players[i].getBullets())
		{
		    b.draw(this);
		}
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
	
	s.write("#");
    }
    
    public void sendTimerInfo()
    {
	s.write("timerinfo" + ":" + interval + ":");
	s.write("#");
    }
    
    public void sendBulletInfo()
    {
	for(Bullet b : player.getBullets())
	{
	    s.write("bulletinfo" + ":" + b.getX() + ":" + b.getY() + ":" + b.getDirection() + ":");
	}
	
	for(int i = 0; i < s.clientCount; i++)
	{
	    if(players[i] != null)
	    {
		for(Bullet b : players[i].getBullets())
		{
		    s.write("bulletinfo" + ":" + b.getX() + ":" + b.getY() + ":" + b.getDirection() + ":");
		}
	    }
	}
	
	s.write("#");
    }
    
    public void checkBullets()
    {
	for(Player p : players)
	{
	    if(p != null)
	    {
		ArrayList<Bullet> bullets = p.getBullets();
		for(Bullet b : bullets)
		{
		    for(Player t : players)
		    {
			if(t != null && !t.equals(p) && t.isHit(b))
			{
			    t.decHealth(10);
			    bullets.remove(b);
			}
		    }
		}
	    }
	}
    }
}
