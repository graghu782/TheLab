package Gameplay;

import processing.core.PApplet;

public class Arm {

	private double x,y,mx,my;
	public Arm (double xx,double xy,double xmx,double xmy){
		x = xx;
		y = xy;
		mx = xmx;
		my = xmy;
	}
	public void draw(PApplet drawer){
		drawer.line((float)x,(float)y,(float)mx,(float)my);
	}
}
