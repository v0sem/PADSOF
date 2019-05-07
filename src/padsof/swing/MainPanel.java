package padsof.swing;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.swing.items.StandardButton;
import padsof.system.System;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private JPanel searchBar;
	private SideBarPanel sideBar;
	
	static int buttonWidth = 127;
	static int buttonHeight = 42;
	static int buttonSep = 20;
	
	public MainPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		this.searchBar = new SearchBarPanel();
		this.add(searchBar);
		
		ScrollableJTablePlayable tablita = new ScrollableJTablePlayable(550, 278);
		this.add(tablita);
		
		JButton play = new StandardButton("Play", buttonWidth, buttonHeight);		
		this.add(play);
		
		JButton report = new StandardButton("Report", buttonWidth, buttonHeight);		
		this.add(report);
		
		JButton follow = new StandardButton("Follow", buttonWidth, buttonHeight);		
		this.add(follow);
		
		JButton comment = new StandardButton("Comment", buttonWidth, buttonHeight);		
		this.add(comment);
		
		// Update
		tablita.insertMultiple(System.getInstance().getSongList());
		
		layout.putConstraint(SpringLayout.EAST, searchBar, 10, SpringLayout.EAST, this);
		
		layout.putConstraint(SpringLayout.EAST, tablita, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, tablita, -buttonHeight - buttonSep, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.EAST, play, -buttonSep * 3 - buttonWidth * 3, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, play, 0, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.EAST, report, -buttonSep * 2 - buttonWidth * 2, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, report, 0, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.EAST, follow, -buttonSep - buttonWidth, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, follow, 0, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.EAST, comment, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, comment, 0, SpringLayout.SOUTH, this);
	}
	
	public SideBarPanel getSideBar() {
		return this.sideBar;
	}

	public void updateSideBar() {
		this.remove(sideBar);
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
	}
}
