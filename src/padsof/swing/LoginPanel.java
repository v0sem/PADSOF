package padsof.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import padsof.swing.items.StandardButton;

/**
 * Panel para conectarte con tu cuenta
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
@SuppressWarnings("serial")
public class LoginPanel extends JPanel {

	private JLabel nickLabel;
	private JTextField nickField;
	private StandardButton loginButton;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JLabel title;
	private SideBarPanel sideBar;

	/**
	 * Constructor de LoginPanel
	 */
	public LoginPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		// PANEL DESCRIPTION / TITLE
		title = new JLabel("Iniciar sesion");
		title.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 22));
		this.add(title);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, title, -80, SpringLayout.VERTICAL_CENTER, this);
		
		// NICK
		nickLabel = new JLabel("Nick: ");
		nickLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		this.add(nickLabel);
		nickField = new JTextField(20);
		this.add(nickField);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nickLabel, -5, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nickLabel, -22, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, nickField, 0, SpringLayout.NORTH, nickLabel);
		layout.putConstraint(SpringLayout.WEST, nickField, 6, SpringLayout.EAST, nickLabel);
		
		// PASS
		passwordLabel = new JLabel("Contrasena: ");
		passwordLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		this.add(passwordLabel);
		passwordField = new JPasswordField(20);
		this.add(passwordField);
		layout.putConstraint(SpringLayout.NORTH, passwordLabel, 5, SpringLayout.SOUTH, nickLabel);
		layout.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, nickLabel);
		layout.putConstraint(SpringLayout.NORTH, passwordField, 0, SpringLayout.NORTH, passwordLabel);
		layout.putConstraint(SpringLayout.WEST, passwordField, 6, SpringLayout.EAST, passwordLabel);
	
		// BUTTON
		loginButton = new StandardButton("Entrar", 150, 50);
		this.add(loginButton);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginButton, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, loginButton, 70, SpringLayout.VERTICAL_CENTER, this);
		this.setPreferredSize(new Dimension(400, 150));
	}

	/**
	 * Updater del sidebar
	 */
	public void updateSideBar() {
		this.remove(sideBar);
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
	}
	
	
	public void setControlador(ActionListener controlador){
		this.loginButton.addActionListener(controlador);
	}
	
	public JTextField getNick() {
		return nickField;
	}

	public JPasswordField getPassword() {
		return passwordField;
	}
}
