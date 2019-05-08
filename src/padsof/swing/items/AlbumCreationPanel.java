package padsof.swing.items;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel para crear un Nuevo Album
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
@SuppressWarnings("serial")
public class AlbumCreationPanel extends JPanel {
	
	/**
	 * Titulo del nuevo Album
	 */
	JTextField titulo;
	
	/**
	 * Ano del nuevo Album
	 */
	JTextField ano;

	/**
	 * Constructor de clase.
	 */
	public AlbumCreationPanel() {
		titulo = new JTextField(5);
		ano = new JTextField(5);
		this.add(new JLabel("Titulo:"));
		this.add(titulo);
		this.add(Box.createHorizontalStrut(15)); // a spacer
		this.add(new JLabel("Ano:"));
		this.add(ano);	
	}

	/**
	 * Getter del titulo
	 * 
	 * @return titulo del album
	 */
	public JTextField getTitulo() {
		return titulo;
	}

	/**
	 * Getter del ano
	 * 
	 * @return ano del album
	 */
	public JTextField getAno() {
		return ano;
	}
}
