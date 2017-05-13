package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

import processing.awt.PSurfaceAWT;

public class Main extends JFrame
{
	private JPanel cardPanel;
	
	private MenuPanel panel1;
	private InstructionsPanel panel2;
	
	public Main(String title)
	{
		super(title);
		setBounds(100, 100, 800, 600);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    
	    cardPanel = new JPanel();
	    CardLayout cl = new CardLayout();
	    cardPanel.setLayout(cl);
	    
		panel1 = new MenuPanel(this); 
		panel2 = new InstructionsPanel(this);
		
	    cardPanel.add(panel1,"1"); // Card is named "1"
	    add(cardPanel);
	
	    setVisible(true);
	}
	
	public static void main(String args[]) 
	{	
		Main w = new Main("The Lab");
	}
	
	public void changePanel(int x)
	{
		if(x == 0)
		{		 
			cardPanel.remove(panel2);
		    cardPanel.add(panel1,"1"); // Card is named "1"
		    add(cardPanel);
		
		    setVisible(true);
		}
		else if(x == 1)
		{
			cardPanel.remove(panel1);
			cardPanel.add(panel2, "2");
			add(cardPanel);
			
			setVisible(true);
		}
		else if(x == 2)
		{
			this.setVisible(false);
			DrawingSurface drawing = new DrawingSurface();
			
			PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
			PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
			
			JFrame window = (JFrame)canvas.getFrame();
	
			window.setSize(800, 600);
			window.setMinimumSize(new Dimension(100, 100));
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setResizable(false);
			window.setVisible(true);
			
			JFrame optionsWindow = new JFrame("Options");
		}
	}
}
