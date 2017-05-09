package Gameplay;

import java.awt.Color;

import processing.core.PApplet;

public class Player {
	public double x, y, width, length;
	private Color color;
	private String name;
	
	
	public Player(double x, double y, String name) {
		this.x = x;
		this.y = y;
		width = 30;
		length = 70;
		this.name = name;
	}
	
	public void move(double x, double y) {
		this.x += x;
		this.y += y;
	}

	public void draw(PApplet drawer) {
		drawer.rect((float)x, (float)y, (float)(width), (float)(length));
	}
	
}
