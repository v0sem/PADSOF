package padsof.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import padsof.swing.items.StandardButton;

@SuppressWarnings("serial")
public class AddAudioPanel extends JPanel {

	private JLabel songTitleLabel;
	private JTextField songTitleField;
	private StandardButton button;
	private JLabel pathLabel;
	private JTextField pathField;
	private StandardButton path;
	private JLabel title;
	private SideBarPanel sideBar;

	public AddAudioPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		// PANEL DESCRIPTION / TITLE
		title = new JLabel("Anadir un audio");
		title.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 22));
		this.add(title);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, title, -80, SpringLayout.VERTICAL_CENTER, this);
		
		// NAME
		songTitleLabel = new JLabel("Titulo del audio: ");
		songTitleLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		this.add(songTitleLabel);
		songTitleField = new JTextField(20);
		this.add(songTitleField);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, songTitleLabel, -18, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, songTitleLabel, -22, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, songTitleField, 0, SpringLayout.NORTH, songTitleLabel);
		layout.putConstraint(SpringLayout.WEST, songTitleField, 6, SpringLayout.EAST, songTitleLabel);
	
		// PATH TO FILE
		pathLabel = new JLabel("Ruta al archivo: ");
		pathLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		this.add(pathLabel);
		path = new StandardButton("Choose File", 120, 20);
		this.add(path);
		pathField = new JTextField(20);
		this.add(pathField);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, pathLabel, -17, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, pathLabel, 5, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, pathField, 0, SpringLayout.NORTH, pathLabel);
		layout.putConstraint(SpringLayout.WEST, pathField, 6, SpringLayout.EAST, pathLabel);
		layout.putConstraint(SpringLayout.NORTH, path, 5, SpringLayout.SOUTH, pathField);
		layout.putConstraint(SpringLayout.WEST, path, 0, SpringLayout.WEST, pathField);
		
		// BUTTON
		button = new StandardButton("Anadir audio", 200, 60);
		this.add(button);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, button, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, button, 110, SpringLayout.VERTICAL_CENTER, this);
		this.setPreferredSize(new Dimension(400, 150));
	}
	
	public void setControlador(ActionListener controlador) {
		this.button.addActionListener(controlador);
		this.path.addActionListener(controlador);
	}
	
	public JTextField getPathField() {
		return pathField;
	}
	
	public JTextField getSongTitle() {
		return songTitleField;
	}
	
	public StandardButton getPath() {
		return path;
	}
	
	public void updateSideBar() {
		this.remove(sideBar);
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
	}
}
