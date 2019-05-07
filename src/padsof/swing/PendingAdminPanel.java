package padsof.swing;

import javax.swing.JPanel;

import padsof.swing.items.StandardButton;

public class PendingAdminPanel extends JPanel{
	
	private StandardButton approveSong;
	private StandardButton setExplicit;
	private StandardButton rejectSong;
	private StandardButton approveReport;
	private StandardButton punish;
	
	private ScrollableJTable canciones;
	private ScrollableJTable reportes;
	
	public PendingAdminPanel() {
		
		approveSong = new StandardButton("Aprobar", 100, 30);
		setExplicit = new StandardButton("+18", 100, 30);
		rejectSong = new StandardButton("Rechazar", 100, 30);
		approveReport = new StandardButton("Reportar", 100, 30);
		punish = new StandardButton("Rechazar", 100, 30);
		
		this.add(approveSong);
		this.add(setExplicit);
		this.add(rejectSong);
		this.add(approveReport);
		this.add(punish);
	}
}
