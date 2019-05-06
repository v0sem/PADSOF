package padsof.swing;

//// CARLAYAUT
//// CREAR LA VENTANA TAL
//// SI TAL, IR A LA VENTANA TAL
//// HEREDA DE JFRAME
//// TENER METODOS PARA PASAR DE UNA VENT A OTRA
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import padsof.control.*;

import java.awt.*;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	final static String LOGINPANEL = "Carta con el LOGIN";
	final static String SIDEBAR = "Sidebar";
	private static MainFrame instance = null;
	
	/**
	 * Constructor de mainPanel
	 */
	public MainFrame() {
		super("MP3BALL");
		ImageIcon img = new ImageIcon("icons/MP3BALL-icon.png");
		super.setIconImage(img.getImage());
		super.setFont(new Font("Roboto", Font.BOLD, 13));
		
		Container container = this.getContentPane();
		container.setLayout(new CardLayout());
		
		 String[] titulos = {"Titulo", "Autor", "Duracion", ""};
		Object[][] filas = {
				{"Alba",   "Sanz",  5,   true},
				{"Belén",  "López", 3.2, true},
				{"Luisa",  "López", 4.5, true},
				{"Marcos", "Pérez", 8.5, true},
				{"Miguel", "Vela",  7,   true},
				{"Sara",   "Valle", 10,  true},
		};
		/* Tabla de creacion de playlists dinamica
		AbstractTableModel tablaDeMierda = new PlaylistCreationTable(titulos, filas);
		JTable tabla = new JTable(tablaDeMierda);
		container.add(tabla);*/
		
		DefaultTableModel tablaDeMierda = new DefaultTableModel(filas, titulos
				);
		JTable tabla = new JTable(tablaDeMierda);
		container.add(tabla);
		
		LoginPanel login = new LoginPanel();
		login.setControlador(new LoginControl(login, this));
		
		SideBarPanel side = new SideBarPanel();
		//TODO:Ay k meter 1 Kontrolador
		
		//Aniadimos los componentes al container
		container.add(SIDEBAR, side);
		container.add(LOGINPANEL, login);
		
	
		//Colocar los componentes de acuerdo a sus tamanios
		this.setPreferredSize(new Dimension(800, 450));
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static MainFrame getInstance() {

		if (instance == null)
			instance = new MainFrame();

		return instance;
	}
	
	public void test() {
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), SIDEBAR);
	}
	
	public void mostrarLogin(){
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), LOGINPANEL);
	}
}
