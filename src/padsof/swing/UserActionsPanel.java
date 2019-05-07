package padsof.swing;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.swing.items.StandardButton;

@SuppressWarnings("serial")
public class UserActionsPanel extends JPanel {
	private StandardButton button1;
	private StandardButton button2;
	SpringLayout layout;
	
	public UserActionsPanel() {
		this.layout = new SpringLayout();
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(200, 125));
	}

	public void addButton1(String buttonText) {
		this.button1 = new StandardButton(buttonText, 200, 42);
		this.add(button1);
		this.layout.putConstraint(SpringLayout.WEST, button1, 0, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, button1, 10, SpringLayout.NORTH, this);
	}
	
	public void addButton2(String buttonText) {
		this.button2 = new StandardButton(buttonText, 200, 42);
		this.add(button2);
		this.layout.putConstraint(SpringLayout.WEST, button2, 0, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, button2, 50, SpringLayout.NORTH, this);
	}
	
	public StandardButton getButton1() {
		return button1;
	}
	
	public StandardButton getButton2() {
		return button2;
	}
	
	public void setControlador(ActionListener controlador){
		button1.addActionListener(controlador);
		button2.addActionListener(controlador);
	}
}