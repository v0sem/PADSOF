package padsof.swing.items;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class StandardButton extends JButton{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StandardButton(String text, int x, int y) {
		super(text);
		// Default values orange with white text
		Color c = new Color(255, 87, 34);
		this.setPreferredSize(new Dimension(x, y));
		this.setFocusPainted(false);
		this.setContentAreaFilled(true);
		this.setBackground(c);
		this.setForeground(Color.WHITE);
	}

}
