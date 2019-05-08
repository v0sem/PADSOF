package padsof.swing;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.playable.Album;
import padsof.playable.PlayableObject;
import padsof.playable.Song;
import padsof.playable.SongState;
import padsof.system.System;

@SuppressWarnings("serial")
public class PlaylistPanel extends JPanel {

	private SpringLayout layout;
	private JLabel title;
	private SearchBarPanel searchBar;
	private SideBarPanel sideBar;
	private ScrollableJTablePlayable tablita;

	static int buttonWidth = 100;
	static int buttonHeight = 42;
	static int buttonSep = 17;
	
	public PlaylistPanel() {
		title = new JLabel("Lista de canciones de la playlist");
		this.add(title);

		layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		tablita = new ScrollableJTablePlayable();
		this.add(tablita);
		
		updateTables();

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this);
		
		this.setPreferredSize(new Dimension(800, 450));
	}
	
	public void updateTables() {
		if(tablita != null)
			this.remove(tablita);
		
		tablita.resetTable();
		
		List<PlayableObject> input = new ArrayList<>();
		List<PlayableObject> output = new ArrayList<>();
		input.addAll(System.getInstance().getSongList());
		input.addAll(System.getInstance().getAlbumList());
		
		for(PlayableObject po: input) {
			if(po.getClass() == Album.class || ((Song) po).getState() == SongState.ACCEPTED)
				output.add(po);
		}
		
		tablita.insertMultiple(output);
		
		this.add(tablita);
		
		layout.putConstraint(SpringLayout.EAST, tablita, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, tablita, 0, SpringLayout.SOUTH, this);
	}
	
	public void updateTables(List<PlayableObject> list) {
		if(tablita != null)
			this.remove(tablita);
		
		tablita.resetTable();

		tablita.insertMultiple(list);
		
		this.add(tablita);
		
		layout.putConstraint(SpringLayout.EAST, tablita, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, tablita, -buttonHeight - buttonSep, SpringLayout.SOUTH, this);
	}
	
	public SideBarPanel getSideBar() {
		return this.sideBar;
	}

	public void updateSideBar() {
		this.remove(sideBar);
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
	}

	public SearchBarPanel getSearchBar() {
		return searchBar;
	}

	public static void setButtonWidth(int buttonWidth) {
		PlaylistPanel.buttonWidth = buttonWidth;
	}

	public static void setButtonHeight(int buttonHeight) {
		PlaylistPanel.buttonHeight = buttonHeight;
	}

	public static void setButtonSep(int buttonSep) {
		PlaylistPanel.buttonSep = buttonSep;
	}
	
	public ScrollableJTablePlayable getTablita() {
		return tablita;
	}
}
