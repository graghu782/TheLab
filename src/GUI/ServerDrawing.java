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
    private Player receivedPlayer;

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
		if (interval == 1)
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
	if (interval < 1)
	{
	    textSize(72);
	    text("GAME OVER", 200, 100);
	    textSize(11);
	    noLoop();
	}

	fill(255);

	c = s.available();

	for (int i = 0; i < s.clientCount + 1; i++)
	{
	    if (s.clients[i] != null)
	    {
		input = s.clients[i].readString();

		try
		{
		    if (input != null)
		    {
			playerData = input.substring(0, input.indexOf("#"));
			data = playerData.split(":");

			receivedPlayer = new Player(Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[3],
				false);
			receivedPlayer.setHealth((int) Double.parseDouble(data[4]));
			receivedPlayer.setDirection(Double.parseDouble(data[5]));

			input = input.substring(input.indexOf("#") + 1);
			bulletData = input.substring(input.indexOf("bulletinfo"), input.indexOf("#"));
			data = bulletData.split(":");

			int j = 0;
			while (j * 4 + 3 < data.length)
			{
			    Bullet b = new Bullet(Double.parseDouble(data[j * 4 + 1]),
				    Double.parseDouble(data[j * 4 + 2]), Double.parseDouble(data[j * 4 + 3]),
				    receivedPlayer);
			    receivedPlayer.addBullet(b);

			    j++;
			}
		    }
		}
		catch (Exception e)
		{
		}
	    }
	}

	if(receivedPlayer != null)
	    receivedPlayer.draw(this);

	checkBullets();

	sendPlayerInfo();
	sendTimerInfo();
	sendBulletInfo();

	popMatrix();
    }

    public void sendPlayerInfo()
    {
	s.write("playerinfo" + ":" + player.getX() + ":" + player.getY() + ":" + player.getName() + ":"
		+ player.getHealth() + ":" + player.getDirection() + ":");

	if (receivedPlayer != null)
	    s.write("playerinfo" + ":" + receivedPlayer.getX() + ":" + receivedPlayer.getY() + ":" + receivedPlayer.getName()
	    + ":" + receivedPlayer.getHealth() + ":" + receivedPlayer.getDirection() + ":");

	s.write("#");
    }

    public void sendTimerInfo()
    {
	s.write("timerinfo" + ":" + interval + ":");
	s.write("#");
    }

    public void sendBulletInfo()
    {
	for (Bullet b : player.getBullets())
	{
	    s.write("bulletinfo" + ":" + b.getXCoord() + ":" + b.getYCoord() + ":" + b.getDirection() + ":"
		    + player.getName() + ":");
	}

	if(receivedPlayer != null) {
	    for (Bullet b : receivedPlayer.getBullets())
	    {
		s.write("bulletinfo" + ":" + b.getXCoord() + ":" + b.getYCoord() + ":" + b.getDirection() + ":"
			+ receivedPlayer.getName() + ":");
	    }
	}
	s.write("#");
    }

    public void checkBullets()
    {
	super.checkBullets(player);
	super.checkBullets(receivedPlayer);
    }
}
