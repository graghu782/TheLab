package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import processing.awt.PSurfaceAWT;

public class Main extends JFrame
{
    private JPanel cardPanel;
    private CardLayout cl;

    private MenuPanel menuPanel;
    private InstructionsPanel instructionsPanel;
    private OptionPanel optionPanel;
    private ServerCreationPanel serverCreatePanel;
    private ServerJoinPanel serverJoinPanel;
    private PendingPanel pendingPanel;
 
    private JFrame window;
    
    private DrawingSurface drawing;
    
    private String IP;
    private String playerName = "";
    private boolean createdServer;

    public Main(String title)
    {
	super(title);
	setBounds(250, 250, 800, 600);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);

	cardPanel = new JPanel();
	cl = new CardLayout(); 
	cardPanel.setLayout(cl);

	menuPanel = new MenuPanel(this);
	instructionsPanel = new InstructionsPanel(this);
	optionPanel = new OptionPanel(this);
	serverCreatePanel = new ServerCreationPanel(this);
	serverJoinPanel = new ServerJoinPanel(this);
	pendingPanel = new PendingPanel(this);

	cardPanel.add(menuPanel, "menu");
	cardPanel.add(instructionsPanel, "instructions");
	cardPanel.add(optionPanel, "game");
	cardPanel.add(serverCreatePanel, "servermenu");
	cardPanel.add(serverJoinPanel, "joinmenu");
	cardPanel.add(pendingPanel, "pending");
	add(cardPanel);
		
	setVisible(true);
    }

    public static void main(String args[]) throws UnknownHostException, IOException
    {
	Main w = new Main("The Lab");
    }
    
    public void setIP(String s)
    {
	IP = s;
    }
    
    public void setIfServer(boolean b)
    {
	createdServer = b;
    }
    
    public void setName(String s)
    {
	playerName = s;
    }

    public void changePanel(String s)
    {
	cl.show(cardPanel, s);

	setBounds(200, 200, 800, 600);

	if (s.equals("game"))
	{
	    setSize(300, 100);
	    setBounds(200, 200, 800, 100);
	    
	    if(createdServer)
	    {
		drawing = new ServerDrawing(IP, playerName);
	    }
	    else
	    {
		drawing = new ClientDrawing(IP, playerName);
	    }
	    
	    PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();;
	    PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();

	    window = (JFrame) canvas.getFrame();

	    window.setBounds(200, 300, 800, 600);
	    window.setMinimumSize(new Dimension(100, 100));
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setResizable(false);
	    window.setVisible(true);
	}
	else if (drawing != null && window != null)
	{
	    drawing.noLoop();
	    drawing.dispose();
	    window.dispose();
	}
    }
}
