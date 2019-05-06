package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import padsof.swing.MainFrame;
import padsof.swing.UserActionsPanel;
import padsof.system.System;

public class UserActionsControl implements ActionListener {

	private UserActionsPanel panel;

	public UserActionsControl(UserActionsPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(panel.getButton1())) {
			if (System.getInstance().getLoggedUser() == null)
				MainFrame.getInstance().mostrarLogin(); //ANON
			else if (System.getInstance().adminIsLogged())
				MainFrame.getInstance().mostrarLogin(); //ADMIN
			else
				MainFrame.getInstance().mostrarLogin(); //STANDARD OR PREMIUM
		}
		if (event.getSource().equals(panel.getButton2())) {
			if (System.getInstance().getLoggedUser() == null)
				MainFrame.getInstance().mostrarRegister(); //ANON
			else if (System.getInstance().adminIsLogged())
				MainFrame.getInstance().mostrarRegister(); //ADMIN
			else
				MainFrame.getInstance().mostrarRegister(); //STANDARD OR PREMIUM
		}
	}

}
