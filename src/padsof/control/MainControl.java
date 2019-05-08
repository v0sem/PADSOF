package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import padsof.playable.Album;
import padsof.playable.PlayableObject;
import padsof.playable.Playlist;
import padsof.playable.Song;
import padsof.swing.MainFrame;
import padsof.swing.MainPanel;
import padsof.system.System;
import padsof.user.User;

public class MainControl implements ActionListener {

	private MainPanel panel;

	public MainControl(MainPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		java.lang.System.out.println("NEPEEE00");
		if (event.getSource().equals(panel.getPlay()))
		{ // PLAY
			PlayableObject p = panel.getTablita().getSelected();
			p.play();
		} else if(event.getSource().equals(panel.getReport())) {
			PlayableObject p = panel.getTablita().getSelected();
			if(p.getClass() == Album.class) {
				JOptionPane.showMessageDialog(this.panel, "No puedes reportar un album");
				return;
			}
			((Song) p).report();
			panel.updateTables();
			JOptionPane.showMessageDialog(this.panel, "Cancion reportada");
		} else if(event.getSource().equals(panel.getFollow())) {
			PlayableObject p = panel.getTablita().getSelected();
			User u = System.getInstance().getLoggedUser();
			if(u == null) {
				JOptionPane.showMessageDialog(this.panel, "No estas loggeado");
				return;
			}
			u.follow(p.getAuthor());
			JOptionPane.showMessageDialog(this.panel, "Siguiendo");
		} else if(event.getSource().equals(panel.getComment())) {
			MainFrame.getInstance().mostrarMainPanel(); //TODO:Comment panel
		} else if(event.getSource().equals(panel.getSearchBar().getSearchButton())) {
			List<PlayableObject> list = System.getInstance().search(
					panel.getSearchBar().getSearchField().getText()
					, panel.getSearchBar().getAudio().isSelected(), 
					panel.getSearchBar().getAuthor().isSelected()
					, panel.getSearchBar().getAlbum().isSelected());
			
			panel.updateTables(list);
		} else if (event.getSource().equals(panel.getAddPlaylist())) {
			Boolean found = false;
			String title = JOptionPane.showInputDialog("Titulo de la playlist:");
			if (title != null) {
				if (title.isEmpty()) {
					JOptionPane.showMessageDialog(this.panel, "Es obligatorio rellenar el campo", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					List<Playlist> pl = System.getInstance().getPlaylistList();
					for (Playlist p : pl) {
						if (p.getTitle() == title) {
							// Meter objeto seleccionado
							p.addPlayableObject(panel.getTablita().getSelected());
							// Mostrar la otra mierda
							MainFrame.getInstance().mostrarMyPlaylists();
							found = true;
						}
					}
				}
				
				if (!found) {
					JOptionPane.showMessageDialog(this.panel, "Esa playlist no existe", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
