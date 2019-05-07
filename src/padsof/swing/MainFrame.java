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
	final static String GOPREMIUMPANEL = "Go premium";
	final static String ADDAUDIOPANEL = "Add audio";
	final static String ADMINPANEL = "Admin basic config";
	private static MainFrame instance = null;
	private	LoginPanel login;
	private MainPanel main;
	private RegisterPanel register;
	private AboutPanel about;
	private GoPremiumPanel gopremium;
	private AddAudioPanel addaudio;
	private AdminPanel admin;
	
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
		
		login = new LoginPanel();
		main = new MainPanel();
		register = new RegisterPanel();
		about = new AboutPanel();
		gopremium = new GoPremiumPanel();
		addaudio = new AddAudioPanel();
		admin = new AdminPanel();
		
		login.setControlador(new LoginControl(login));
		register.setControlador(new RegisterControl(register));
		gopremium.setControlador(new GoPremiumControl(gopremium));
		addaudio.setControlador(new AddAudioControl(addaudio));
		
		container.add(MAINPANEL, main);
		container.add(LOGINPANEL, login);
		container.add(REGISTERPANEL, register);
		container.add(ABOUTPANEL, about);
		container.add(GOPREMIUMPANEL, gopremium);
		container.add(ADDAUDIOPANEL, addaudio);
		container.add(ADMINPANEL, admin);
	
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

	public void mostrarGoPremium() {
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), GOPREMIUMPANEL);
	}
	
	public void mostrarAddAudio() {
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), ADDAUDIOPANEL);
	}
	
	public void mostrarAdmin() {
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), ADMINPANEL);
	}
	
	public void updateSideBar() {
		this.main.updateSideBar();
		this.about.updateSideBar();
		this.gopremium.updateSideBar();
		this.addaudio.updateSideBar();
	}
}
