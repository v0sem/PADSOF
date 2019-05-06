package padsof.swing;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

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
		layout.putConstraint(SpringLayout.EAST, searchBar, 10, SpringLayout.EAST, this);
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
