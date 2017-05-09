package GUI;

import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;

public class Main {

	public static void main(String args[]) {
		DrawingSurface drawing = new DrawingSurface();
		
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		
		JFrame window = (JFrame)canvas.getFrame();

		window.setSize(1000, 500);
		window.setMinimumSize(new Dimension(100, 100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		window.setVisible(true);
	}
}
