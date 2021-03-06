package GUI;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
 
    private JFrame window;
    
    private DrawingSurface drawing;
    
    private String IP;
    private String playerName = "";
    private boolean createdServer;
    
    private JLabel background;
/**
 * creates a new menu and serverpanel
 * @param title name of JFrame
 */
    public Main(String title)
    {
	super(title);
	setBounds(250, 250, 800, 600);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	cardPanel = new JPanel();
	cl = new CardLayout(); 
	cardPanel.setLayout(cl);

	menuPanel = new MenuPanel(this);
	instructionsPanel = new InstructionsPanel(this);
	optionPanel = new OptionPanel(this);
	serverCreatePanel = new ServerCreationPanel(this);
	serverJoinPanel = new ServerJoinPanel(this);

	cardPanel.add(menuPanel, "menu");
	cardPanel.add(instructionsPanel, "instructions");
	cardPanel.add(optionPanel, "game");
	cardPanel.add(serverCreatePanel, "servermenu");
	cardPanel.add(serverJoinPanel, "joinmenu");
	
	add(cardPanel);

	setVisible(true);
    }
/**
 * main menu of the program
 * @param args
 * @throws UnknownHostException
 * @throws IOException
 */
    public static void main(String args[]) throws UnknownHostException, IOException
    {
	Main w = new Main("The Lab");
    }
    /**
     * sets the IP to s
     * @param s the string to set IP to
     */
    public void setIP(String s)
    {
	IP = s;
    }
    /**
     * checks and creates server if b is true
     * @param b true if server is created
     */
    public void setIfServer(boolean b)
    {
	createdServer = b;
    }
    /**
     * sets playername to s
     * @param s name of player
     */
    public void setName(String s)
    {
	playerName = s;
    }
/**
 * changes panel to panel s
 * @param s name of panel
 */
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
