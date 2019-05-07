package padsof.swing;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import padsof.playable.Song;
import padsof.system.System;

@SuppressWarnings("serial")
public class AboutPanel extends JPanel {

	private JLabel title;
	private SideBarPanel sideBar;
	private JTable table;

	public AboutPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		// PANEL DESCRIPTION / TITLE
		title = new JLabel("Sobre la aplicacion");
		title.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 22));
		this.add(title);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this);
		
		// TODO: REMOVE MY FRANKENSTEIN TESTS
		System.getInstance().login("admin", "admin");
		System.getInstance().addSong(new Song("Africa", "music/africa.mp3"));
		ArrayList<Song> songList = System.getInstance().getSongList();
		//java.lang.System.out.println("listaAbout" + songList);
		Object[][] filas = new Object[songList.size()][3];
		int i = 0;
		for (Song s : songList) {
			filas[i][0] = s.getTitle();
			filas[i][1] = s.getAuthor().getName();
			filas[i][2] = s.getLength().intValue();
			i++;
		}
		String[] titulos = {"Titulo", "Autor", "Duracion"};
		/* Tabla de creacion de playlists dinamica */
		AbstractTableModel tablaDeMierda = new PlaylistCreationTable(titulos, filas);
		table = new JTable(tablaDeMierda);
		this.add(table);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, table, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, table, 65, SpringLayout.NORTH, this);
	}
	
	public void updateSideBar() {
		this.remove(sideBar);
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
	}
	
	public void updateTable() {
		// YA TAL
	}
}
