package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import padsof.Status;
import padsof.playable.Song;
import padsof.swing.AddAudioPanel;
import padsof.system.System;

public class AddAudioControl implements ActionListener {

	private AddAudioPanel panel;

	public AddAudioControl(AddAudioPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource().equals(panel.getPath())) {
			JFileChooser j = new JFileChooser();
			int r = j.showOpenDialog(null);
			
			if(r == JFileChooser.APPROVE_OPTION) {
				String test = j.getSelectedFile().getAbsolutePath();
				panel.getPathField().setText(test);
			}
		}
		else {	
			String title = this.panel.getSongTitle().getText();
			String path = this.panel.getPathField().getText();
			
			if (title.isEmpty() || path.isEmpty()) {
				JOptionPane.showMessageDialog(this.panel, "Es obligatorio rellenar ambos campos", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (System.getInstance().addSong(new Song(title, path)) == Status.OK) {
				JOptionPane.showMessageDialog(this.panel, "Se anadio la cancion", "Congrats", JOptionPane.INFORMATION_MESSAGE);
				this.panel.getSongTitle().setText("");
				this.panel.getPath().setText("");
			}
			else {
				JOptionPane.showMessageDialog(this.panel, "No se ha podido subir la cancion, comprueba que no exista ya", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			this.panel.getSongTitle().setText("");
			this.panel.getPathField().setText("");
		}
	}

}
