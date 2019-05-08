package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import padsof.playable.Playlist;
import padsof.swing.MainFrame;
import padsof.swing.MyPlaylistsPanel;
import padsof.system.System;

/**
 * Controlador de My Playlists
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
public class MyPlaylistsControl implements ActionListener {

	/**
	 * Panel My PlayLists
	 */
	private MyPlaylistsPanel panel;

	/**
	 * Constructor de clase
	 * 
	 * @param panel My Playlists
	 */
	public MyPlaylistsControl(MyPlaylistsPanel panel) {
		this.panel = panel;
	}

	/**
	 * Accionador de eventos de My Playlists
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(panel.getPlayButton())) {
			panel.getTable().getSelected().play();
		} else if (event.getSource().equals(panel.getDeleteButton())) {
			if(panel.getTable().getSelected().getClass() == Playlist.class) {
				System.getInstance().deletePlaylist((Playlist) panel.getTable().getSelected());
				MainFrame.getInstance().mostrarMyPlaylists();
			}
		} else if (event.getSource().equals(panel.getCreateButton())) {
			String title = JOptionPane.showInputDialog("Titulo para la nueva lista:");
			if (title != null) { 
				if (title.isEmpty()) {
					JOptionPane.showMessageDialog(this.panel, "Es obligatorio rellenar el campo", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					System.getInstance().addPlaylist(new Playlist(title));
					MainFrame.getInstance().mostrarMyPlaylists();
				}
			}
		} else if (event.getSource().equals(panel.getShowButton())) {
			Playlist p = (Playlist) panel.getTable().getSelected();
			MainFrame.getInstance().mostrarPlaylist(p);
		}
	}

}
