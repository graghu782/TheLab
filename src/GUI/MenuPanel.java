package GUI;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class MenuPanel extends JPanel implements ActionListener
{

    private Main w;

    private JButton startServer;
    private JButton joinServer;
    private JButton instructions;
    
    private JLabel title;
    private JLabel background;

    public MenuPanel(Main w)
    {
	this.w = w;
	
	
	JPanel mainPanel = new JPanel(new BorderLayout());
	JPanel buttonPanel = new JPanel();
	buttonPanel.setBackground(new Color(0, 0, 0, 0)); // Panel is transparent

	buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
	// Move down by 300 pixels
	
	instructions = new JButton("Instructions");
	startServer = new JButton("Start Server");
	joinServer = new JButton("Join Server");

	title = new JLabel();
	title.setIcon(new ImageIcon("LabTitle.png"));

	startServer.addActionListener(this);
	joinServer.addActionListener(this);
	instructions.addActionListener(this);

	
	buttonPanel.add(Box.createHorizontalStrut(100));
	buttonPanel.add(startServer);
	buttonPanel.add(joinServer);
	buttonPanel.add(instructions);
	
	mainPanel.add(buttonPanel, BorderLayout.CENTER);
	mainPanel.add(title, BorderLayout.NORTH);
	
	add(mainPanel);
    }

    public void paintComponent(Graphics g)
    {
	super.paintComponent(g);

	double ratioX = getWidth() / 800.0;
	double ratioY = getHeight() / 600.0;

	Graphics2D g2 = (Graphics2D) g;

	AffineTransform af = g2.getTransform();

	g2.scale(ratioX, ratioY);


	/*
	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
	g.drawString("The Lab", 315, 115);
	*/

	g2.setTransform(af);
    }

    public void actionPerformed(ActionEvent e)
    {
	if(e.getSource() == startServer)
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