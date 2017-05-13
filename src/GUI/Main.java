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
	private OptionPanel panel3;
	
	private JFrame window;
	private DrawingSurface drawing;
	
	private boolean[] panelsInPlace;
	
	public Main(String title)
	{
		super(title);
		setBounds(250, 250, 800, 600);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    
	    panelsInPlace = new boolean[3];
	    
	    cardPanel = new JPanel();
	    CardLayout cl = new CardLayout();
	    cardPanel.setLayout(cl);
	    
		panel1 = new MenuPanel(this); 
		panel2 = new InstructionsPanel(this);
		panel3 = new OptionPanel(this);
		
	    cardPanel.add(panel1,"1"); // Card is named "1"
	    add(cardPanel);
	    panelsInPlace[0] = true;
	
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
			setSize(800, 600);
			
			if(panelsInPlace[1])
			{
				cardPanel.remove(panel2);
				panelsInPlace[1] = false;
			}
			if(panelsInPlace[2])
			{
				cardPanel.remove(panel3);
				panelsInPlace[2] = false;
				
				window.setVisible(false);
				window.dispose();
				
				drawing.end();
			}
			
		    cardPanel.add(panel1,"1");
		    add(cardPanel);
		    panelsInPlace[0] = true;
		
		    setVisible(true);
		}
		else if(x == 1)
		{
			if(panelsInPlace[0])
			{
				cardPanel.remove(panel1);
				panelsInPlace[0] = false;
			}
			if(panelsInPlace[2])
			{
				cardPanel.remove(panel3);
				panelsInPlace[2] = false;
			}
			
			cardPanel.add(panel2, "2");
			add(cardPanel);
			panelsInPlace[1] = true;
			
			setVisible(true);
		}
		else if(x == 2)
		{
			cardPanel.remove(panel1);
			cardPanel.add(panel3, "3");
			add(cardPanel);
			panelsInPlace[2] = true;
			
			setSize(300, 100);
			
			drawing = new DrawingSurface();
			
			PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
			PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
			
			window = (JFrame)canvas.getFrame();
	
			window.setSize(800, 600);
			window.setMinimumSize(new Dimension(100, 100));
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setResizable(false);
			window.setVisible(true);
		}
	}
}
