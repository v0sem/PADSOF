package padsof.swing;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.playable.Album;
import padsof.playable.PlayableObject;
import padsof.playable.Song;
import padsof.playable.SongState;
import padsof.swing.items.StandardButton;
import padsof.system.System;

/**
 * Panel principal
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private SpringLayout layout;
	
	private SearchBarPanel searchBar;
	private SideBarPanel sideBar;
	private StandardButton play;
	private StandardButton report;
	private StandardButton follow;
	private StandardButton comment;
	private StandardButton addPlaylist;
	private ScrollableJTablePlayable tablita;

	static int buttonWidth = 106;
	static int buttonHeight = 25;
	static int buttonSep = 10;

	/**
	 * Constructor de MainPanel
	 */
	public MainPanel() {		
		layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		this.searchBar = new SearchBarPanel();
		this.add(searchBar);
		
		tablita = new ScrollableJTablePlayable(550, 294);
		this.add(tablita);
		
		play = new StandardButton("Play", buttonWidth, buttonHeight);		
		this.add(play);
		
		report = new StandardButton("Reportar", buttonWidth, buttonHeight);		
		this.add(report);
		
		follow = new StandardButton("Seguir", buttonWidth, buttonHeight);		
		this.add(follow);
		
		comment = new StandardButton("Coment.", buttonWidth, buttonHeight);		
		this.add(comment);
		
		addPlaylist = new StandardButton("Anadir a", buttonWidth, buttonHeight);		
		this.add(addPlaylist);
		
		updateTables();
		
		layout.putConstraint(SpringLayout.EAST, searchBar, 10, SpringLayout.EAST, this);
		
		layout.putConstraint(SpringLayout.EAST, play, -buttonSep * 4 - buttonWidth * 4, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, play, -10, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.EAST, report, -buttonSep * 3 - buttonWidth * 3, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, report, -10, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.EAST, follow, -buttonSep * 2 - buttonWidth * 2, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, follow, -10, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.EAST, comment, -buttonSep - buttonWidth, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, comment, -10, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.EAST, addPlaylist, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, addPlaylist, -10, SpringLayout.SOUTH, this);

		this.setPreferredSize(new Dimension(800, 450));
	}

	/**
	 * Updater de las tablas sin parametros
	 */
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
		layout.putConstraint(SpringLayout.SOUTH, tablita, -buttonHeight - 2 * buttonSep, SpringLayout.SOUTH, this);
	}

	/**
	 * Updater de las tablas con una lista para actualizarlas
	 */
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

	public void setControlador(ActionListener controlador){
		this.play.addActionListener(controlador);
		this.report.addActionListener(controlador);
		this.follow.addActionListener(controlador);
		this.comment.addActionListener(controlador);
		this.addPlaylist.addActionListener(controlador);
		this.searchBar.setControlador(controlador);
	}

	public SearchBarPanel getSearchBar() {
		return searchBar;
	}

	public StandardButton getPlay() {
		return play;
	}

	public StandardButton getReport() {
		return report;
	}

	public StandardButton getFollow() {
		return follow;
	}

	public StandardButton getComment() {
		return comment;
	}

	public StandardButton getAddPlaylist() {
		return addPlaylist;
	}

	public static void setButtonWidth(int buttonWidth) {
		MainPanel.buttonWidth = buttonWidth;
	}

	public static void setButtonHeight(int buttonHeight) {
		MainPanel.buttonHeight = buttonHeight;
	}

	public static void setButtonSep(int buttonSep) {
		MainPanel.buttonSep = buttonSep;
	}
	
	public ScrollableJTablePlayable getTablita() {
		return tablita;
	}
}
