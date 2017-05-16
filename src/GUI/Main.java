package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import Networking.frontend.NetworkManagementPanel;

import processing.awt.PSurfaceAWT;

public class Main extends JFrame
{
    private JPanel cardPanel;
    private CardLayout cl;

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
	cl = new CardLayout();
	cardPanel.setLayout(cl);

	panel1 = new MenuPanel(this);
	panel2 = new InstructionsPanel(this);
	panel3 = new OptionPanel(this);
	panel4 = new NetworkManagementPanel("The Lab", 8, drawing);

	cardPanel.add(panel1, "menu");
	cardPanel.add(panel2, "instructions");
	cardPanel.add(panel3, "game");
	cardPanel.add(panel4, "servermenu");
	add(cardPanel);
		
	setVisible(true);
    }

    public static void main(String args[]) throws UnknownHostException, IOException
    {
	Networking n = new Networking("127.0.0.1", 4444);
	
	Main w = new Main("The Lab");
    }

    public void changePanel(String s)
    {
	cl.show(cardPanel, s);

	setSize(800, 600);

	if (s.equals("game"))
	{
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
	else if (drawing != null && window != null)
	{
	    drawing.noLoop();
	    window.dispose();
	}
    }
}
