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

@SuppressWarnings("serial")
public class RegisterPanel extends JPanel {

	private JLabel nickLabel;
	private JTextField nickField;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel dateLabel;
	private JTextField dateField;
	private StandardButton regButton;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JLabel title;
	private SideBarPanel sideBar;

	public RegisterPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		// PANEL DESCRIPTION / TITLE	
		title = new JLabel("Registrarse");
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
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nickLabel, 23, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nickLabel, -22, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, nickField, 0, SpringLayout.NORTH, nickLabel);
		layout.putConstraint(SpringLayout.WEST, nickField, 6, SpringLayout.EAST, nickLabel);
		
		// NAME
		nameLabel = new JLabel("Nombre de autor: ");
		nameLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		this.add(nameLabel);
		nameField = new JTextField(20);
		this.add(nameField);
		layout.putConstraint(SpringLayout.NORTH, nameLabel, 5, SpringLayout.SOUTH, nickLabel);
		layout.putConstraint(SpringLayout.EAST, nameLabel, 0, SpringLayout.EAST, nickLabel);
		layout.putConstraint(SpringLayout.NORTH, nameField, 0, SpringLayout.NORTH, nameLabel);
		layout.putConstraint(SpringLayout.WEST, nameField, 6, SpringLayout.EAST, nameLabel);
		
		// DATE
		dateLabel = new JLabel("Fecha de nacimiento: ");
		dateLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		this.add(dateLabel);
		dateField = new JTextField(20);
		this.add(dateField);
		layout.putConstraint(SpringLayout.NORTH, dateLabel, 5, SpringLayout.SOUTH, nameLabel);
		layout.putConstraint(SpringLayout.EAST, dateLabel, 0, SpringLayout.EAST, nameLabel);
		layout.putConstraint(SpringLayout.NORTH, dateField, 0, SpringLayout.NORTH, dateLabel);
		layout.putConstraint(SpringLayout.WEST, dateField, 6, SpringLayout.EAST, dateLabel);		
		
		// PASS
		passwordLabel = new JLabel("Contrasena: ");
		passwordLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		this.add(passwordLabel);
		passwordField = new JPasswordField(20);
		this.add(passwordField);
		layout.putConstraint(SpringLayout.NORTH, passwordLabel, 5, SpringLayout.SOUTH, dateLabel);
		layout.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, dateLabel);
		layout.putConstraint(SpringLayout.NORTH, passwordField, 0, SpringLayout.NORTH, passwordLabel);
		layout.putConstraint(SpringLayout.WEST, passwordField, 6, SpringLayout.EAST, passwordLabel);

		// BUTTON
		regButton = new StandardButton("Registrarse", 150, 50);
		this.add(regButton);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, regButton, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, regButton, 130, SpringLayout.VERTICAL_CENTER, this);
		this.setPreferredSize(new Dimension(400, 150));
	}
	
	public void updateSideBar() {
		this.remove(sideBar);
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
	}
	
	public void setControlador(ActionListener controlador){
		this.regButton.addActionListener(controlador);
	}
	
	public StandardButton getRegisterButton() {
		return this.regButton;
	}
	
	public SideBarPanel getSideBar() {
		return this.sideBar;
	}
	
	public JTextField getNick() {
		return nickField;
	}
	
	public JTextField getAuthorName() {
		return nameField;
	}

	public JTextField getDate() {
		return dateField;
	}
	
	public JPasswordField getPassword() {
		return passwordField;
	}
}
