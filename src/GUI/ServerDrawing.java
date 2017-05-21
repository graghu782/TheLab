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

	for(int i = 0; i < s.clientCount; i++) {
	    if(s.clients[i] != null) {
		input = s.clients[i].readString();
		if(input != null) {
		    data = input.split(":");
		    try {
			if(players[i] != null) {
			    players[i].update(Double.parseDouble(data[0]), Double.parseDouble(data[1]));
			}
			else {
			    players[i] = new Player(Double.parseDouble(data[0]), Double.parseDouble(data[1]), data[2], false);   
			}
			players[i].setHealth((int)Double.parseDouble(data[3]));
			players[i].setDirection(Double.parseDouble(data[4])); 
		    } catch(Exception e) {
		    }
		    finally {
			if(players[i] != null) {
			    players[i].draw(this);
			}
		    }
		}

	    }
	    
	    if(players[i] != null) {
		players[i].draw(this);
	    }
	}

	popMatrix();
    }

    public void sendPlayerInfo()
    {
	s.write(player.getX() + ":" + player.getY() + ":" + player.getName() + ":" + player.getHealth() + ":" + player.getDirection() + ":");
	
	for(int i = 0; i < s.clientCount; i++) {
	    if(players[i] != null) {
		s.write(players[i].getX() + ":" + players[i].getY() + ":" + players[i].getName() + ":" + players[i].getHealth() + ":" + players[i].getDirection() + ":");

	    }
	}
//	for(Bullet b : player.getBullets())
//	{
//	    if(s != null)
//		s.write(b.getX() + ":" + b.getY() + ":" + b.getDirection() + ":");
//	}
    }
}
