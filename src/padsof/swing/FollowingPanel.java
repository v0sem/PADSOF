package padsof.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.swing.items.StandardButton;
import padsof.system.System;
import padsof.user.User;

@SuppressWarnings("serial")
public class FollowingPanel extends JPanel {

	private SideBarPanel sideBar;
	
	private ScrollableJTableUser table;
	
	private StandardButton unfollowButton;

	private JLabel title;
	
	private SpringLayout layout;
	
	// TODO: ADD SONG TO SELECTED PLAYLIST BUTTON. NEW PLAYLIST BUTTON
	public FollowingPanel() {
		layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		// PANEL DESCRIPTION / TITLE
		title = new JLabel("Usuarios seguidos");
		title.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 22));
		this.add(title);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this);
		
		unfollowButton = new StandardButton("Dejar de seguir", 160, 25);
		this.add(unfollowButton);
		
		table = new ScrollableJTableUser(500, 290);
		this.add(table);
		
		layout.putConstraint(SpringLayout.WEST, table, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, table, 80, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, unfollowButton, 0, SpringLayout.WEST, table);
		layout.putConstraint(SpringLayout.NORTH, unfollowButton, 5, SpringLayout.SOUTH, table);
		
		this.setPreferredSize(new Dimension(800, 450));
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
		this.unfollowButton.addActionListener(controlador);
	}
	
	public void updateTables(){
		this.remove(table);
		table.resetTable();
		User u = System.getInstance().getLoggedUser();
		if (u != null) table.insertMultiple(u.getFollows());
		this.add(table);
		
		layout.putConstraint(SpringLayout.WEST, table, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, table, 80, SpringLayout.NORTH, this);
	}

	public StandardButton getUnfollowButton() {
		return unfollowButton;
	}

	public ScrollableJTableUser getTable() {
		return table;
	}

}
