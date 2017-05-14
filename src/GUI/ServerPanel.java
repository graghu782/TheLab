package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ServerPanel extends JPanel implements ActionListener
{
	private Main w;
	
	public ServerPanel(Main w)
	{
		this.w = w;
		JPanel p = new JPanel();
		p.setBackground(new Color(0,0,0,0));  // Panel is transparent
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		
		JButton button = new JButton("Back");
		button.addActionListener(this);
		
		p.add(button);
		add(p);
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		double ratioX = getWidth() / 100.0;
		double ratioY = getHeight() / 100.0;
		
		Graphics2D g2 = (Graphics2D)g;
		
		AffineTransform af = g2.getTransform();
		g2.scale(ratioX,ratioY);
		g2.setTransform(af);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		w.changePanel(0);
	}
}
