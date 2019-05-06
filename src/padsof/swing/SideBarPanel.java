package padsof.swing;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.control.MenuControl;
import padsof.control.UserActionsControl;
import padsof.control.UserInfoControl;
import padsof.system.System;

@SuppressWarnings("serial")
public class SideBarPanel extends JPanel {

	private UserInfoPanel userInfo;
	private MenuPanel menu;
	private UserActionsPanel userActions;
	
	public SideBarPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
			
		userActions = new UserActionsPanel();
		userInfo = new UserInfoPanel();
		if(System.getInstance().getLoggedUser() == null) { //ANON
			userActions.addButton1("Iniciar sesion");
			userActions.addButton2("Registrarse");
		} else { //LOGGED
			userActions.addButton1("Anadir un audio");
			if(System.getInstance().adminIsLogged()) userActions.addButton2("Panel de admin.");
			else userActions.addButton2("Obtener premium");
		}
		menu = new MenuPanel();
		
		menu.setControlador(new MenuControl(menu));
		userActions.setControlador(new UserActionsControl(userActions));
		userInfo.setControlador(new UserInfoControl());

		this.add(menu);
		this.add(userActions);
		this.add(userInfo);
		
		layout.putConstraint(SpringLayout.WEST, userInfo, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, userInfo, 5, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, menu, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, menu, 178, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, userActions, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, userActions, 70, SpringLayout.NORTH, this);
		
		this.setPreferredSize(new Dimension(200, 450));
	}
}
