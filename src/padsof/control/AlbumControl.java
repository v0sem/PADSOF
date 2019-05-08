package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import padsof.playable.Playlist;
import padsof.swing.AlbumPanel;
import padsof.swing.MainFrame;
import padsof.swing.MyPlaylistsPanel;
import padsof.swing.PlaylistPanel;
import padsof.system.System;

public class AlbumControl implements ActionListener {

	private AlbumPanel panel;

	public AlbumControl(AlbumPanel albumpanel) {
		this.panel = albumpanel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(panel.getBackButton())) {
			MainFrame.getInstance().mostrarMyAlbums();
		}
	}

}
