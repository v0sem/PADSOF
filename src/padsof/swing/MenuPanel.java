package padsof.swing;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.swing.items.StandardButton;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel {
	
	private StandardButton home;
	private StandardButton lists;
	private StandardButton albums;
	private StandardButton audios;
	private StandardButton following;
	private StandardButton about;
	
	public MenuPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		home = new StandardButton("Inicio", 200, 42);
		lists = new StandardButton("Mis Listas", 200, 42);
		albums = new StandardButton("Mis Albumes", 200, 42);
		audios = new StandardButton("Mis Audios", 200, 42);
		following = new StandardButton("Usuarios Seguidos", 200, 42);
		about = new StandardButton("Sobre la app", 200, 42);
		
		this.add(home);
		this.add(lists);
		this.add(albums);
		this.add(audios);
		this.add(following);
		this.add(about);
		
		layout.putConstraint(SpringLayout.WEST, home, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, home, 0, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, lists, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lists, 40, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, albums, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, albums, 80, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, audios, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, audios, 120, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, following, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, following, 160, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, about, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, about, 200, SpringLayout.NORTH, this);
		
		this.setPreferredSize(new Dimension(200, 245));
	}
	
	public StandardButton getHome() {
		return home;
	}

	public StandardButton getLists() {
		return lists;
	}

	public StandardButton getAlbums() {
		return albums;
	}

	public StandardButton getAudios() {
		return audios;
	}

	public StandardButton getFollowing() {
		return following;
	}

	public StandardButton getAbout() {
		return about;
	}

	public void setControlador(ActionListener controlador){
		this.home.addActionListener(controlador);
		this.lists.addActionListener(controlador);
		this.albums.addActionListener(controlador);
		this.audios.addActionListener(controlador);
		this.following.addActionListener(controlador);
		this.about.addActionListener(controlador);
	}

}
