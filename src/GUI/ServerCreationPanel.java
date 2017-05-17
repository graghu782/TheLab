package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ServerCreationPanel extends JPanel implements ActionListener
{
    private Main w;

    public ServerCreationPanel(Main w)
    {
	this.w = w;
	JPanel p = new JPanel();
	p.setBackground(new Color(0, 0, 0, 0)); // Panel is transparent
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

	JButton button = new JButton("Create");
	button.addActionListener(this);

	p.add(button);
	add(p);
    }

    public void actionPerformed(ActionEvent e)
    {
	w.changePanel("menu");
    }
}
