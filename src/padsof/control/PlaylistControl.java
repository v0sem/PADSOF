package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import padsof.swing.MainFrame;
import padsof.swing.PlaylistPanel;

/**
 * Controlador de Playlist Panel
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
public class PlaylistControl implements ActionListener {

	/**
	 * Panel Playlist
	 */
	private PlaylistPanel panel;

	/**
	 * Constructor de Clase
	 * 
	 * @param panel Playlist
	 */
	public PlaylistControl(PlaylistPanel panel) {
		this.panel = panel;
	}

	/**
	 * Accionador de eventos de Playlist
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(panel.getBackButton())) {
			MainFrame.getInstance().mostrarMyPlaylists();
		}
	}

}
