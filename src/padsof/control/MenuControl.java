package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import padsof.swing.MainFrame;
import padsof.swing.MenuPanel;
import padsof.system.System;

/**
 * Controlador de Menu Panel
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
public class MenuControl implements ActionListener {

	/**
	 * Panel Menu
	 */
	private MenuPanel panel;

	/**
	 * Constructor de clase
	 * 
	 * @param panel Menu
	 */
	public MenuControl(MenuPanel panel) {
		this.panel = panel;
	}

	/**
	 * Accionador de eventos del menu
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(panel.getHome())) {
			MainFrame.getInstance().mostrarMainPanel();
		} else if (System.getInstance().getLoggedUser() != null) {
			if (event.getSource().equals(panel.getLists()))
				MainFrame.getInstance().mostrarMyPlaylists();
			if (event.getSource().equals(panel.getAlbums()))
				MainFrame.getInstance().mostrarMyAlbums();
			if (event.getSource().equals(panel.getAudios()))
				MainFrame.getInstance().mostrarMySongs();
			if (event.getSource().equals(panel.getFollowing()))
				MainFrame.getInstance().mostrarFollowing();
			if (event.getSource().equals(panel.getNotif()))
				MainFrame.getInstance().mostrarNotifications();
		}
	}

}
