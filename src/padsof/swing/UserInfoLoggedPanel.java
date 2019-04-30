package padsof.swing;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.sistem.Sistem;
import padsof.swing.items.StandardButton;

@SuppressWarnings("serial")
public class UserInfoLoggedPanel extends JPanel {
	
	private JLabel nick;
	
	private StandardButton button;
	
	public UserInfoLoggedPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		nick  = new JLabel();
		nick.setText("Usuario: @" + Sistem.getInstance().getLoggedUser().getNick());
		
		button = new StandardButton("Cerrar sesion", 200, 40);
		
		this.add(nick);
		this.add(button);
		
		layout.putConstraint(SpringLayout.WEST, nick, -100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nick, 5, SpringLayout.NORTH, this);

		
		layout.putConstraint(SpringLayout.WEST, button, -100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, this);
		
		this.setPreferredSize(new Dimension(200, 55));
	}
}
