package Gameplay;

import java.awt.Color;
import processing.core.PApplet;

public class Border {
	double x, y, x2, y2;
	public Border(double x, double y, double x2, double y2){
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
	}
	public void draw(PApplet drawer){
		drawer.noFill();
		drawer.strokeWeight(10);
		drawer.rect((float)x,(float)y,(float)x2,(float)y2);
		drawer.fill(255);
		drawer.strokeWeight(1);
	}
}
