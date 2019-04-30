package padsof.swing;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private JPanel searchBar;
	private JPanel sideBar;
	
	public MainPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		this.searchBar = new SearchBarPanel();
		this.sideBar = new SideBarPanel();
		
		this.add(searchBar);
		this.add(sideBar);
		
		layout.putConstraint(SpringLayout.WEST, sideBar, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, sideBar, 0, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.EAST, searchBar, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, searchBar, 0, SpringLayout.NORTH, this);
		
		this.setPreferredSize(new Dimension(800, 450));
	}
}
