package padsof.swing;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import padsof.swing.items.StandardButton;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel {

	private JLabel nickLabel;
	private JTextField nickField;
	private StandardButton loginButton;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JLabel title;


	public LoginPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		//Creamos nuestros componentes
		nickLabel = new JLabel("Nick: ");
		nickLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		this.add(nickLabel);
		nickField = new JTextField(20);
		this.add(nickField);
		passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		this.add(passwordLabel);
		passwordField = new JPasswordField(20);
		this.add(passwordField);
		title = new JLabel("Iniciar sesion");
		title.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 22));
		this.add(title);
		
		// PANEL DESCRIPTION / TITLE
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 30, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 100, SpringLayout.NORTH, this);
		
		// NICK
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nickLabel, -100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nickLabel, -22, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, nickField, 0, SpringLayout.NORTH, nickLabel);
		layout.putConstraint(SpringLayout.WEST, nickField, 6, SpringLayout.EAST, nickLabel);
		
		// PASS
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordLabel, -117, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, passwordLabel, 5, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, passwordField, 0, SpringLayout.NORTH, passwordLabel);
		layout.putConstraint(SpringLayout.WEST, passwordField, 6, SpringLayout.EAST, passwordLabel);
	
		
		loginButton = new StandardButton("LOGIN", 150, 50);
		this.add(loginButton);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginButton, 30, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, loginButton, -30, SpringLayout.SOUTH, this);
		this.setPreferredSize(new Dimension(400, 150));
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
