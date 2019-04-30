package padsof.swing;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.swing.items.StandardButton;

@SuppressWarnings("serial")
public class UserActionsAdminPanel extends JPanel {
	private StandardButton addAudio;
	private StandardButton getPremium;
	
	public UserActionsAdminPanel() {
		
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		addAudio = new StandardButton("Añadir un audio", 200, 40);
		getPremium = new StandardButton("Panel de admin.", 200, 40);
		
		this.add(addAudio);
		this.add(getPremium);
		
		layout.putConstraint(SpringLayout.WEST, addAudio, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, addAudio, 5, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, getPremium, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, getPremium, 50, SpringLayout.NORTH, this);
		
		this.setPreferredSize(new Dimension(200, 125));
	}
}
