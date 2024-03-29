package padsof.swing;

import padsof.playable.CommentableObject;
import padsof.system.System;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import padsof.control.AddAudioControl;
import padsof.control.AdminControl;
import padsof.control.AlbumControl;
import padsof.control.CommentControl;
import padsof.control.FollowingControl;
import padsof.control.GoPremiumControl;
import padsof.control.LoginControl;
import padsof.control.MainControl;
import padsof.control.MyAlbumsControl;
import padsof.control.MyPlaylistsControl;
import padsof.control.MySongsControl;
import padsof.control.PendingControl;
import padsof.control.PlaylistControl;
import padsof.control.RegisterControl;
import padsof.playable.Album;
import padsof.playable.Playlist;

/**
 * Panel principal de construccion
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
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
	final static String FOLLOWINGPANEL = "Following";
	final static String COMMENTPANEL = "Comments";
	final static String PLAYLISTPANEL = "Playlist Table";
	final static String ALBUMPANEL = "Album Table";
  
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
	private FollowingPanel following;
	private CommentPanel comment;
	private PlaylistPanel playlistpanel;
	private AlbumPanel albumpanel;
	
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
		following = new FollowingPanel();
		comment = new CommentPanel();
		playlistpanel = new PlaylistPanel();
		albumpanel = new AlbumPanel();
		
		login.setControlador(new LoginControl(login));
		register.setControlador(new RegisterControl(register));
		gopremium.setControlador(new GoPremiumControl(gopremium));
		addaudio.setControlador(new AddAudioControl(addaudio));
		mysongs.setControlador(new MySongsControl(mysongs));
		myplaylists.setControlador(new MyPlaylistsControl(myplaylists));
		myalbums.setControlador(new MyAlbumsControl(myalbums));
		admin.setControlador(new AdminControl(admin));
		pending.setControlador(new PendingControl(pending));
		main.setControlador(new MainControl(main));
		following.setControlador(new FollowingControl(following));
		playlistpanel.setControlador(new PlaylistControl(playlistpanel));
		albumpanel.setControlador(new AlbumControl(albumpanel));
		comment.setControlador(new CommentControl(comment));
		
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
		container.add(FOLLOWINGPANEL, following);
		container.add(COMMENTPANEL, comment);
		container.add(PLAYLISTPANEL, playlistpanel);
		container.add(ALBUMPANEL, albumpanel);
	
		//Colocar los componentes de acuerdo a sus tamanios
		this.setPreferredSize(new Dimension(800, 450));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				JFrame frame = (JFrame) e.getSource();
				try {
					System.getInstance().saveData();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}
		});
		this.setResizable(false);
	}
	
	public static MainFrame getInstance() {

		if (instance == null)
			instance = new MainFrame();

		return instance;
	}
	
	public void mostrarMainPanel() {
		main.updateTables();
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
		pending.updateTables();
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), PENDINGPANEL);
	}
	
	public void mostrarFollowing() {
		following.updateTable();
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), FOLLOWINGPANEL);
	}
	
	public void mostrarPlaylist(Playlist p) {
		playlistpanel.updateTables(p);
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), PLAYLISTPANEL);
	}
  
	public void mostrarComment(CommentableObject song) {
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), COMMENTPANEL);
		this.comment.updateTables(song);
	}
	
	public void mostrarAlbum(Album p) {
		albumpanel.updateTables(p);
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
		cl.show(this.getContentPane(), ALBUMPANEL);
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
		this.login.updateSideBar();
		this.register.updateSideBar();
		this.following.updateSideBar();
		this.comment.updateSideBar();
		this.playlistpanel.updateSideBar();
		this.albumpanel.updateSideBar();
	}
}
