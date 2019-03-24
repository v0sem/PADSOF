package padsof.system;

import java.util.ArrayList;
import java.util.Date;

import padsof.Status;
import padsof.playable.PlayableObject;
import padsof.user.User;

public class System {

	private static System instance = null;
	
	private User adminUser = null;
	
	private User loggedUser = null;
	
	private static long anonSongCount;
	
	private ArrayList<User> userList;
	
	private ArrayList<PlayableObject> playableObjectList;
	
	public System() {
		
	}
	
	public static System getInstance() {
		if(instance == null)
			instance = new System();
		
		return instance;
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
	
	/************************ User related *********************/
	
	public Status register(String userName, String userNick, Date date, String userPass) {
		
		return Status.OK;
	}
	
	public Status login(String userName, String userPass) {
		
		return Status.OK;
	}
	
	public Status logout(User user) {
		
		return Status.OK;
	}
	
	/************************* Song Related ********************/
	
	public Status search(String query, Boolean title, Boolean author, Boolean album) {
		
		Status flag = null;
		
		if(title) {
			flag = searchTitle(query);
		}
		if(author) {
			flag = searchAuthor(query);
		}
		if(album) {
			flag = searchAlbum(query);
		}
		
		return flag;
	}
	
	private Status searchTitle(String songTitle) {
		
		return Status.OK;
	}
	
	private Status searchAuthor(String songAuthor) {
		
		return Status.OK;
	}
	
	private Status searchAlbum(String songAlbum) {
		
		return Status.OK;
	}
	
}
