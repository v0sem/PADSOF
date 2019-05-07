package padsof.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.playable.Song;
import padsof.playable.SongState;
import padsof.swing.items.StandardButton;
import padsof.system.System;

@SuppressWarnings("serial")
public class MySongsPanel extends JPanel {

	private SideBarPanel sideBar;
	
	private ScrollableJTablePlayable acceptedTable;
	
	private ScrollableJTablePlayable pendingTable;
	
	private StandardButton delete;
	
	private StandardButton play;
	
	private StandardButton delete2;

	private JLabel title;
	
	private SpringLayout layout;
	
	public MySongsPanel() {
		layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		// PANEL DESCRIPTION / TITLE
		title = new JLabel("Mis canciones");
		title.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 22));
		this.add(title);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this);
		
		delete = new StandardButton("Eliminar", 90, 25);
		play = new StandardButton("Play", 70, 25);
		delete2 = new StandardButton("Eliminar", 90, 25);
		this.add(delete);
		this.add(play);
		this.add(delete2);
		
		acceptedTable = new ScrollableJTablePlayable(500, 120);
		pendingTable = new ScrollableJTablePlayable(500, 120);
		this.add(acceptedTable);
		this.add(pendingTable);
		
		layout.putConstraint(SpringLayout.WEST, acceptedTable, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, acceptedTable, 80, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, delete, 0, SpringLayout.WEST, acceptedTable);
		layout.putConstraint(SpringLayout.NORTH, delete, 5, SpringLayout.SOUTH, acceptedTable);
		layout.putConstraint(SpringLayout.WEST, play, 20, SpringLayout.EAST, delete);
		layout.putConstraint(SpringLayout.NORTH, play, 5, SpringLayout.SOUTH, acceptedTable);
		
		layout.putConstraint(SpringLayout.WEST, pendingTable, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, pendingTable, 20, SpringLayout.SOUTH, play);
		layout.putConstraint(SpringLayout.WEST, delete2, 0, SpringLayout.WEST, pendingTable);
		layout.putConstraint(SpringLayout.NORTH, delete2, 5, SpringLayout.SOUTH, pendingTable);
		
		this.setPreferredSize(new Dimension(800, 450));
	}
	
	public SideBarPanel getSideBar() {
		return this.sideBar;
	}

	public void updateSideBar() {
		this.remove(sideBar);
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
	}
	
	public void setControlador(ActionListener controlador){
		this.delete.addActionListener(controlador);
		this.play.addActionListener(controlador);
		this.delete2.addActionListener(controlador);
	}
	
	public void updateTables(){
		this.remove(acceptedTable);
		this.remove(pendingTable);
		acceptedTable.resetTable();
		pendingTable.resetTable();
		List<Song> acceptedList = new ArrayList<>();
		List<Song> pendingList = new ArrayList<>();
		for (Song s : System.getInstance().getSongList()) {
			if (s.getAuthor() == System.getInstance().getLoggedUser()) {
				if (s.getState() == SongState.ACCEPTED) acceptedList.add(s);
				else pendingList.add(s);
			}
		}
		acceptedTable.insertMultiple(acceptedList);
		pendingTable.insertMultiple(pendingList);
		this.add(acceptedTable);
		this.add(pendingTable);
		
		layout.putConstraint(SpringLayout.WEST, acceptedTable, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, acceptedTable, 80, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, pendingTable, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, pendingTable, 20, SpringLayout.SOUTH, play);
	}
}
