package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import padsof.swing.MainFrame;
import padsof.swing.UserActionsPanel;
import padsof.system.System;
import padsof.user.UserType;

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
			else MainFrame.getInstance().mostrarAddAudio(); //LOGGED
		}
		if (event.getSource().equals(panel.getButton2())) {
			if (System.getInstance().getLoggedUser() == null)
				MainFrame.getInstance().mostrarRegister(); //ANON
			else if (System.getInstance().adminIsLogged()) {
				MainFrame.getInstance().updateSideBar();
				MainFrame.getInstance().mostrarAdmin(); //ADMIN
			}
			else if(System.getInstance().getLoggedUser().getUserType().equals(UserType.STANDARD))
				MainFrame.getInstance().mostrarGoPremium(); //STANDARD
		}
	}

}
