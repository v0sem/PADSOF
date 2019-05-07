package padsof.swing;

import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.control.MainControl;
import padsof.playable.Album;
import padsof.playable.PlayableObject;
import padsof.playable.Song;
import padsof.playable.SongState;
import padsof.swing.items.StandardButton;
import padsof.system.System;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private SpringLayout layout;
	
	private SearchBarPanel searchBar;
	private SideBarPanel sideBar;
	private StandardButton play;
	private StandardButton report;
	private StandardButton follow;
	private StandardButton comment;
	private ScrollableJTablePlayable tablita;

	static int buttonWidth = 127;
	static int buttonHeight = 42;
	static int buttonSep = 20;
	
	public MainPanel() {
		layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		this.searchBar = new SearchBarPanel();
		this.add(searchBar);
		
		tablita = new ScrollableJTablePlayable(550, 278);
		this.add(tablita);
		
		play = new StandardButton("Play", buttonWidth, buttonHeight);		
		this.add(play);
		
		report = new StandardButton("Report", buttonWidth, buttonHeight);		
		this.add(report);
		
		follow = new StandardButton("Follow", buttonWidth, buttonHeight);		
		this.add(follow);
		
		comment = new StandardButton("Comment", buttonWidth, buttonHeight);		
		this.add(comment);
		
		updateTables();
		
		layout.putConstraint(SpringLayout.EAST, searchBar, 10, SpringLayout.EAST, this);
		
		layout.putConstraint(SpringLayout.EAST, play, -buttonSep * 3 - buttonWidth * 3, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, play, 0, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.EAST, report, -buttonSep * 2 - buttonWidth * 2, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, report, 0, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.EAST, follow, -buttonSep - buttonWidth, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, follow, 0, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.EAST, comment, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, comment, 0, SpringLayout.SOUTH, this);
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
		layout.putConstraint(SpringLayout.SOUTH, tablita, -buttonHeight - buttonSep, SpringLayout.SOUTH, this);
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

	public void setControlador(ActionListener controlador){
		this.play.addActionListener(controlador);
		this.report.addActionListener(controlador);
		this.follow.addActionListener(controlador);
		this.comment.addActionListener(controlador);
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
