package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import padsof.playable.Album;
import padsof.swing.MainFrame;
import padsof.swing.MyAlbumsPanel;
import padsof.swing.items.AlbumCreationPanel;
import padsof.system.System;

/**
 * Controlador de eventos de My Albums
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
public class MyAlbumsControl implements ActionListener {

	/**
	 * Panel My Albums
	 */
	private MyAlbumsPanel panel;

	/**
	 * Constructor de clase
	 * 
	 * @param panel My Albums
	 */
	public MyAlbumsControl(MyAlbumsPanel panel) {
		this.panel = panel;
	}

	/**
	 * Accionador de eventos de My Album Panel
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(panel.getPlayButton())) {
			panel.getTable().getSelected().play();
		}
		else if (event.getSource().equals(panel.getDeleteButton())) {
			if(panel.getTable().getSelected().getClass() == Album.class) {
				System.getInstance().deleteAlbum((Album) panel.getTable().getSelected());
				MainFrame.getInstance().mostrarMyAlbums();
			}
		}
		else if (event.getSource().equals(panel.getCreateButton())) {
			AlbumCreationPanel popup = new AlbumCreationPanel();
			int option = JOptionPane.showConfirmDialog(null, popup, "Nuevo album", JOptionPane.OK_CANCEL_OPTION);
			String title = popup.getTitulo().getText();
			String ano = popup.getAno().getText();
			if (option == JOptionPane.OK_OPTION) {
				if (title.isEmpty() || ano.isEmpty()) {
					JOptionPane.showMessageDialog(this.panel, "Es obligatorio rellenar los campos", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					int realAno = Integer.parseInt(ano);
					System.getInstance().addAlbum(new Album(title, realAno));
					MainFrame.getInstance().mostrarMyAlbums();
				}	
			}
		}
		else if (event.getSource().equals(panel.getShowButton())) {
			Album p = (Album) panel.getTable().getSelected();
			MainFrame.getInstance().mostrarAlbum(p);
		}
	}
}
