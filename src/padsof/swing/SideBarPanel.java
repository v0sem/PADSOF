package padsof.swing;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.sistem.Sistem;

@SuppressWarnings("serial")
public class SideBarPanel extends JPanel {

	private JPanel userInfo;
	private JPanel menu;
	
	public SideBarPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		//TODO: la wea esa de conprobar que ay login y mierdas
		Sistem.getInstance().login("admin", "admin");
		userInfo = new UserInfoLoggedPanel();
		menu = new MenuPanel();
		
		this.add(userInfo);
		this.add(menu);
		
		layout.putConstraint(SpringLayout.WEST, userInfo, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, userInfo, 10, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, menu, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, menu, 75, SpringLayout.NORTH, this);
		
		this.setPreferredSize(new Dimension(200, 450));
	}
}
