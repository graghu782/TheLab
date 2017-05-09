package GUI;
import Gameplay.Player;
import processing.core.PApplet;

public class DrawingSurface extends PApplet {
	private Player player;

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
		background(255);
		fill(255);
		player.draw(this);
	}
	
	public void keyPressed() {
	
		if(key == 'w') {
			player.move(0, -10);
		}
		if(key == 'a') {
			player.move(-10, 0);
		}
		if(key == 's') { 
			player.move(0, 10);
		}
		if(key == 'd') {
			player.move(10, 0);
		}
		System.out.println(player.x + " " + player.y + " " + player.length + " " + player.width);
	}
}

