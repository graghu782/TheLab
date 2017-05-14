package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Networking.frontend.NetworkManagementPanel;

import processing.awt.PSurfaceAWT;

public class Main extends JFrame
{
    private JPanel cardPanel;

    private MenuPanel panel1;
    private InstructionsPanel panel2;
    private OptionPanel panel3;
    private NetworkManagementPanel panel4;

    private JFrame window;
    private DrawingSurface drawing;

    public Main(String title)
    {
	super(title);
	setBounds(250, 250, 800, 600);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	    
	cardPanel = new JPanel();
	CardLayout cl = new CardLayout();
	cardPanel.setLayout(cl);
	    
	panel1 = new MenuPanel(this); 
	panel2 = new InstructionsPanel(this);
	panel3 = new OptionPanel(this);

		
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
	if (x == 0)
	{
	    setSize(800, 600);

	    cardPanel.remove(panel3);
	    cardPanel.remove(panel2);
	    //cardPanel.removeAll();
	    
	    if(drawing != null)
		drawing.noLoop();
	    if(window != null)
		window.dispose();

	    cardPanel.add(panel1, "1");
	    add(cardPanel);
	    
	    setVisible(true);
	}
	else if (x == 1)
	{
	    cardPanel.remove(panel1);
	    cardPanel.add(panel2, "2");
	    add(cardPanel);

	    setVisible(true);
	}
	else if (x == 2)
	{
	    cardPanel.remove(panel1);
	    cardPanel.remove(panel2);
	    cardPanel.add(panel3, "3");
	    add(cardPanel);
	    setSize(300, 100);

	    drawing = new DrawingSurface();

	    PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
	    PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();

	    window = (JFrame) canvas.getFrame();

	    window.setSize(800, 600);
	    window.setMinimumSize(new Dimension(100, 100));
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setResizable(false);
	    window.setVisible(true);
	}
    }
}
