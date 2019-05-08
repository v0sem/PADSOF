package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import padsof.swing.MainFrame;
import padsof.swing.PlaylistPanel;

public class PlaylistControl implements ActionListener {

	private PlaylistPanel panel;

	public PlaylistControl(PlaylistPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(panel.getBackButton())) {
			MainFrame.getInstance().mostrarMyPlaylists();
		}
	}

}
