package padsof.swing;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.system.System;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private JPanel searchBar;
	private SideBarPanel sideBar;
	
	public MainPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		this.searchBar = new SearchBarPanel();
		this.add(searchBar);
		
		ScrollableJTable tablita = new ScrollableJTable(new String[]{"a", "b", "c"}, 340, 550);
		this.add(tablita);
		
		tablita.insertMultiple(System.getInstance().getSongList());
		
		layout.putConstraint(SpringLayout.EAST, searchBar, 10, SpringLayout.EAST, this);
		
		layout.putConstraint(SpringLayout.EAST, tablita, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, tablita, 0, SpringLayout.SOUTH, this);
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
