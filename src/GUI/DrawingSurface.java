package GUI;
import Gameplay.Player;
import processing.core.PApplet;

public class DrawingSurface extends PApplet {
	Player player;

	public DrawingSurface () {
		runSketch();
	}

	public void setup() {
		background(255);
		player = new Player(100, 100, "noob");
	}

	// The statements in draw() are executed 60 times a second until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.


	public void draw() {		
		player.draw(this);
	}
	
}

