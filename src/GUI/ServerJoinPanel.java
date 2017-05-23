package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServerJoinPanel extends JPanel implements ActionListener
{
    private Main w;
    private JTextField inputName;
    private JTextField inputIP;
    private JButton joinButton;
    private JButton menuButton;
/**
 * creates a new server join panel
 * @param w name of JPanel
 */
    public ServerJoinPanel(Main w)
    {
	this.w = w;
	JPanel p = new JPanel();
	p.setBackground(new Color(0, 0, 0, 0)); // Panel is transparent
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	p.add(Box.createVerticalStrut(150));

	inputName = new JTextField("Name here (Client)");
	inputIP = new JTextField("IP here");

	joinButton = new JButton("Join");
	menuButton = new JButton("Menu");

	joinButton.addActionListener(this);
	menuButton.addActionListener(this);

	p.add(inputName);
	p.add(inputIP);
	p.add(joinButton);
	p.add(menuButton);
	add(p);
    }
/**
 * draws the panel
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
	g.drawString("JOIN A SERVER", 225, 115);

	g2.setTransform(af);
    }
/**
 * checks to see what buttons are pressed and acts accordingly
 */
    public void actionPerformed(ActionEvent e)
    {
	if (e.getSource() == joinButton)
	{
	    String ip = inputIP.getText();
	    String name = inputName.getText();

	    /*
	    try
	    {
		Client client = new Client(ip, 4444);
		new Thread(client).start();
	    }
	    catch (IOException e1)
	    {
		e1.printStackTrace();
	    }
	    */
	    
	    w.setIP(ip);
	    w.setName(name);
	    w.setIfServer(false);
	    w.changePanel("game");
	}
	if (e.getSource() == menuButton)
	{
	    w.changePanel("menu");
	}
    }
}