package padsof.sistem;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;

import padsof.Status;
import padsof.interactions.Report;
import padsof.playable.Album;
import padsof.playable.PlayableObject;
import padsof.playable.Playlist;
import padsof.playable.Song;
import padsof.user.User;
import padsof.user.UserType;

public class Sistem implements java.io.Serializable {
	
	private static Sistem instance = null;
	
	private User adminUser = null;
	
	private User loggedUser = null; //mientras este a null, estamos en anon
	
	private long anonSongCount;
	
	private ArrayList<User> userList;
	
	private ArrayList<Song> songList;
	
	private ArrayList<Album> albumList;
	
	private ArrayList<Playlist> playlistList;
	
	private ArrayList<Report> reportList;
	
	private LocalDate songCountDate;
	
	private double premiumPrice;
	
	private long maxAnonSong;
	
	private long maxRegisteredSong;
	
	private long playsToPremium;
	
	public Sistem() {
	
		//si existe un archivo para cargarlo, lo carga
		if (new File("System.bal").exists()) {
			this.loadData();
		}
		else {
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
	
	public static Sistem getInstance() {
		
		if(instance == null)
			instance = new Sistem();
		
		return instance;
	}

	public Sistem loadData(){
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
			
			ois.close();
			return loadedSystem;
		}
		catch(IOException | ClassNotFoundException r) {
			return null;
		}
	}
	
	public void checkDate() {
		LocalDate today = LocalDate.now();
		Period period;
		int diff;
		
		// Check songs reported and delete dem
		for(Song s : this.songList) {
			period = Period.between(today, s.getRejectedDate());
	    	diff = period.getDays();
	    	
	    	if (diff >= 3)
	    		this.songList.remove(s);
		}
		
		// Check anon song counts
		period = Period.between(today, this.songCountDate);
    	diff = period.getDays();
    	
    	if (diff >= 30) {
    		this.anonSongCount = maxAnonSong;
    	}
    	
    	// Check registered song counts
    	period = Period.between(today, this.loggedUser.getRegisteredDate());
    	diff = period.getDays();
    	
    	if (diff >= 30) {
    		// reset date
    		this.loggedUser.setRegisterdDate(today);
    		// reset count
    		this.loggedUser.setSongCount(this.maxRegisteredSong);
    	}
		
		// Check if premium users have to pay again
		if (this.loggedUser.getUserType() == UserType.PREMIUM)
		{
			// User is premium, check for payment
			period = Period.between(today, this.loggedUser.getPremiumDate());
	    	diff = period.getDays();
			
	    	if (diff >= 30) {
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
	
	public User getAdminUser() {
	
		return adminUser;
	}
	
	public User getLoggedUser() {
		
		return loggedUser;
	}

	public Long getAnonSongCount() {
		
		return anonSongCount;
	}
	
	public double getPremiumPrice() {
		return premiumPrice;
	}
	
	public long getMaxAnonSong() {
		return maxAnonSong;
	}

	public long getMaxRegisteredSong() {
		return maxRegisteredSong;
	}
	
	public long getPlaysToPremium() {
		return playsToPremium;
	}
	
	/*************************** Setters ***************************/
	
	public void increaseAnonSongCount() {
		
		anonSongCount--;
	}
	
	public void setMaxAnonSong(long count) {
		
		maxAnonSong = count;
	}

	public void setPremiumPrice(double premiumPrice) {
		this.premiumPrice = premiumPrice;
	}

	public void setMaxRegisteredSong(long maxRegisteredSong) {
		this.maxRegisteredSong = maxRegisteredSong;
	}


	public void setPlaysToPremium(long playsToPremium) {
		this.playsToPremium = playsToPremium;
	}

	/********************** Adding to Lists ************************/
	
	public Status addUser(User user) {
		if(user != null) {
			this.userList.add(user);
			return Status.OK;
		}
		
		return Status.ERROR;
	}
	
	public Status addSong(Song s) {
		
		if(s != null) {
			this.songList.add(s);
			return Status.OK;
		}
		
		return Status.ERROR;
	}
	
	public Status addAlbum(Album a) {
		
		if(a != null) {
			this.albumList.add(a);
			return Status.OK;
		}
		
		return Status.ERROR;
	}
	
	public Status addPlaylist(Playlist pl) {
		
		if(pl != null) {
			this.playlistList.add(pl);
			return Status.OK;
		}
		
		return Status.ERROR;
	}
	
	/******************* Removing from lists *****************/
	
	public Status deleteUser(User user) {
		
		if(user != null) {
			this.userList.remove(user);
			return Status.OK;
		}
		
		return Status.ERROR;
	}

	public Status deleteSong(Song s) {
		
		if(s != null) {
			this.songList.remove(s);
			return Status.OK;
		}
		
		return Status.ERROR;
	}
	
	public Status deleteAlbum(Album a) {
		
		if(a != null) {
			this.albumList.remove(a);
			return Status.OK;
		}
		
		return Status.ERROR;
	}

	public Status deletePlaylist(Playlist pl) {
		
		if(pl != null) {
			this.playlistList.remove(pl);
			return Status.OK;
		}
		
		return Status.ERROR;
	}
	
	/******************** Getting from lists *******************/
	
	private Boolean userNickExists(String userNick) {
		
		for(User user : this.userList) {
			if(userNick.equals(user.getNick())) {
				return true;
			}
		}
		
		return false;
	}
	
	private Boolean userNameExists(String userName) {
		if (this.userList == null)
			System.out.println("user list esta a nul"); // #DEBUG
		for(User user : this.userList) {
			if(userName.equals(user.getName())) {
				return true;
			}
		}
		
		return false;
	}
	
	/************************ User related *********************/
	
	public Status register(String userName, String userNick, LocalDate date, String userPass) {
		
		if(userNameExists(userName) || userNickExists(userNick))
			return Status.ERROR;
		
		this.userList.add(new User(userName, date, userNick, userPass));
		
		return Status.OK;
	}
	
	public Status login(String userName, String userPass) {
		
		for(User u : userList) {
			if(userName.equals(u.getNick()) && userPass.equals(u.getPassword())) {
				if(u.getUserType() == UserType.ADMIN) {
					adminUser = u;	
				}
				loggedUser = u;  //Si es admin tambien le loggea

				return Status.OK;
			}
		}
		
		return Status.ERROR;
	}
	
	public Status logout() {
		
		loggedUser = null;
		
		try {
			saveData();
		} 
		catch(IOException e) {
			return Status.ERROR;
		}
		
		return Status.OK;
	}
	
	public boolean adminIsLogged() {
		
		if (loggedUser == adminUser)
			return true;
		return false;
	}
	
	/************************* Song Related ********************/
	
	public ArrayList<PlayableObject> search(String query, Boolean title, Boolean author, Boolean album) {
		
		ArrayList<PlayableObject> search = new ArrayList<PlayableObject>();
		
		if(title) {
			search.addAll(searchTitle(query));
		}
		if(author) {
			search.addAll(searchAuthor(query));
		}
		if(album) {
			search.addAll(searchAlbum(query));
		}
		
		return search;
	}
	
	private ArrayList<PlayableObject> searchTitle(String title) {
		
		ArrayList<PlayableObject> search = new ArrayList<PlayableObject>();
		
		for(Song s : this.songList) {
			if(title.equals(s.getTitle()))
				search.add(s);
		}
		
		for(Album a : this.albumList) {
			if(title.equals(a.getTitle()))
				search.add(a);
		}
		
		return search;
	}
	
	private ArrayList<PlayableObject> searchAuthor(String author) {
		
		ArrayList<PlayableObject> search = new ArrayList<PlayableObject>();
		
		for(Song s : this.songList) {
			if(author.equals(s.getAuthor().getName()))
				search.add(s);
		}
		
		for(Album a : this.albumList) {
			if(author.equals(a.getAuthor().getName()))
				search.add(a);
		}
		
		return search;
	}
	
	private ArrayList<PlayableObject> searchAlbum(String albumTitle) {
		
		ArrayList<PlayableObject> search = new ArrayList<PlayableObject>();
				
		for(Album a : this.albumList) {
			if(albumTitle.equals(a.getTitle()))
				search.add(a);
		}
		
		return search;
	}
	

	/******************** Report related ******************/
	public Status addReport(Report r) {

		if(r != null) {
			this.reportList.add(r);
			return Status.OK;
		}

		return Status.ERROR;
	}

	public Status deleteReport(Report r) {

		if(r != null) {
			this.reportList.remove(r);
			return Status.OK;
		}

		return Status.ERROR;
	}

	/******************* SAVE STATE ***********************/
	
	public void saveData()throws IOException{
	    String fileName= "System.bal";
	    FileOutputStream fos = new FileOutputStream(fileName);
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(this);
	    oos.close();
	}
}
