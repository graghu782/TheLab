package GUI;

import Gameplay.Player;
import processing.net.Client;
import processing.net.Server;

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
			players[i] = new Player(Double.parseDouble(data[0]), Double.parseDouble(data[1]), data[2], false);   
			players[i].setHealth((int)Double.parseDouble(data[3]));
			players[i].setDirection(Double.parseDouble(data[4])); 
		    } catch(Exception e) {
		    }
		}

	    }
	}

	for(int i = 0; i < players.length; i++) {
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
    }
}
