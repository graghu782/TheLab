package Gameplay;

import java.awt.Color;

import processing.core.PApplet;

public class Character {
	private int x, y;
	private Color color;
	private String name;
	
	
	public Character(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	public void draw(PApplet drawer) {
		drawer.rect(x, y, x+30, y+30);
	}
}
