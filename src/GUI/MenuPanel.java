package GUI;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;


public class MenuPanel extends JPanel implements ActionListener 
{
	
	private Main w;
	
	public MenuPanel(Main w) 
	{
		this.w = w;
		JPanel p = new JPanel();
		p.setBackground(new Color(0,0,0,0));  // Panel is transparent
		
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));	
		p.add(Box.createVerticalStrut(300));   // Move down by 300 pixels  
		
		JButton button = new JButton("Play");
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
		
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
		g.drawString("The Title", 305, 200);
		
		g2.setTransform(af);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		w.changePanel();
	}
	
}