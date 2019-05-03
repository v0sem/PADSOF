package padsof.swing.items;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class StandardButton extends JButton{

	public StandardButton(String text, int x, int y) {
		super(text);
		Color c1 = new Color(0xff76cf); //button background color
		Color c2 = new Color(0x212121); //text color
		this.setPreferredSize(new Dimension(x, y));
		this.setFocusPainted(false);
		this.setBackground(c1);
		this.setForeground(c2);
		this.setFont(new Font("Arial", Font.BOLD, 14));
	}
}
