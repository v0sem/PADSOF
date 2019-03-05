package padsof.user;

import java.util.ArrayList;
import java.util.Date;

public class User {

	private UserType userType;
	
	private Date birthDate;
	
	private String nick;
	
	private String name;
	
	private String password;
	
	private Boolean blocked;
	
	private int songCount;
	
	private ArrayList<User> follows;

	public User(String name, Date date, String nick, String password) {
		
		this.userType = UserType.STANDARD;
		this.blocked = false;
		this.birthDate = date;
		this.name = name;
		this.nick = nick;
		this.password = password;
		this.songCount = 0;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}

	public int getSongCount() {
		return songCount;
	}

	public void setSongCount(int songCount) {
		this.songCount = songCount;
	}

	public ArrayList<User> getFollows() {
		return follows;
	}

	public void setFollows(ArrayList<User> follows) {
		this.follows = follows;
	}
	
	
}
