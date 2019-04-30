package padsof.swing;

import java.awt.Checkbox;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import padsof.swing.items.StandardButton;

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
	
	public SearchBarPanel() {
		this.searchField = new JTextField();
		this.searchButton = new StandardButton("Buscar", 70, 25);
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
		
		
		this.setPreferredSize(new Dimension(600, 150));
	}
}
