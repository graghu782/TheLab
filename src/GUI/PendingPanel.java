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
import javax.swing.JTextArea;

import Networking.Client;

public class PendingPanel extends JPanel implements ActionListener
{
    private Main w;
    private JButton startButton;
    private JTextArea playerList;
    
    public PendingPanel(Main w)
    {
	this.w = w;
	JPanel p = new JPanel();
	p.setBackground(new Color(0, 0, 0, 0)); // Panel is transparent
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	
	startButton = new JButton("Start");
	playerList = new JTextArea("Players in game: ");
	
	startButton.addActionListener(this);
	
	p.add(startButton);
	add(p);
    }

    public void actionPerformed(ActionEvent e)
    {
	
    }
}