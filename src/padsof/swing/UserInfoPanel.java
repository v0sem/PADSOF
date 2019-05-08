package padsof.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.swing.items.StandardButton;
import padsof.system.System;
import padsof.user.User;

/**
 * Panel que muestra el usuario logueado y el boton 
 * de cerrar sesion si esta logueado
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
@SuppressWarnings("serial")
public class UserInfoPanel extends JPanel {
	
	private JLabel nick;
	
	private JLabel state;
	
	private StandardButton button;
	
	/**
	 * Constructor de UserInfoPanel
	 */
	public UserInfoPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		User loggedUser = System.getInstance().getLoggedUser();
		button = new StandardButton("Cerrar sesion", 200, 40);
		state = new JLabel("(Sesion no iniciada)");
		state.setFont(new Font("Arial", Font.BOLD, 14));
		if (loggedUser == null) {
			nick = new JLabel("Usuario anonimo");
			this.add(state);
		} else {
			nick = new JLabel("Usuario: " + loggedUser.getNick());	
			this.add(button);
		}
		nick.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(nick);
		
		layout.putConstraint(SpringLayout.WEST, nick, -95, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nick, 10, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, button, -100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.WEST, state, -95, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, state, 38, SpringLayout.NORTH, this);
		
		this.setPreferredSize(new Dimension(200, 65));
	}
	
	/**
	 * Establece el controlador del panel
	 * @param controlador
	 */
	public void setControlador(ActionListener controlador){
		this.button.addActionListener(controlador);
	}
}
