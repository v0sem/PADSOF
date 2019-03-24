package padsof.system;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import padsof.Status;
import padsof.playable.Album;
import padsof.playable.PlayableObject;
import padsof.user.User;
import padsof.user.UserType;

public class System {

	private static System instance = null;
	
	private User adminUser = null;
	
	private User loggedUser = null;
	
	private User anonUser = new User("anon", LocalDate.now(), "anon", "1234");
	
	private static long anonSongCount;
	
	private ArrayList<User> userList;
	
	private ArrayList<PlayableObject> playableObjectList;
	
	private ArrayList<PlayableObject> searchResult;
	
	public System() {
		
		this.userList = new ArrayList<User>();
		this.playableObjectList = new ArrayList<PlayableObject>();
		this.searchResult = new ArrayList<PlayableObject>();
	}
	
	public static System getInstance() {
		
		instance = loadData();
		
		if(instance == null)
			instance = new System();
		
		return instance;
	}

	public static System loadData(){
		try {   
			String fileName= "System.bal";
			FileInputStream fin = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fin);
			System system = (System) ois.readObject();
			ois.close();
			return system;
		}
		catch(IOException | ClassNotFoundException r) {
			return null;
		}
	}
	/************************** Getters ***************************/
	
	public User getAdminUser() {
	
		return adminUser;
	}
	
	public User getLoggedUser() {
		
		return loggedUser;
	}
	
	public User getAnonUser() {
		
		return anonUser;
	}

	public Long getAnonSongCount() {
		
		return anonSongCount;
	}
	
	/*************************** Setters ***************************/
	
	public void setAnonSongCount(Long count) {
		
		anonSongCount = count;
	}

	/********************** Adding to Lists ************************/
	
	public Status addUser(User user) {
		if(user != null) {
			this.userList.add(user);
			return Status.OK;
		}
		
		return Status.ERROR;
	}
	
	public Status addPlayableObject(PlayableObject play) {
		
		if(play != null) {
			this.playableObjectList.add(play);
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

	public Status deletePlayableObject(PlayableObject play) {
		
		if(play != null) {
			this.playableObjectList.remove(play);
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
			if(userName.equals(u.getName()) && userPass.equals(u.getPassword())) {
				if(u.getUserType() == UserType.ADMIN)
					adminUser = u;
				else
					loggedUser = u;
				
				anonUser = null;
				return Status.OK;
			}
		}
		
		return Status.OK;
	}
	
	public Status logout(User user) {
		
		adminUser = null;
		loggedUser = null;
		
		try {
			saveData();
		} 
		catch(IOException e) {
			//TODO: Print there was a problem or smth
		}
		
		anonUser = new User("anon", LocalDate.now(), "anon", "1234");
		
		return Status.OK;
	}
	
	/************************* Song Related ********************/
	
	public Status search(String query, Boolean title, Boolean author, Boolean album) {
		
		Status flag = null;
		
		this.searchResult.clear();
		
		if(title) {
			this.searchResult.addAll(searchTitle(query));
		}
		if(author) {
			this.searchResult.addAll(searchAuthor(query));
		}
		if(album) {
			this.searchResult.addAll(searchAlbum(query));
		}
		
		return flag;
	}
	
	private ArrayList<PlayableObject> searchTitle(String songTitle) {
		
		ArrayList<PlayableObject> search = new ArrayList<PlayableObject>();
		
		for(PlayableObject play : this.playableObjectList ) {
			if(songTitle.equals(play.getTitle()))
				search.add(play);
		}
		
		return search;
	}
	
	private ArrayList<PlayableObject> searchAuthor(String songAuthor) {
		
		ArrayList<PlayableObject> search = new ArrayList<PlayableObject>();
		
		for(PlayableObject play : this.playableObjectList ) {
			if(songAuthor.equals(play.getAuthor().getName()))
				search.add(play);
		}
		
		return search;
	}
	
	private ArrayList<PlayableObject> searchAlbum(String songAlbum) {
		
		ArrayList<PlayableObject> search = new ArrayList<PlayableObject>();
		
		for(PlayableObject play : this.playableObjectList ) {
			if(play.getClass() == Album.class && 
			songAlbum.equals(play.getTitle()))
				search.add(play);
		}
		
		return search;
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
