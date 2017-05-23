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

public class OptionPanel extends JPanel implements ActionListener
{
    private Main w;
/**
 * creates new OptionPanel
 * @param w name of JPanel
 */
    public OptionPanel(Main w)
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
 * changes panel to menu panel if menu button is pressed
 */
    public void actionPerformed(ActionEvent e)
    {
	w.changePanel("menu");
    }
}
