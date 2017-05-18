package Gameplay;

import processing.core.PApplet;

public class Hud
{
    private static final double MAX_HEALTH = 100.0;

    private double health;
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

    public void draw(PApplet drawer, int x, int y)
    {
	drawer.text(playerName, x, y);
	// display rectangle which uses ratio to draw the health bar
    }
}
