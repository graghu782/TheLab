package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.*;

public class ServerCreationPanel extends JPanel implements ActionListener
{
    private Main w;

    private JTextArea IPText;
    private InetAddress myIP;

    private JButton joinButton;
    private JButton menuButton;
    
    private JTextField inputName;
/**
 * creates new ServerPanel
 * @param w name of JPanel
 */
    public ServerCreationPanel(Main w)
    {
	this.w = w;
	JPanel p = new JPanel();
	p.setBackground(new Color(0, 0, 0, 0)); // Panel is transparent
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	p.add(Box.createVerticalStrut(150));

	IPText = new JTextArea();
	try
	{
	    myIP = InetAddress.getLocalHost();
	    IPText.append("Your Hostname/IP address is " + myIP);
	}
	catch (UnknownHostException e)
	{
	    e.printStackTrace();
	    IPText.append("Error getting your IP address!");
	}
	
	inputName = new JTextField("Name here (Server)");

	joinButton = new JButton("Start");
	menuButton = new JButton("Menu");

	joinButton.addActionListener(this);
	menuButton.addActionListener(this);

	p.add(IPText);

	p.add(Box.createVerticalStrut(50));
	
	p.add(inputName);
	
	p.add(Box.createVerticalStrut(50));

	p.add(joinButton);
	p.add(menuButton);
	add(p);
    }
/**
 * prints out the panel into the JPanel
 */
    public void paintComponent(Graphics g)
    {
	super.paintComponent(g);

	double ratioX = getWidth() / 800.0;
	double ratioY = getHeight() / 600.0;

	Graphics2D g2 = (Graphics2D) g;

	AffineTransform af = g2.getTransform();

	g2.scale(ratioX, ratioY);

	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
	g.drawString("CREATE A SERVER", 225, 115);

	g2.setTransform(af);
    }
/**
 * checks to see what actions are performed and acts accordingly
 */
    public void actionPerformed(ActionEvent e)
    {
	if (e.getSource() == joinButton)
	{
	    /*
	    Server s = new Server(4444);
	    new Thread(s).start();
	    joinButton = null;
	    */
	    
	    w.setIP(myIP.toString().substring(myIP.toString().indexOf("/")));
	    w.setIfServer(true);
	    w.setName(inputName.getText());
	    w.changePanel("game");
	}
	if (e.getSource() == menuButton)
	{
	    w.changePanel("menu");

	}
    }
}