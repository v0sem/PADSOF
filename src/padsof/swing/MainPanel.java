package padsof.swing;

import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private JPanel searchBar;
	
	public MainPanel() {
		this.searchBar = new SearchBarPanel();
		
		this.add(searchBar);
		
		this.setPreferredSize(new Dimension(600, 450));
	}
}
