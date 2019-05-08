package padsof.swing;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import padsof.swing.items.StandardButton;

/**
 * Panel con la barra de busqueda 
 *  
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
@SuppressWarnings("serial")
public class SearchBarPanel extends JPanel {

	private JTextField searchField;
	private StandardButton searchButton;
	private JCheckBox author;
	private JLabel authorLabel;
	private JCheckBox audio;
	private JLabel audioLabel;
	private JCheckBox album;
	private JLabel albumLabel;
	
	/**
	 * Constructor del panel de busqueda
	 */
	public SearchBarPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		this.searchField = new JTextField(35);
		searchField.setMinimumSize(new Dimension(100, 35));
		this.searchButton = new StandardButton("Buscar", 100, 20);
		this.author = new JCheckBox();
		this.audio = new JCheckBox();
		this.album = new JCheckBox();
		this.authorLabel = new JLabel("author");
		this.audioLabel = new JLabel("audio");
		this.albumLabel = new JLabel("album");
		
		this.add(searchField);
		this.add(searchButton);
		this.add(author);
		this.add(audio);
		this.add(album);
		this.add(authorLabel);
		this.add(audioLabel);
		this.add(albumLabel);
		
		layout.putConstraint(SpringLayout.WEST, searchField, 35, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, searchField, 10, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.EAST, searchButton, -25, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, searchButton, 10, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, author, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, author, 40, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, audio, 75, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, audio, 40, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, album, 150, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, album, 40, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, authorLabel, 25, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, authorLabel, 40, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, audioLabel, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, audioLabel, 40, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, albumLabel, 175, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, albumLabel, 40, SpringLayout.NORTH, this);
		
		this.setPreferredSize(new Dimension(600, 65));
	}
	
	public void setControlador(ActionListener controlador){
		this.searchButton.addActionListener(controlador);
	}
	
	/**
	 * Getter del campo de busqueda
	 * @return campo de busqueda
	 */
	public JTextField getSearchField() {
		return searchField;
	}

	/**
	 * Getter del checkBox del autor
	 * @return checkBox del autor
	 */
	public JCheckBox getAuthor() {
		return author;
	}

	/**
	 * Getter del checkBox del audio
	 * @return checkBox del audio
	 */
	public JCheckBox getAudio() {
		return audio;
	}

	/**
	 * Getter del checkBox del album
	 * @return checkBox del album
	 */
	public JCheckBox getAlbum() {
		return album;
	}

	/**
	 * Getter del boton de busqueda
	 * @return boton de busqueda
	 */
	public StandardButton getSearchButton() {
		return searchButton;
	}
}
