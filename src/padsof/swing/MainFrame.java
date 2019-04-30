package padsof.swing;

//// CARLAYAUT
//// CREAR LA VENTANA TAL
//// SI TAL, IR A LA VENTANA TAL
//// HEREDA DE JFRAME
//// TENER METODOS PARA PASAR DE UNA VENT A OTRA
import javax.swing.*;

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
