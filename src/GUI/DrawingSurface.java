package GUI;
import processing.core.PApplet;

public class DrawingSurface extends PApplet {
	
	public DrawingSurface () {
		runSketch();
	}

	public void setup() {
		background(255);
		Gameplay.Character ch = new Gameplay.Character(50, 50, "LOL");
	}

	// The statements in draw() are executed 60 times a second until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.


	public void draw() {		
	}
	
}

