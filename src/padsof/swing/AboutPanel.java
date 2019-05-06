package padsof.swing;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class AboutPanel extends JPanel {

	private JLabel paragraph;
	private JLabel title;
	private SideBarPanel sideBar;

	public AboutPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		// PANEL DESCRIPTION / TITLE
		title = new JLabel("Sobre la aplicacion");
		title.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 22));
		this.add(title);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this);
				
		// TEXT
		paragraph = new JLabel("Texto que explica como va la aplicasion ");
		paragraph.setFont(new Font("Roboto", Font.BOLD, 14));
		this.add(paragraph);
		layout.putConstraint(SpringLayout.WEST, paragraph, 240, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, paragraph, 5, SpringLayout.VERTICAL_CENTER, this);
		
	}
	
	public void updateSideBar() {
		this.remove(sideBar);
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
	}
}
