package padsof.swing.items;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class StandardButton extends JButton{

	public StandardButton(String text, int x, int y) {
		super(text);
		Color c1 = new Color(0xFF5722); //button background color. Pablo says it has to be 0xFF5722
		Color c2 = new Color(0x212121); //text color. Pablo says it has to be 0xFFFFFF
		this.setPreferredSize(new Dimension(x, y));
		this.setFocusPainted(false);
		this.setBackground(c1);
		this.setForeground(c2);
		this.setFont(new Font("Arial", Font.BOLD, 14));
	}
}
