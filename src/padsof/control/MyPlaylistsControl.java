package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import padsof.playable.Playlist;
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
			System.getInstance().deletePlaylist((Playlist) panel.getTable().getSelected());
		}
		if (event.getSource().equals(panel.getCreateButton())) {
			// MOSTRAR PANEL DE CREAR
		}
	}

}
