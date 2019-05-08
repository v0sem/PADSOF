package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import padsof.swing.FollowingPanel;
import padsof.swing.MainFrame;
import padsof.system.System;
import padsof.user.User;

/**
 * Controlador de Following Panel
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
public class FollowingControl implements ActionListener {

	/**
	 * Panel Following
	 */
	private FollowingPanel panel;

	/**
	 * Constructor de Clase
	 * 
	 * @param panel Following Panel
	 */
	public FollowingControl(FollowingPanel panel) {
		this.panel = panel;
	}

	/**
	 * Accionador de eventos de Following
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		User u = System.getInstance().getLoggedUser();
		
		if (u != null)
			if (panel.getTable().getSelected() != null)
				u.unfollow(panel.getTable().getSelected());

		panel.updateTable();
		MainFrame.getInstance().mostrarFollowing();
	}

}
