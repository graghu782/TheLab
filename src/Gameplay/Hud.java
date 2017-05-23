package Gameplay;

import processing.core.PApplet;

public class Hud
{
    private static final double MAX_HEALTH = 100.0;

    private int health;
    private double ratio;
    private String playerName;
/**
 * creates a hud for the player
 * @param playerName the name of the player
 */
    public Hud(String playerName)
    {
	this.playerName = playerName;
    }
/**
 * calculates the ratio for the player hud
 */
    public void calculateRatio()
    {
	ratio = health / MAX_HEALTH;
    }
    /**
     * updates the health of the player
     * @param health current health of the player
     */
    public void updateHealth(int health)
    {
	this.health = health;
    }
/**
 * draws the hud
 * @param drawer the PApplet used
 * @param x x-coordinate of hud
 * @param y y-coordinate of hud
 */
    public void draw(PApplet drawer, float x, float y)
    {
	drawer.fill(0);
	
	drawer.text(playerName, x, y - 25);
	drawer.text("HP: " + health, x, y - 15);
	// display rectangle which uses ratio to draw the health bar
    }
}
