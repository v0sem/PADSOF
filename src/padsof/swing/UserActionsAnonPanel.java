package padsof.swing;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.swing.items.StandardButton;

@SuppressWarnings("serial")
public class UserActionsAnonPanel extends JPanel {
	
	private StandardButton addAudio;
	private StandardButton getPremium;
	
	public UserActionsAnonPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		addAudio = new StandardButton("Iniciar sesion", 200, 40);
		getPremium = new StandardButton("Registrarse", 200, 40);
		
		this.add(addAudio);
		this.add(getPremium);
		
		layout.putConstraint(SpringLayout.WEST, addAudio, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, addAudio, 5, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, getPremium, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, getPremium, 50, SpringLayout.NORTH, this);
		
		this.setPreferredSize(new Dimension(200, 125));
	}
}
