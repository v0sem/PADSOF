package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import padsof.playable.Album;
import padsof.swing.MyAlbumsPanel;
import padsof.system.System;

public class MyAlbumsControl implements ActionListener {

	private MyAlbumsPanel panel;

	public MyAlbumsControl(MyAlbumsPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(panel.getPlayButton())) {
			panel.getTable().getSelected().play();
		}
		if (event.getSource().equals(panel.getDeleteButton())) {
			if(panel.getTable().getSelected().getClass() == Album.class)
				System.getInstance().deleteAlbum((Album) panel.getTable().getSelected());
		}
		if (event.getSource().equals(panel.getCreateButton())) {
			// MOSTRAR PANEL DE CREAR
		}
	}

}
