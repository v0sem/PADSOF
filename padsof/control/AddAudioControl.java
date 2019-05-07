package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import padsof.swing.AddAudioPanel;
import padsof.Status;
import padsof.playable.Song;
import padsof.system.System;

public class AddAudioControl implements ActionListener {

	private AddAudioPanel panel;

	public AddAudioControl(AddAudioPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String title = this.panel.getSongTitle().getText();
		String path = this.panel.getPath().getText();
		
		if (title.isEmpty() || path.isEmpty()) {
			JOptionPane.showMessageDialog(this.panel, "Es obligatorio rellenar ambos campos", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (System.getInstance().addSong(new Song(title, path)) == Status.OK) {
			JOptionPane.showMessageDialog(this.panel, "Se anadio la cancion", "Congrats", JOptionPane.INFORMATION_MESSAGE);
			this.panel.getSongTitle().setText("");
			this.panel.getPath().setText("");
		}
		else {
			JOptionPane.showMessageDialog(this.panel, "No hemos podido validar el pago. Vuelve a intentarlo", "Error", JOptionPane.ERROR_MESSAGE);
			this.panel.getSongTitle().setText("");
			this.panel.getPath().setText("");
		}
	}

}
