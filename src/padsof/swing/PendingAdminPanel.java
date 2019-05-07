package padsof.swing;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.swing.items.StandardButton;
import padsof.system.System;
import padsof.playable.Song;
import padsof.playable.SongState;

public class PendingAdminPanel extends JPanel{
	
	private StandardButton approveSong;
	private StandardButton setExplicit;
	private StandardButton rejectSong;
	private StandardButton play;
	private StandardButton approveReport;
	private StandardButton punish;
	
	private ScrollableJTable canciones;
	private ScrollableJTable reportes;
	
	private SideBarPanel sideBar;
	
	public PendingAdminPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		approveSong = new StandardButton("Aprobar", 100, 30);
		setExplicit = new StandardButton("+18", 100, 30);
		rejectSong = new StandardButton("Rechazar", 100, 30);
		play = new StandardButton("Play", 100, 30);
		approveReport = new StandardButton("Reportar", 100, 30);
		punish = new StandardButton("Rechazar", 100, 30);
		
		this.add(approveSong);
		this.add(setExplicit);
		this.add(rejectSong);
		this.add(play);
		this.add(approveReport);
		this.add(punish);
		
		canciones = new ScrollableJTable(new String[]{"Titulo", "Autor", "Duracion"} , 500, 150);
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
		
		layout.putConstraint(SpringLayout.WEST, approveSong, 0, SpringLayout.WEST, canciones);
		layout.putConstraint(SpringLayout.NORTH, approveSong, 2, SpringLayout.SOUTH, canciones);
		layout.putConstraint(SpringLayout.WEST, canciones, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, canciones, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, canciones, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, canciones, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, canciones, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, canciones, 10, SpringLayout.NORTH, this);
		
		this.setPreferredSize(new Dimension(800, 450));
	}
	
	public void updateSideBar() {
		this.remove(sideBar);
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
	}
}
