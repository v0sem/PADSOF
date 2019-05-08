package padsof.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.playable.Album;
import padsof.swing.items.StandardButton;

/**
 * Panel para ver los audios que hay dentro de un album
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
@SuppressWarnings("serial")
public class AlbumPanel extends JPanel {

	private SpringLayout layout;
	private JLabel title;
	private SearchBarPanel searchBar;
	private SideBarPanel sideBar;
	private ScrollableJTablePlayable tablita;	
	private StandardButton backButton;

	static int buttonWidth = 100;
	static int buttonHeight = 42;
	static int buttonSep = 17;
	
	/**
	 * Constructor de AlbumPanel
	 */
	public AlbumPanel() {
		layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		// PANEL DESCRIPTION / TITLE
		title = new JLabel("Showing album contents");
		title.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 22));
		this.add(title);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this);
				
		tablita = new ScrollableJTablePlayable(500, 285);
		this.add(tablita);
		
		backButton = new StandardButton("Back", 160, 25);
		this.add(backButton);
		
		layout.putConstraint(SpringLayout.WEST, tablita, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, tablita, 80, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, backButton, -20, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, backButton, 10, SpringLayout.SOUTH, tablita);
		layout.putConstraint(SpringLayout.NORTH, backButton, 10, SpringLayout.SOUTH, tablita);	
		
		this.setPreferredSize(new Dimension(800, 450));
	}

	/**
	 * Updater de las tablas
	 */
	public void updateTables(Album list) {
		if(tablita != null)
			this.remove(tablita);
				
		tablita.resetTable();
		tablita.insertMultiple(list.getList());
		this.add(tablita);
		
		layout.putConstraint(SpringLayout.WEST, tablita, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, tablita, 80, SpringLayout.NORTH, this);
	}

	public SideBarPanel getSideBar() {
		return this.sideBar;
	}

	/**
	 * Updater del sidebar
	 */
	public void updateSideBar() {
		this.remove(sideBar);
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
	}

	public SearchBarPanel getSearchBar() {
		return searchBar;
	}

	public static void setButtonWidth(int buttonWidth) {
		AlbumPanel.buttonWidth = buttonWidth;
	}
	
	public static void setButtonHeight(int buttonHeight) {
		AlbumPanel.buttonHeight = buttonHeight;
	}

	public static void setButtonSep(int buttonSep) {
		AlbumPanel.buttonSep = buttonSep;
	}
	
	public ScrollableJTablePlayable getTablita() {
		return tablita;
	}
	
	public void setControlador(ActionListener controlador){
		this.backButton.addActionListener(controlador);
	}

	public Object getBackButton() {
		return this.backButton;
	}
}
