package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

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

    public ServerJoinPanel(Main w)
    {
	this.w = w;
	JPanel p = new JPanel();
	p.setBackground(new Color(0, 0, 0, 0)); // Panel is transparent
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	p.add(Box.createVerticalStrut(150));
	
	inputName = new JTextField("Input name here");
	inputIP = new JTextField("Enter IP here");

	JButton joinButton = new JButton("Join");
	JButton menuButton = new JButton("Menu");
	joinButton.addActionListener(this);
	menuButton.addActionListener(this);

	p.add(inputName);
	p.add(inputIP);
	p.add(joinButton);
	p.add(menuButton);
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
	g.drawString("JOIN A SERVER", 225, 115);

	g2.setTransform(af);
    }

    public void actionPerformed(ActionEvent e)
    {
	String k = inputIP.getText();
	w.changePanel("menu");
    }
}