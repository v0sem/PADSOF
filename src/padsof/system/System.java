package padsof.system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import padsof.playable.PlayableObject;
import padsof.user.User;
import padsof.user.UserType;

public class System {

	private User adminUser;
	
	private User anonUser;
	
	private ArrayList<User> userList;
	
	private ArrayList<PlayableObject> playableObjectList;
	
	public System() throws ParseException{
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			
		this.adminUser = new User("admin", format.parse("01/01/1000"), "admin", "admin");
		adminUser.setUserType(UserType.ADMIN);
		this.anonUser = new User("anon", format.parse("01/01/1000"), "anon", "");	
		adminUser.setUserType(UserType.ANON);
		
	}

	public User getAdminUser() {
		return adminUser;
	}

	public User getAnonUser() {
		return anonUser;
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	public ArrayList<PlayableObject> getPlayableObjectList() {
		return playableObjectList;
	}

	public void setPlayableObjectList(ArrayList<PlayableObject> playableObjectList) {
		this.playableObjectList = playableObjectList;
	}
}
