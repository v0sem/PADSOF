package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import padsof.playable.Playlist;
import padsof.swing.MainFrame;
import padsof.swing.MyPlaylistsPanel;
import padsof.system.System;

public class MyPlaylistsControl implements ActionListener {

	private MyPlaylistsPanel panel;

	public MyPlaylistsControl(MyPlaylistsPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(panel.getPlayButton())) {
			panel.getTable().getSelected().play();
		}
		if (event.getSource().equals(panel.getDeleteButton())) {
			if(panel.getTable().getSelected().getClass() == Playlist.class) {
				System.getInstance().deletePlaylist((Playlist) panel.getTable().getSelected());
				MainFrame.getInstance().mostrarMyPlaylists();
			}
		}
		if (event.getSource().equals(panel.getCreateButton())) {
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
		}
	}

}
