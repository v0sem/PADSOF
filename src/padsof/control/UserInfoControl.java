package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import padsof.swing.MainFrame;
import padsof.system.System;

/**
 * Controlador de UserInfo
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
public class UserInfoControl implements ActionListener {

	/**
	 * Constructor de clase
	 */
	public UserInfoControl() {}

	/**
	 * Accionador de eventos de UserInfo
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		System.getInstance().stop();
		System.getInstance().logout();
		MainFrame.getInstance().updateSideBar();
		MainFrame.getInstance().mostrarLogin();
	}

}
