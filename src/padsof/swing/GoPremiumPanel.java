package padsof.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import padsof.swing.items.StandardButton;

/**
 * Panel para convertirse en un usuario premium
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
@SuppressWarnings("serial")
public class GoPremiumPanel extends JPanel {

	private JLabel nameLabel;
	private JTextField nameField;
	private StandardButton button;
	private JLabel creditLabel;
	private JTextField creditField;
	private JLabel title;
	private SideBarPanel sideBar;

	/**
	 * Constructor de GoPremiumPanel
	 */
	public GoPremiumPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		// PANEL DESCRIPTION / TITLE
		title = new JLabel("Obtener premium");
		title.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 22));
		this.add(title);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, title, -80, SpringLayout.VERTICAL_CENTER, this);
		
		// NAME
		nameLabel = new JLabel("Nombre de titular: ");
		nameLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		this.add(nameLabel);
		nameField = new JTextField(20);
		this.add(nameField);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nameLabel, -20, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nameLabel, -22, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, nameField, 0, SpringLayout.NORTH, nameLabel);
		layout.putConstraint(SpringLayout.WEST, nameField, 6, SpringLayout.EAST, nameLabel);
		
		// CREDIT CARD
		creditLabel = new JLabel("Numero de tarjeta: ");
		creditLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		this.add(creditLabel);
		creditField = new JTextField(20);
		this.add(creditField);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, creditLabel, -20, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, creditLabel, 5, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, creditField, 0, SpringLayout.NORTH, creditLabel);
		layout.putConstraint(SpringLayout.WEST, creditField, 6, SpringLayout.EAST, creditLabel);
	
		// BUTTON
		button = new StandardButton("Finalizar pago", 150, 50);
		this.add(button);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, button, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, button, 70, SpringLayout.VERTICAL_CENTER, this);
		this.setPreferredSize(new Dimension(400, 150));
	}
	
	public void setControlador(ActionListener controlador) {
		this.button.addActionListener(controlador);
	}
	
	public JTextField getFullName() {
		return nameField;
	}

	public JTextField getCreditCard() {
		return creditField;
	}
	
	public void updateSideBar() {
		this.remove(sideBar);
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
	}
}
