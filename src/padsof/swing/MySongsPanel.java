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

/**
 * Panel para mostrar las canciones del usuario
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
@SuppressWarnings("serial")
public class MySongsPanel extends JPanel {

	private SideBarPanel sideBar;
	
	private ScrollableJTablePlayable acceptedTable;
	
	private ScrollableJTablePlayable pendingTable;
	
	private StandardButton deleteButton;
	
	private StandardButton playButton;
	
	private StandardButton deletePendButton;
	
	private StandardButton addToAlbumButton;
	
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
		
		deleteButton = new StandardButton("Borrar", 90, 25);
		playButton = new StandardButton("Play", 70, 25);
		addToAlbumButton = new StandardButton("Anadir a album", 160, 25);
		deletePendButton = new StandardButton("Borrar", 90, 25);
		this.add(deleteButton);
		this.add(playButton);
		this.add(addToAlbumButton);
		this.add(deletePendButton);
		
		acceptedTable = new ScrollableJTablePlayable(500, 120);
		pendingTable = new ScrollableJTablePlayable(500, 120);
		this.add(acceptedTable);
		this.add(pendingTable);
		
		layout.putConstraint(SpringLayout.WEST, acceptedTable, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, acceptedTable, 80, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, deleteButton, 0, SpringLayout.WEST, acceptedTable);
		layout.putConstraint(SpringLayout.NORTH, deleteButton, 5, SpringLayout.SOUTH, acceptedTable);
		layout.putConstraint(SpringLayout.WEST, playButton, 20, SpringLayout.EAST, deleteButton);
		layout.putConstraint(SpringLayout.NORTH, playButton, 5, SpringLayout.SOUTH, acceptedTable);
		layout.putConstraint(SpringLayout.WEST, addToAlbumButton, 20, SpringLayout.EAST, playButton);
		layout.putConstraint(SpringLayout.NORTH, addToAlbumButton, 5, SpringLayout.SOUTH, acceptedTable);
		
		layout.putConstraint(SpringLayout.WEST, pendingTable, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, pendingTable, 20, SpringLayout.SOUTH, playButton);
		layout.putConstraint(SpringLayout.WEST, deletePendButton, 0, SpringLayout.WEST, pendingTable);
		layout.putConstraint(SpringLayout.NORTH, deletePendButton, 5, SpringLayout.SOUTH, pendingTable);
		
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
		this.deleteButton.addActionListener(controlador);
		this.playButton.addActionListener(controlador);
		this.deletePendButton.addActionListener(controlador);
		this.addToAlbumButton.addActionListener(controlador);
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
		layout.putConstraint(SpringLayout.NORTH, pendingTable, 20, SpringLayout.SOUTH, playButton);
	}

	public ScrollableJTablePlayable getAcceptedTable() {
		return acceptedTable;
	}

	public ScrollableJTablePlayable getPendingTable() {
		return pendingTable;
	}

	public StandardButton getDeleteButton() {
		return deleteButton;
	}

	public StandardButton getPlayButton() {
		return playButton;
	}

	public StandardButton getDeletePendButton() {
		return deletePendButton;
	}

	public StandardButton getAddToAlbumButton() {
		return addToAlbumButton;
	}

}
