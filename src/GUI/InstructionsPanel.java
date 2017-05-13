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

public class InstructionsPanel extends JPanel implements ActionListener 
{
	private Main w;
	
	public InstructionsPanel(Main w)
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
		
		double ratioX = getWidth() / 800.0;
		double ratioY = getHeight() / 600.0;
		
		Graphics2D g2 = (Graphics2D)g;
		
		AffineTransform af = g2.getTransform();
		
		g2.scale(ratioX,ratioY);
		
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		g.drawString("Instructions: ", 100, 100);
		
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		g.drawString("Move around the map with the WASD keys and kill as many enemy players as possible.", 100, 150);
		g.drawString("The matches are all time based, and the team, or individual with the most kills wins!", 100, 200);
		
		g2.setTransform(af);
	}
	
	
	public void actionPerformed(ActionEvent e) 
	{
		w.changePanel(0);
	}
}
