package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import padsof.interactions.Report;
import padsof.playable.Song;
import padsof.playable.SongState;
import padsof.swing.PendingAdminPanel;


public class PendingControl implements ActionListener{

	private PendingAdminPanel panel;

	public PendingControl(PendingAdminPanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(panel.getApproveSong())) {
			Song s = (Song) panel.getCanciones().getSelected();
			s.setState(SongState.ACCEPTED);
			panel.updateTables();
			JOptionPane.showMessageDialog(this.panel, "Cancion aceptada");
		} else if(event.getSource().equals(panel.getSetExplicit())) {
			Song s = (Song) panel.getCanciones().getSelected();
			s.setExplicit(true);
			panel.updateTables();
			JOptionPane.showMessageDialog(this.panel, "Cancion marcada como explicita");
		} else if(event.getSource().equals(panel.getRejectSong())) {
			Song s = (Song) panel.getCanciones().getSelected();
			panel.updateTables();
			s.setState(SongState.REJECTED);
			JOptionPane.showMessageDialog(this.panel, "Cancion rechazada");
		} else if(event.getSource().equals(panel.getPlay())) {
			Song s = (Song) panel.getCanciones().getSelected();
			s.play();
		} else if(event.getSource().equals(panel.getApproveReport())) {
			Report r = panel.getReportes().getSelected();
			r.accept();
			panel.updateTables();
			JOptionPane.showMessageDialog(this.panel, "Reporte aceptado");
		} else if(event.getSource().equals(panel.getPunish())) {
			Report r = panel.getReportes().getSelected();
			r.reject();
			panel.updateTables();
			JOptionPane.showMessageDialog(this.panel, "Reporte rechazado y usuario bloqueado");
		}
	}

}
