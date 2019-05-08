package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import padsof.swing.MainFrame;
import padsof.swing.UserActionsPanel;
import padsof.system.System;
import padsof.user.UserType;

/**
 * Controlador de eventos de User Actions
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
public class UserActionsControl implements ActionListener {

	/**
	 * Panel User Actions
	 */
	private UserActionsPanel panel;
	
	/**
	 * Constructor de Clase
	 * 
	 * @param panel User Actions
	 */
	public UserActionsControl(UserActionsPanel panel) {
		this.panel = panel;
	}

	/**
	 * Accionador de eventos de User Actions
	 */
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
