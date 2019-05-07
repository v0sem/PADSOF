package padsof.swing;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import padsof.playable.Song;
import padsof.system.System;

@SuppressWarnings("serial")
public class AboutPanel extends JPanel {

	private JLabel title;
	private SideBarPanel sideBar;
	private ScrollableJTable table;
	private SpringLayout layout;

	public AboutPanel() {
		layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		// PANEL DESCRIPTION / TITLE
		title = new JLabel("Notificaciones");
		title.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 22));
		this.add(title);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this);
		
		table = new ScrollableJTable(new String[]{"a", "b", "c"}, 550, 340);
		//table.insertMultiple(System.getInstance().getLoggedUser().getNotifications());
		
		this.add(table);
		layout.putConstraint(SpringLayout.EAST, table, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, table, 0, SpringLayout.SOUTH, this);
	}
	
	public void updateSideBar() {
		this.remove(sideBar);
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
	}
	
	public void updateTable() {

	}
}
