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
/**
 * gives the user the instructions for the game
 * @param w the panel to draw the instruction panel in
 */
    public InstructionsPanel(Main w)
    {
	this.w = w;
	JPanel p = new JPanel();
	p.setBackground(new Color(0, 0, 0, 0)); // Panel is transparent
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

	JButton button = new JButton("Back");
	button.addActionListener(this);

	p.add(button);
	add(p);
    }
/**
 * draws everything into the panel
 */
    public void paintComponent(Graphics g)
    {
	super.paintComponent(g);

	double ratioX = getWidth() / 800.0;
	double ratioY = getHeight() / 600.0;

	Graphics2D g2 = (Graphics2D) g;

	AffineTransform af = g2.getTransform();

	g2.scale(ratioX, ratioY);

	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
	g.drawString("Instructions: ", 100, 100);

	g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
	g.drawString("Move around the map with the WASD keys and kill as many enemy players as possible.", 100, 150);
	g.drawString("Use the mouse to aim and click to shoot.", 100, 180);
	g.drawString("You can also click and hold your mouse to fire bullets.", 100, 210);
	g.drawString("Remember, there is a limited amount of bullets you can fire before you have to reload!", 100,
		240);
	g.drawString("The matches are all time based, and the team, or individual with the most kills wins!", 100, 270);

	g2.setTransform(af);
    }
/**
 * checks to see if the user presses the back button
 */
    public void actionPerformed(ActionEvent e)
    {
	w.changePanel("menu");
    }
}
