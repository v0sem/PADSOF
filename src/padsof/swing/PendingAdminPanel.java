package padsof.swing;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.swing.items.StandardButton;
import padsof.system.System;
import padsof.interactions.Report;
import padsof.playable.Song;
import padsof.playable.SongState;

@SuppressWarnings("serial")
public class PendingAdminPanel extends JPanel{
	
	private SpringLayout layout;
	
	private StandardButton approveSong;
	private StandardButton setExplicit;
	private StandardButton rejectSong;
	private StandardButton play;
	private StandardButton approveReport;
	private StandardButton punish;
	
	private ScrollableJTablePlayable canciones;
	private ScrollableJTableReport reportes;
	
	private SideBarPanel sideBar;
	
	public PendingAdminPanel() {
		layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		approveSong = new StandardButton("Aprobar", 90, 25);
		setExplicit = new StandardButton("+18", 60, 25);
		rejectSong = new StandardButton("Rechazar", 100, 25);
		play = new StandardButton("Play", 70, 25);
		approveReport = new StandardButton("Reportar", 100, 25);
		punish = new StandardButton("Rechazar", 100, 25);
		
		this.add(approveSong);
		this.add(setExplicit);
		this.add(rejectSong);
		this.add(play);
		this.add(approveReport);
		this.add(punish);
		
		canciones = new ScrollableJTablePlayable(500, 150);
		reportes = new ScrollableJTableReport(500, 150);
		
		updateTables();
		
		layout.putConstraint(SpringLayout.WEST, approveSong, 0, SpringLayout.WEST, canciones);
		layout.putConstraint(SpringLayout.NORTH, approveSong, 5, SpringLayout.SOUTH, canciones);
		layout.putConstraint(SpringLayout.WEST, setExplicit, 20, SpringLayout.EAST, approveSong);
		layout.putConstraint(SpringLayout.NORTH, setExplicit, 5, SpringLayout.SOUTH, canciones);
		layout.putConstraint(SpringLayout.WEST, rejectSong, 20, SpringLayout.EAST, setExplicit);
		layout.putConstraint(SpringLayout.NORTH, rejectSong, 5, SpringLayout.SOUTH, canciones);
		layout.putConstraint(SpringLayout.WEST, play, 20, SpringLayout.EAST, rejectSong);
		layout.putConstraint(SpringLayout.NORTH, play, 5, SpringLayout.SOUTH, canciones);
		
		layout.putConstraint(SpringLayout.WEST, approveReport, 0, SpringLayout.WEST, reportes);
		layout.putConstraint(SpringLayout.NORTH, approveReport, 5, SpringLayout.SOUTH, reportes);
		layout.putConstraint(SpringLayout.WEST, punish, 20, SpringLayout.EAST, approveReport);
		layout.putConstraint(SpringLayout.NORTH, punish, 5, SpringLayout.SOUTH, reportes);
		
		this.setPreferredSize(new Dimension(800, 450));
	}
	
	public void updateTables() {
		if(reportes != null)
			this.remove(reportes);
		if(canciones != null)
			this.remove(canciones);
		
		canciones.resetTable();
		reportes.resetTable();
		
		List<Song> pending = new ArrayList<>();
		for(Song s: System.getInstance().getSongList()) {
			if(s.getState() == SongState.REVISION_PENDING) {
				pending.add(s);
			}
		}
		canciones.insertMultiple(pending);
		
		this.add(canciones);
		
		layout.putConstraint(SpringLayout.WEST, canciones, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, canciones, 10, SpringLayout.NORTH, this);
		
		List<Report> reportList = System.getInstance().getReportList();
		reportes.insertMultiple(reportList);
		
		this.add(reportes);
		
		layout.putConstraint(SpringLayout.WEST, reportes, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, reportes, 0, SpringLayout.VERTICAL_CENTER, this);
	}
	
	public void updateSideBar() {
		this.remove(sideBar);
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
	}
	
	public void setControlador(ActionListener controlador){
		this.approveSong.addActionListener(controlador);
		this.setExplicit.addActionListener(controlador);
		this.rejectSong.addActionListener(controlador);
		this.play.addActionListener(controlador);
		this.approveReport.addActionListener(controlador);
		this.punish.addActionListener(controlador);
	}

	public StandardButton getApproveSong() {
		return approveSong;
	}

	public StandardButton getSetExplicit() {
		return setExplicit;
	}

	public StandardButton getRejectSong() {
		return rejectSong;
	}

	public StandardButton getPlay() {
		return play;
	}

	public StandardButton getApproveReport() {
		return approveReport;
	}

	public StandardButton getPunish() {
		return punish;
	}

	public ScrollableJTablePlayable getCanciones() {
		return canciones;
	}

	public ScrollableJTableReport getReportes() {
		return reportes;
	}
}
