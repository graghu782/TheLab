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
    private int celiminations;

    private boolean sendEliminated;

    /**
     * sets up a new DrawingSurface
     */
    public ServerDrawing()
    {
	super();
    }

    /**
     * sets up a new DrawingSurface
     * 
     * @param IP IP address of the server
     * @param playerName name of the player
     */
    public ServerDrawing(String IP, String playerName)
    {
	super();

	timeRemaining = new Timer();
	this.IP = IP;
	this.playerName = playerName;
    }

    /**
     * sets up the game drawingsurface
     */
    public void setup()
    {
	super.setup();
	s = new Server(this, 4444);

	interval = 120;

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

    /**
     * draws the main game
     */
    public void draw()
    {

	pushMatrix();

	super.draw();

	fill(0);
	text("My Eliminations: " + eliminations, 675, 500);
	text("Enemy Eliminations: " + celiminations, 670, 525);
	text("Time remaining: " + interval, 675, 550);
	if (interval < 1)
	{
	    textSize(72);
	    text("GAME OVER", 200, 100);

	    if (celiminations < eliminations)
	    {
		text("YOU WON", 200, 300);
	    }
	    else if (celiminations > eliminations)
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

	fill(255);

	c = s.available();

	if (c != null)
	{
	    input = c.readString();

	    if (input != null && input.indexOf("#") >= 0)
	    {
		playerData = input.substring(0, input.indexOf("#"));
		data = playerData.split(":");

		if (data.length > 7 && data[0].equals("playerinfo"))
		{
		    receivedPlayer = new Player(Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[3], false);
		    receivedPlayer.setHealth((int) Double.parseDouble(data[4]));
		    receivedPlayer.setDirection(Double.parseDouble(data[5]));

		    if (data[6].equals("true"))
		    {
			eliminations++;
		    }

		    celiminations = (int) Double.parseDouble(data[7]);
		}

		input = input.substring(input.indexOf("#") + 1);

		if (input.indexOf("bulletinfo") >= 0 && input.indexOf("#") >= 0
			&& input.indexOf("#") > input.indexOf("bulletinfo"))
		{
		    bulletData = input.substring(input.indexOf("bulletinfo"), input.indexOf("#"));
		    data = bulletData.split(":");

		    int j = 0;
		    while (j * 4 + 3 < data.length)
		    {
			Bullet b = new Bullet(Double.parseDouble(data[j * 4 + 1]), Double.parseDouble(data[j * 4 + 2]),
				Double.parseDouble(data[j * 4 + 3]), receivedPlayer);
			receivedPlayer.addBullet(b);

			j++;
		    }
		}
	    }

	}

	if (player.getHealth() < 1)
	{
	    player = new Player(0, 0, playerName, true);
	    sendEliminated = true;
	}

	if (receivedPlayer != null)
	    receivedPlayer.draw(this);

	checkBullets();

	sendPlayerInfo();
	sendTimerInfo();
	sendBulletInfo();

	popMatrix();
    }

    /**
     * sends playerInfo to the server
     */
    public void sendPlayerInfo()
    {
	s.write("playerinfo" + ":" + player.getX() + ":" + player.getY() + ":" + player.getName() + ":"
		+ player.getHealth() + ":" + player.getDirection() + ":" + sendEliminated + ":" + eliminations + ":");
	if (sendEliminated)
	{
	    sendEliminated = false;
	}

	if (receivedPlayer != null)
	{
	    s.write("playerinfo" + ":" + receivedPlayer.getX() + ":" + receivedPlayer.getY() + ":"
		    + receivedPlayer.getName() + ":" + receivedPlayer.getHealth() + ":" + receivedPlayer.getDirection()
		    + ":" + sendEliminated + ":" + eliminations + ":");
	}

	s.write("#");
    }

    /**
     * sends timer info to the server
     */
    public void sendTimerInfo()
    {
	s.write("timerinfo" + ":" + interval + ":");
	s.write("#");
    }

    /**
     * sends bullet info to the server
     */
    public void sendBulletInfo()
    {
	for (Bullet b : player.getBullets())
	{
	    s.write("bulletinfo" + ":" + b.getXCoord() + ":" + b.getYCoord() + ":" + b.getDirection() + ":"
		    + player.getName() + ":");
	}

	if (receivedPlayer != null)
	{
	    for (Bullet b : receivedPlayer.getBullets())
	    {
		s.write("bulletinfo" + ":" + b.getXCoord() + ":" + b.getYCoord() + ":" + b.getDirection() + ":"
			+ receivedPlayer.getName() + ":");
	    }
	}
	s.write("#");
    }

    /**
     * checks to see if the bullets are hitting the player
     */
    public void checkBullets()
    {
	for (int i = 0; i < player.getBullets().size(); i++)
	{
	    if (receivedPlayer != null && receivedPlayer.isHit(player.getBullets().get(i)))
	    {
		receivedPlayer.decHealth(10);
		player.getBullets().remove(player.getBullets().get(i));
	    }
	}

	if (receivedPlayer != null)
	{
	    for (int i = 0; i < receivedPlayer.getBullets().size(); i++)
	    {
		if (player.isHit(receivedPlayer.getBullets().get(i)))
		{
		    player.decHealth(10);
		    receivedPlayer.getBullets().remove(receivedPlayer.getBullets().get(i));
		}
	    }
	}
    }
}
