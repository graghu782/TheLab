package Gameplay;

import java.awt.Color;

import processing.core.PApplet;

public class Player {
	private int x, y;
	private Color color;
	private String name;
	
	
	public Player(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	public void draw(PApplet drawer) {
		drawer.stroke(10);
		drawer.rect(x, y, x+30, y+30);
	}
}
