package padsof.sistem;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;

import fechasimulada.FechaSimulada;
import padsof.Status;
import padsof.interactions.Notification;
import padsof.interactions.Report;
import padsof.playable.Album;
import padsof.playable.PlayableObject;
import padsof.playable.Playlist;
import padsof.playable.Song;
import padsof.playable.SongState;
import padsof.user.User;
import padsof.user.UserType;

/**
 * Clase principal de la aplicacion
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
public class Sistem implements java.io.Serializable {

	/**
	 * Instancia del sistema
	 */
	private static Sistem instance = null;

	/**
	 * Usuario administrador
	 */
	private User adminUser = null;

	/**
	 * Usuario loggeado actualmente
	 */
	private User loggedUser = null; // mientras este a null, estamos en anon

	/**
	 * Numero de canciones que a los usuarios anonimos les queda por reproducir
	 */
	private long anonSongCount;

	/**
	 * Lista de usuarios
	 */
	private ArrayList<User> userList;

	/**
	 * Lista de canciones
	 */
	private ArrayList<Song> songList;

	/**
	 * Lista de albumes
	 */
	private ArrayList<Album> albumList;

	/**
	 * Lista de Playlists
	 */
	private ArrayList<Playlist> playlistList;

	/**
	 * Lista de reportes
	 */
	private ArrayList<Report> reportList;

	/**
	 * Fecha en la que se resetean el numero de canciones reproducibles por los
	 * usuarios anonimos
	 */
	private LocalDate songCountDate;

	/**
	 * Precio configurable para ser premium
	 */
	private double premiumPrice;

	/**
	 * Maximo configurable del numero de canciones que un usuario anonimo puede
	 * reproducir
	 */
	private long maxAnonSong;

	/**
	 * Maximo configurable del numero de canciones que un usuario registrado puede
	 * reproducir
	 */
	private long maxRegisteredSong;

	/**
	 * Numero de veces que tienen que ser reproducidas las canciones de un usuario
	 * para que se le pase automaticamente a premium
	 */
	private long playsToPremium;

	/**
	 * Constructor de la clase Sistem. Inicializa con valores por defecto y con un
	 * usuario Admin User. Carga los datos de un fichero si este existe
	 */
	public Sistem() {

		// si existe un archivo para cargarlo, lo carga
		if (new File("System.bal").exists()) {
			this.loadData();
		} else {
			this.userList = new ArrayList<User>();
			this.songList = new ArrayList<Song>();
			this.albumList = new ArrayList<Album>();
			this.playlistList = new ArrayList<Playlist>();
			this.reportList = new ArrayList<Report>();

			this.songCountDate = LocalDate.now();

			this.maxRegisteredSong = 1000;
			this.maxAnonSong = 1000;

			this.anonSongCount = this.maxAnonSong;

			this.adminUser = new User("Admin User", LocalDate.of(1980, Month.JANUARY, 1), "admin", "admin");
			this.adminUser.setUserType(UserType.ADMIN);
			this.userList.add(this.adminUser);

			this.premiumPrice = 19.99;
			this.playsToPremium = 10000;
		}
	}

	/**
	 * Permite recoger la instancia de sistema y que solo haya un sistema
	 * 
	 * @return el Sistem
	 */
	public static Sistem getInstance() {

		if (instance == null)
			instance = new Sistem();

		return instance;
	}

	/**
	 * Carga los datos de Sistem de un fichero System.bal
	 * 
	 * @return Sistem cargado
	 */
	public Sistem loadData() {
		try {
			FileInputStream fin = new FileInputStream("System.bal");
			ObjectInputStream ois = new ObjectInputStream(fin);
			Sistem loadedSystem = (Sistem) ois.readObject();

			this.adminUser = loadedSystem.adminUser;
			this.loggedUser = loadedSystem.loggedUser;
			this.songList = loadedSystem.songList;
			this.albumList = loadedSystem.albumList;
			this.playlistList = loadedSystem.playlistList;
			this.userList = loadedSystem.userList;
			this.reportList = loadedSystem.reportList;
			this.anonSongCount = loadedSystem.anonSongCount;
			this.songCountDate = loadedSystem.songCountDate;
			this.premiumPrice = loadedSystem.premiumPrice;
			this.maxAnonSong = loadedSystem.maxAnonSong;
			this.maxRegisteredSong = loadedSystem.maxRegisteredSong;
			this.playsToPremium = loadedSystem.playsToPremium;

			loadedSystem.checkDate();
			ois.close();
			return loadedSystem;
		} catch (IOException | ClassNotFoundException r) {
			return null;
		}
	}

	/**
	 * Comprueba la fecha de hoy y realiza todas las acciones necesarias dependiendo
	 * del resultado
	 */
	public void checkDate() {
		LocalDate today = FechaSimulada.getHoy();
		Period period;

		// Check songs reported and delete dem
		ArrayList<Report> reportsToRemove = new ArrayList<Report>();
		for (Report r : this.reportList) {
			if (r.getClosed() == true) {
				period = Period.between(r.getDecisionDate(), today);
				// #DEBUG:
				System.out.println("Fecha de decision: " + r.getDecisionDate() + " Hoy: " + today);

				// Unblock the user if 30 days have passed
				if (period.getDays() >= 30 || period.getMonths() > 0 || period.getYears() > 0) {
					System.out.println("Unblock user and removing report");
					r.getReporter().unblock();
					reportsToRemove.add(r);
				}
			}
		}

		// Remove all report we found
		for (Report r : reportsToRemove) {
			this.reportList.remove(r);
		}

		// Check anon song counts
		period = Period.between(today, this.songCountDate);

		if (period.getDays() >= 30 || period.getMonths() > 0 || period.getYears() > 0) {
			this.anonSongCount = maxAnonSong;
		}

		if (loggedUser == null)
			return;

		// Check registered song counts
		period = Period.between(today, this.loggedUser.getRegisteredDate());

		if (period.getDays() >= 30 || period.getMonths() > 0 || period.getYears() > 0) {
			// reset date
			this.loggedUser.setRegisterdDate(today);
			// reset count
			this.loggedUser.setSongCount(this.maxRegisteredSong);
		}

		// Check if premium users have to pay again
		if (this.loggedUser.getUserType() == UserType.PREMIUM) {
			// If they are privileged, no need to ask them to pay
			if (this.loggedUser.getSongPlaycount() > this.playsToPremium)
				return;

			// User is premium, check for payment
			period = Period.between(today, this.loggedUser.getPremiumDate());

			if (period.getDays() >= 30 || period.getMonths() > 0 || period.getYears() > 0) {
				// Demote user first
				this.loggedUser.setUserType(UserType.STANDARD);

				// Check if we have the credit card number from last payment
				if (this.loggedUser.getCardNumber() == null) {
					System.out.println("Credit card not provided...?");
					return;
				}

				// If we do, pay the saved price with last credit card
				this.loggedUser.goPremium(this.loggedUser.getCardNumber());
			}
		}
	}

	/************************** Getters ***************************/

	/**
	 * Getter de AdminUser
	 * 
	 * @return admin user
	 */
	public User getAdminUser() {

		return adminUser;
	}

	/**
	 * Getter del usuario loggeado
	 * 
	 * @return usuario loggeado o null si no hay nadie loggeado
	 */
	public User getLoggedUser() {

		return loggedUser;
	}

	/**
	 * Getter de AnonSongCount
	 * 
	 * @return numero de reproducciones que le quedan a los usuarios anonimos
	 */
	public Long getAnonSongCount() {

		return anonSongCount;
	}

	/**
	 * Getter de PremiumPrice
	 * 
	 * @return precio para ser premium
	 */
	public double getPremiumPrice() {
		return premiumPrice;
	}

	/**
	 * Getter de MaxAnonSongCount
	 * 
	 * @return numero maximo de reproducciones de los anonimos
	 */
	public long getMaxAnonSong() {
		return maxAnonSong;
	}

	/**
	 * Getter de MaxRegisteredSong
	 * 
	 * @return numero maximo de reproducciones de los registrados
	 */
	public long getMaxRegisteredSong() {
		return maxRegisteredSong;
	}

	/**
	 * Getter de MaxRegisteredSong
	 * 
	 * @return numero maximo de reproducciones de los registrados
	 */
	public long getPlaysToPremium() {
		return playsToPremium;
	}

	/*************************** Setters ***************************/

	/**
	 * Decreases by one anonSongCount
	 */
	public void increaseAnonSongCount() {

		anonSongCount--;
	}

	/**
	 * Setter de MaxAnonSong
	 * 
	 * @param count nuevo maximo
	 */
	public void setMaxAnonSong(long count) {

		maxAnonSong = count;
	}

	/**
	 * Setter de PremiumPrice
	 * 
	 * @param premiumPrice nuevo precio
	 */
	public void setPremiumPrice(double premiumPrice) {
		this.premiumPrice = premiumPrice;
	}

	/**
	 * Setter de MaxRegisteredSong
	 * 
	 * @param maxRegisteredSong nuevo maximo
	 */
	public void setMaxRegisteredSong(long maxRegisteredSong) {
		this.maxRegisteredSong = maxRegisteredSong;
	}

	/**
	 * Setter de PlaysToPremium
	 * 
	 * @param playsToPremium nueva meta
	 */
	public void setPlaysToPremium(long playsToPremium) {
		this.playsToPremium = playsToPremium;
	}

	/********************** Adding to Lists ************************/

	/**
	 * Aniade un user al sistema
	 * 
	 * @param user un usuario
	 * @return ERROR en caso de que el user sea null
	 */
	public Status addUser(User user) {
		if (user != null) {
			this.userList.add(user);
			return Status.OK;
		}

		return Status.ERROR;
	}

	/**
	 * Aniade una cancion al sistema
	 * 
	 * @param s una cancion
	 * @return ERROR en caso de que la cancion sea null
	 */
	public Status addSong(Song s) {

		if (s != null && loggedUser != null) {
			this.songList.add(s);

			for (User u : loggedUser.getIsFollowed()) {
				u.notificate(new Notification(loggedUser.getNick() + " subi� una nueva canci�n", loggedUser, s));
			}

			return Status.OK;
		}

		return Status.ERROR;
	}

	/**
	 * Aniade un album al sistema
	 * 
	 * @param a un album
	 * @return ERROR en caso de que el album sea null
	 */
	public Status addAlbum(Album a) {

		if (a != null && loggedUser != null) {
			this.albumList.add(a);
			for (User u : loggedUser.getIsFollowed()) {
				u.notificate(new Notification(loggedUser.getNick() + " subi� un nuevo album", loggedUser, a));
			}
			return Status.OK;
		}

		return Status.ERROR;
	}

	/**
	 * Aniade una playlist al sistema
	 * 
	 * @param pl una playlist
	 * @return ERROR en caso de que la playlist sea null
	 */
	public Status addPlaylist(Playlist pl) {

		if (pl != null && loggedUser != null) {
			this.playlistList.add(pl);
			for (User u : loggedUser.getIsFollowed()) {
				u.notificate(new Notification(loggedUser.getNick() + " subi� una nueva playlist", loggedUser, pl));
			}
			return Status.OK;
		}

		return Status.ERROR;
	}

	/******************* Removing from lists *****************/

	/**
	 * Elimina un user del sistema
	 * 
	 * @param user a eliminar
	 * @return ERROR en el caso de que user sea null
	 */
	public Status deleteUser(User user) {

		if (user != null) {
			this.userList.remove(user);
			return Status.OK;
		}

		return Status.ERROR;
	}

	/**
	 * Elimina una cancion del sistema
	 * 
	 * @param s a eliminar
	 * @return ERROR en el caso de que s sea null
	 */
	public Status deleteSong(Song s) {

		if (s != null) {
			this.songList.remove(s);
			return Status.OK;
		}

		return Status.ERROR;
	}

	/**
	 * Elimina un album del sistema
	 * 
	 * @param a a eliminar
	 * @return ERROR en el caso de que a sea null
	 */
	public Status deleteAlbum(Album a) {

		if (a != null) {
			this.albumList.remove(a);
			return Status.OK;
		}

		return Status.ERROR;
	}

	/**
	 * Elimina una playlist del sistema
	 * 
	 * @param pl a eliminar
	 * @return ERROR en el caso de que pl sea null
	 */
	public Status deletePlaylist(Playlist pl) {

		if (pl != null) {
			this.playlistList.remove(pl);
			return Status.OK;
		}

		return Status.ERROR;
	}

	/******************** Getting from lists *******************/

	/**
	 * Comprueba si ya hay alguien que tiene este nick
	 * 
	 * @param userNick a comprobar
	 * 
	 * @return true si ya existe
	 */
	private Boolean userNickExists(String userNick) {

		for (User user : this.userList) {
			if (userNick.equals(user.getNick())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Comprueba si ya hay alguien que tiene este nombre
	 * 
	 * @param userName a comprobar
	 * @return true si ya existe
	 */
	private Boolean userNameExists(String userName) {
		if (this.userList == null)
			System.out.println("user list esta a nul"); // #DEBUG
		for (User user : this.userList) {
			if (userName.equals(user.getName())) {
				return true;
			}
		}

		return false;
	}

	/************************ User related *********************/

	/**
	 * Crea un nuevo usuario y lo anade al sistema
	 * 
	 * @param userName nombre del usuario (unico)
	 * @param userNick nick del usuario (unico)
	 * @param date     fecha de nacimiento
	 * @param userPass contrasena
	 * 
	 * @return ERROR si algo ha ido mal
	 */
	public Status register(String userName, String userNick, LocalDate date, String userPass) {

		if (userName == null || userNick == null || date == null || userPass == null || userNameExists(userName)
				|| userNickExists(userNick))
			return Status.ERROR;

		this.userList.add(new User(userName, date, userNick, userPass));

		return Status.OK;
	}

	/**
	 * Loggea a un usuario al sistema
	 * 
	 * @param userName nick del usuario
	 * @param userPass contrasena del usuario
	 * 
	 * @return ERROR si algo ha ido mal
	 */
	public Status login(String userName, String userPass) {

		if (loggedUser != null && userPass != null)
			return Status.ERROR;

		for (User u : userList) {
			if (userName.equals(u.getNick()) && userPass.equals(u.getPassword())) {
				loggedUser = u; // Si es admin tambien le loggea

				return Status.OK;
			}
		}

		return Status.ERROR;
	}

	/**
	 * Desloggea al que estuviese loggeado en el sistema y guarda el sistema
	 * 
	 * @return ERROR en caso de IOException
	 */
	public Status logout() {

		loggedUser = null;

		try {
			saveData();
		} catch (IOException e) {
			return Status.ERROR;
		}

		return Status.OK;
	}

	/**
	 * Checks if admin is logged
	 * 
	 * @return boolean
	 */
	public boolean adminIsLogged() {

		if (loggedUser == adminUser)
			return true;
		return false;
	}

	/************************* Song Related ********************/

	/**
	 * Busca a partir de la query con las restricciones oportunas
	 * 
	 * @param query  string de busqueda
	 * @param title  busqueda por titulo
	 * @param author busqueda por autor
	 * @param album  busqueda por album
	 * 
	 * @return lista con todos los resultados de busqueda
	 */
	public ArrayList<PlayableObject> search(String query, Boolean title, Boolean author, Boolean album) {

		ArrayList<PlayableObject> search = new ArrayList<PlayableObject>();

		if (title) {
			search.addAll(searchTitle(query));
		}
		if (author) {
			search.addAll(searchAuthor(query));
		}
		if (album) {
			search.addAll(searchAlbum(query));
		}

		return search;
	}

	/**
	 * Busqueda por titulo
	 * 
	 * @param title titulo a buscar
	 * 
	 * @return lista con resultados
	 */
	private ArrayList<PlayableObject> searchTitle(String title) {

		ArrayList<PlayableObject> search = new ArrayList<PlayableObject>();

		for (Song s : this.songList) {
			if (title.equals(s.getTitle()))
				search.add(s);
		}

		for (Album a : this.albumList) {
			if (title.equals(a.getTitle()))
				search.add(a);
		}

		return search;
	}

	/**
	 * Busqueda por autor
	 * 
	 * @param author autor a buscar
	 * 
	 * @return lista con resultados
	 */
	private ArrayList<PlayableObject> searchAuthor(String author) {

		ArrayList<PlayableObject> search = new ArrayList<PlayableObject>();

		for (Song s : this.songList) {
			if (author.equals(s.getAuthor().getName()))
				search.add(s);
		}

		for (Album a : this.albumList) {
			if (author.equals(a.getAuthor().getName()))
				search.add(a);
		}

		return search;
	}

	/**
	 * Busqueda por album
	 * 
	 * @param albumTitle album a buscar
	 * 
	 * @return lista con resultados
	 */
	private ArrayList<PlayableObject> searchAlbum(String albumTitle) {

		ArrayList<PlayableObject> search = new ArrayList<PlayableObject>();

		for (Album a : this.albumList) {
			if (albumTitle.equals(a.getTitle()))
				search.add(a);
		}

		return search;
	}

	/******************** Report related ******************/

	/**
	 * Anade un report a la lista
	 * 
	 * @param r report a anadir
	 * 
	 * @return ERROR si r es null
	 */
	public Status addReport(Report r) {

		if (r != null) {
			this.reportList.add(r);
			return Status.OK;
		}

		return Status.ERROR;
	}

	/**
	 * Elimina un report de la lista
	 * 
	 * @param r report a eliminar
	 * 
	 * @return ERROR si r es null
	 */
	public Status deleteReport(Report r) {

		if (r != null) {
			this.reportList.remove(r);
			return Status.OK;
		}

		return Status.ERROR;
	}

	/******************* SAVE STATE ***********************/

	/**
	 * Guarda los datos en un fichero System.bal
	 * 
	 * @throws IOException si hay problemas con el fichero
	 */
	public void saveData() throws IOException {
		String fileName = "System.bal";
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
		oos.close();
	}

	/******************* List getters *********************/

	/**
	 * Getter de la lista de usuarios
	 * @return ArrayList de usuarios
	 */
	public ArrayList<User> getUserList() {
		return userList;
	}

	/**
	 * Getter de la lista de canciones
	 * @return ArrayList de canciones
	 */
	public ArrayList<Song> getSongList() {
		return songList;
	}

	/**
	 * Getter de la lista de albumes
	 * @return ArrayList de albumes
	 */
	public ArrayList<Album> getAlbumList() {
		return albumList;
	}

	/**
	 * Getter de la lista de Playlists
	 * @return ArrayList de Playlists
	 */
	public ArrayList<Playlist> getPlaylistList() {
		return playlistList;
	}

	/**
	 * Getter de la lista de Reports
	 * @return ArrayList de Reports
	 */
	public ArrayList<Report> getReportList() {
		return reportList;
	}
}
