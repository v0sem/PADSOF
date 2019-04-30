package padsof.swing;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.system.System;

@SuppressWarnings("serial")
public class SideBarPanel extends JPanel {

	private JPanel userInfo;
	private JPanel menu;
	private JPanel userActions;
	
	public SideBarPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		//TODO: la wea esa de conprobar que ay login y mierdas
		if(System.getInstance().getLoggedUser()==null)
			userInfo = new UserInfoAnonPanel();
		else
			userInfo = new UserInfoLoggedPanel();
		menu = new MenuPanel();
		if(System.getInstance().getLoggedUser()==null)
			userActions = new UserActionsAnonPanel();
		else if(System.getInstance().adminIsLogged())
			userActions = new UserActionsAdminPanel();
		else
			userActions = new UserActionsLoggedPanel();
		
		this.add(userInfo);
		this.add(menu);
		this.add(userActions);
		
		layout.putConstraint(SpringLayout.WEST, userInfo, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, userInfo, 5, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, menu, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, menu, 70, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, userActions, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, userActions, 315, SpringLayout.NORTH, this);
		
		this.setPreferredSize(new Dimension(200, 450));
	}
}
