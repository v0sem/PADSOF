package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import padsof.playable.Album;
import padsof.playable.Song;
import padsof.swing.MainFrame;
import padsof.swing.MySongsPanel;
import padsof.system.System;
import padsof.user.User;

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
		if (event.getSource().equals(panel.getAddToAlbumButton())) {
			Boolean found = false;
			String title = JOptionPane.showInputDialog("¿A que album la quieres anadir?");
			if (title != null) {
				if (title.isEmpty()) {
					JOptionPane.showMessageDialog(this.panel, "Es obligatorio rellenar el campo", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					User author = System.getInstance().getLoggedUser();
					List<Album> al = System.getInstance().getAlbumList();
					for (Album a : al) {
						if (a.getTitle().equals(title) && a.getAuthor().equals(author)) {
							found = true;
							// Meter objeto seleccionado si es una cancion
							if(panel.getAcceptedTable().getSelected().getClass().equals(Song.class))
								a.addSong((Song) panel.getAcceptedTable().getSelected());
							break;
						}
					}
				}
				
				if (!found) {
					JOptionPane.showMessageDialog(this.panel, "Ese album no existe", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}
