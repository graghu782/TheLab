package Gameplay;

import processing.core.PApplet;

public class Hud
{
    private static final double MAX_HEALTH = 100.0;

    private int health;
    private double ratio;
    private String playerName;

    public Hud(String playerName)
    {
	this.playerName = playerName;
    }

    public void calculateRatio()
    {
	ratio = health / MAX_HEALTH;
    }
    
    public void updateHealth(int health)
    {
	this.health = health;
    }

    public void draw(PApplet drawer, float x, float y)
    {
	drawer.fill(0);
	drawer.text(playerName, x, y - 25);
	drawer.text("HP: " + health, x, y - 15);
	// display rectangle which uses ratio to draw the health bar
    }
}
