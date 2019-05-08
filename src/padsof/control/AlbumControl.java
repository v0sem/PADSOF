package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import padsof.swing.AlbumPanel;
import padsof.swing.MainFrame;

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
