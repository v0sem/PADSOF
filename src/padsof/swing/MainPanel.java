package padsof.swing;

//// CARLAYAUT
//// CREAR LA VENTANA TAL
//// SI TAL, IR A LA VENTANA TAL
//// HEREDA DE JFRAME
//// TENER METODOS PARA PASAR DE UNA VENT A OTRA
import javax.swing.*;

import padsof.control.*;

import java.awt.*;

public class MainPanel extends JFrame {
	
	private static final long serialVersionUID = 1L;
	final static String LOGINPANEL = "Carta con el LOGIN";
	
	/**
	 * Constructor de mainPanel
	 */
	public MainPanel() {
		super("MP3BALL");
		super.setFont(new Font("Roboto", Font.BOLD, 13));
		
		Container container = this.getContentPane();
		container.setLayout(new CardLayout());
		
		LoginPanel login = new LoginPanel();
		login.setControlador(new LoginControl(login, this));

		//Aniadimos los componentes al container
		container.add(LOGINPANEL, login);
	
		//Colocar los componentes de acuerdo a sus tamaños
		this.setPreferredSize(new Dimension(800, 450));
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void mostrarLogin(){
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), LOGINPANEL);
	}
}
