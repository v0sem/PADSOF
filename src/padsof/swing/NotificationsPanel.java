package padsof.swing;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.system.System;
import padsof.user.User;

/**
 * Panel para mostrar las notificaciones
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
@SuppressWarnings("serial")
public class NotificationsPanel extends JPanel {

	private JLabel title;
	private SideBarPanel sideBar;
	private ScrollableJTableNotification table;
	private SpringLayout layout;

	/**
	 * Constructor de NotificationsPanel
	 */
	public NotificationsPanel() {
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
		
		table = new ScrollableJTableNotification(550, 340);
		User u = System.getInstance().getLoggedUser();
		if (u != null) table.insertMultiple(u.getNotifications());
		
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
