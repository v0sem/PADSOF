package padsof.swing;

import javax.swing.*;

import padsof.control.*;

import java.awt.*;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	final static String LOGINPANEL = "Carta con el LOGIN";
	final static String MAINPANEL = "Main";
	final static String REGISTERPANEL = "Register";
	final static String ABOUTPANEL = "About";
	private static MainFrame instance = null;
	private	LoginPanel login = new LoginPanel();
	private MainPanel main = new MainPanel();
	private RegisterPanel register = new RegisterPanel();
	private AboutPanel about = new AboutPanel();
	
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
		
		login.setControlador(new LoginControl(login));
		register.setControlador(new RegisterControl(register));
		container.add(MAINPANEL, main);
		container.add(LOGINPANEL, login);
		container.add(REGISTERPANEL, register);
		container.add(ABOUTPANEL, about);
	
		//Colocar los componentes de acuerdo a sus tamanios
		this.setPreferredSize(new Dimension(800, 450));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static MainFrame getInstance() {

		if (instance == null)
			instance = new MainFrame();

		return instance;
	}
	
	public void mostrarMainPanel() {
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), MAINPANEL);
	}
	
	public void mostrarLogin() {
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), LOGINPANEL);
	}
	
	public void mostrarRegister() {
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), REGISTERPANEL);
	}
	
	public void mostrarAbout() {
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), ABOUTPANEL);
	}
	
	public void updateSideBar() {
		this.main.updateSideBar();
		this.about.updateSideBar();
	}
}
