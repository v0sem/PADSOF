package padsof.swing;

import javax.swing.*;

import padsof.control.*;

import java.awt.*;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	final static String LOGINPANEL = "Carta con el LOGIN";
	final static String MAINPANEL = "Main";
	final static String REGISTERPANEL = "Register";
	final static String NOTIFICATIONSPANEL = "Notifications";
	final static String GOPREMIUMPANEL = "Go premium";
	final static String ADDAUDIOPANEL = "Add audio";
	final static String MYSONGSPANEL = "My Songs";
	final static String MYPLAYLISTSPANEL = "My Playlists";
	final static String MYALBUMSPANEL = "My Albums";
	final static String ADMINPANEL = "Admin basic config";
	final static String PENDINGPANEL = "Pending song review";
	private static MainFrame instance = null;
	private	LoginPanel login;
	private MainPanel main;
	private RegisterPanel register;
	private NotificationsPanel notifications;
	private GoPremiumPanel gopremium;
	private AddAudioPanel addaudio;
	private MySongsPanel mysongs;
	private MyPlaylistsPanel myplaylists;
	private MyAlbumsPanel myalbums;
	private AdminPanel admin;
	private PendingAdminPanel pending;
	
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
		notifications = new NotificationsPanel();
		gopremium = new GoPremiumPanel();
		addaudio = new AddAudioPanel();
		mysongs = new MySongsPanel();
		myplaylists = new MyPlaylistsPanel();
		myalbums = new MyAlbumsPanel();
		admin = new AdminPanel();
		pending = new PendingAdminPanel();
		
		login.setControlador(new LoginControl(login));
		register.setControlador(new RegisterControl(register));
		gopremium.setControlador(new GoPremiumControl(gopremium));
		addaudio.setControlador(new AddAudioControl(addaudio));
		mysongs.setControlador(new MySongsControl(mysongs));
		myplaylists.setControlador(new MyPlaylistsControl(myplaylists));
		myalbums.setControlador(new MyAlbumsControl(myalbums));
		admin.setControlador(new AdminControl(admin));
		
		container.add(MAINPANEL, main);
		container.add(LOGINPANEL, login);
		container.add(REGISTERPANEL, register);
		container.add(NOTIFICATIONSPANEL, notifications);
		container.add(GOPREMIUMPANEL, gopremium);
		container.add(ADDAUDIOPANEL, addaudio);
		container.add(MYSONGSPANEL, mysongs);
		container.add(MYPLAYLISTSPANEL, myplaylists);
		container.add(MYALBUMSPANEL, myalbums);
		container.add(ADMINPANEL, admin);
		container.add(PENDINGPANEL, pending);
	
		//Colocar los componentes de acuerdo a sus tamanios
		this.setPreferredSize(new Dimension(800, 450));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
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
	
	public void mostrarNotifications() {
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), NOTIFICATIONSPANEL);
	}

	public void mostrarGoPremium() {
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), GOPREMIUMPANEL);
	}
	
	public void mostrarAddAudio() {
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), ADDAUDIOPANEL);
	}
	
	public void mostrarMySongs() {
		this.mysongs.updateTables();
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), MYSONGSPANEL);
	}
	
	public void mostrarMyPlaylists() {
		this.myplaylists.updateTables();
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), MYPLAYLISTSPANEL);
	}
	
	public void mostrarMyAlbums() {
		this.myalbums.updateTables();
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), MYALBUMSPANEL);
	}
		
	public void mostrarAdmin() {
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), ADMINPANEL);
	}
	
	public void mostrarPending() {
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), PENDINGPANEL);
	}
	
	public void updateSideBar() {
		this.main.updateSideBar();
		this.notifications.updateSideBar();
		this.gopremium.updateSideBar();
		this.addaudio.updateSideBar();
		this.mysongs.updateSideBar();
		this.myplaylists.updateSideBar();
		this.myalbums.updateSideBar();
		this.admin.updateSideBar();
		this.pending.updateSideBar();
	}
}
