package padsof.swing;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.sistem.Sistem;
import padsof.swing.items.StandardButton;

@SuppressWarnings("serial")
public class UserInfoAnonPanel extends JPanel {
	
	private JLabel anon;
	private JLabel info;
	
	public UserInfoAnonPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		anon  = new JLabel();
		anon.setText("Usuario anónimo");
		//TODO:Poner negro gordo
		
		this.add(anon);
		this.add(info);
		
		layout.putConstraint(SpringLayout.WEST, anon, -100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, anon, 5, SpringLayout.NORTH, this);

		
		layout.putConstraint(SpringLayout.WEST, info, -100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, info, 0, SpringLayout.SOUTH, this);
		
		this.setPreferredSize(new Dimension(200, 55));
	}
}
