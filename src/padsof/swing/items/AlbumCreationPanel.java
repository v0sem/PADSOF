package padsof.swing.items;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AlbumCreationPanel extends JPanel {
	
	JTextField titulo;
	
	JTextField ano;

	public AlbumCreationPanel() {
		titulo = new JTextField(5);
		ano = new JTextField(5);
		this.add(new JLabel("Titulo:"));
		this.add(titulo);
		this.add(Box.createHorizontalStrut(15)); // a spacer
		this.add(new JLabel("Ano:"));
		this.add(ano);	
	}

	public JTextField getTitulo() {
		return titulo;
	}

	public JTextField getAno() {
		return ano;
	}
}
