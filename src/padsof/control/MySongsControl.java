package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import padsof.playable.Song;
import padsof.swing.MainFrame;
import padsof.swing.MySongsPanel;
import padsof.system.System;

public class MySongsControl implements ActionListener {

	private MySongsPanel panel;

	public MySongsControl(MySongsPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(panel.getPlayButton())) {
			panel.getAcceptedTable().getSelected().play();
		}
		if (event.getSource().equals(panel.getDeleteButton())) {
			if(panel.getAcceptedTable().getSelected().getClass() == Song.class) {
				System.getInstance().deleteSong((Song) panel.getAcceptedTable().getSelected());
				MainFrame.getInstance().mostrarMySongs();
			}
		}
		if (event.getSource().equals(panel.getDeletePendButton())) {
			if(panel.getPendingTable().getSelected().getClass() == Song.class) {
				System.getInstance().deleteSong((Song) panel.getPendingTable().getSelected());
				MainFrame.getInstance().mostrarMySongs();
			}
		}
	}

}
