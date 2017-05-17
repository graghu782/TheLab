package GUI;

import java.awt.*;
import javax.swing.*;

import Networking.Server;

import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class MenuPanel extends JPanel implements ActionListener
{

    private Main w;

    private JButton sandbox;
    private JButton startServer;
    private JButton joinServer;
    private JButton instructions;

    public MenuPanel(Main w)
    {
	this.w = w;
	JPanel p = new JPanel();
	p.setBackground(new Color(0, 0, 0, 0)); // Panel is transparent

	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	p.add(Box.createVerticalStrut(300)); // Move down by 300 pixels

	sandbox = new JButton("Sandbox");
	instructions = new JButton("Instructions");
	startServer = new JButton("Start Server");
	joinServer = new JButton("Join Server");


	sandbox.addActionListener(this);
	startServer.addActionListener(this);
	joinServer.addActionListener(this);
	instructions.addActionListener(this);

	p.add(sandbox);
	p.add(startServer);
	p.add(joinServer);
	p.add(instructions);
	add(p);
    }

    public void paintComponent(Graphics g)
    {
	super.paintComponent(g);

	double ratioX = getWidth() / 800.0;
	double ratioY = getHeight() / 600.0;

	Graphics2D g2 = (Graphics2D) g;

	AffineTransform af = g2.getTransform();

	g2.scale(ratioX, ratioY);

	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
	g.drawString("The Lab", 315, 115);

	g2.setTransform(af);
    }

    public void actionPerformed(ActionEvent e)
    {
	if (e.getSource() == sandbox)
	{
	    w.changePanel("game");
	}
	else if(e.getSource() == startServer)
	{
	    w.changePanel("servermenu");
	}
	else if(e.getSource() == joinServer) 
	{
	    w.changePanel("joinmenu");
	}
	else if(e.getSource() == instructions)
	{
	    w.changePanel("instructions");
	}

    }

}