package padsof.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.playable.Album;
import padsof.swing.items.StandardButton;
import padsof.system.System;

@SuppressWarnings("serial")
public class MyAlbumsPanel extends JPanel {

	private SideBarPanel sideBar;
	
	private ScrollableJTablePlayable table;
	
	private StandardButton deleteButton;
	
	private StandardButton playButton;
	
	private StandardButton createButton;

	private JLabel title;
	
	private SpringLayout layout;
	
	// TODO: ADD SONG TO SELECTED PLAYLIST BUTTON. NEW PLAYLIST BUTTON
	public MyAlbumsPanel() {
		layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		// PANEL DESCRIPTION / TITLE
		title = new JLabel("Mis albumes");
		title.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 22));
		this.add(title);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this);
		
		deleteButton = new StandardButton("Eliminar", 90, 25);
		playButton = new StandardButton("Play", 70, 25);
		createButton = new StandardButton("Nuevo Album", 90, 25);
		this.add(deleteButton);
		this.add(playButton);
		this.add(createButton);
		
		table = new ScrollableJTablePlayable(500, 290);
		this.add(table);
		
		layout.putConstraint(SpringLayout.WEST, table, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, table, 80, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, deleteButton, 0, SpringLayout.WEST, table);
		layout.putConstraint(SpringLayout.NORTH, deleteButton, 5, SpringLayout.SOUTH, table);
		layout.putConstraint(SpringLayout.WEST, playButton, 20, SpringLayout.EAST, deleteButton);
		layout.putConstraint(SpringLayout.NORTH, playButton, 5, SpringLayout.SOUTH, table);
		layout.putConstraint(SpringLayout.WEST, createButton, 20, SpringLayout.EAST, playButton);
		layout.putConstraint(SpringLayout.NORTH, createButton, 5, SpringLayout.SOUTH, table);
		
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
		this.createButton.addActionListener(controlador);
	}
	
	public void updateTables(){
		this.remove(table);
		table.resetTable();
		List<Album> list = new ArrayList<>();
		for (Album a : System.getInstance().getAlbumList()) {
			if (a.getAuthor() == System.getInstance().getLoggedUser()) 
				list.add(a);
		}
		table.insertMultiple(list);
		this.add(table);
		
		layout.putConstraint(SpringLayout.WEST, table, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, table, 80, SpringLayout.NORTH, this);
	}
}
