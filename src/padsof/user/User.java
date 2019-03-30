package padsof.user;

import java.util.ArrayList;

import es.uam.eps.padsof.telecard.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import padsof.Status;
import padsof.sistem.Sistem;

public class User implements java.io.Serializable {

	private UserType userType;
	
	private LocalDate birthDate;
	
	private String nick;
	
	private String name;
	
	private String password;
	
	private Boolean blocked;
	
	private int songCount;
	
	private ArrayList<User> follows;
	
	private long songsPlayCount;

	public User(String name, LocalDate date, String nick, String password) {
		
		this.userType = UserType.STANDARD;
		this.blocked = false;
		this.birthDate = date;
		this.name = name;
		this.nick = nick;
		this.password = password;
		this.songCount = 0;
		this.songsPlayCount = 0;
	}

	/************************ SETTERS *****************************/
	
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	/************************* GETTERS *****************************/
	
	public UserType getUserType() {
		return userType;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}

	public String getNick() {
		return nick;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Boolean getBlocked() {
		return blocked;
	}

	public int getSongCount() {
		return songCount;
	}
	
	/******************* OTHER SETTERS *************************/
	
	public void block() {
		this.blocked = true;
	}
	
	public void unblock() {
		this.blocked = false;
	}
	
	public void increaseSongCount() {
		this.songCount--;
	}
	
	public void increaseSongPlaycount() {
		this.songsPlayCount++;
		
		if(this.songsPlayCount >= Sistem.getInstance().getPlaysToPremium())
			this.userType = UserType.PREMIUM;
			
	}
	
	/******************* LIST SETTERS *************************/
	
	public Status follow(User followee) {
		
		if(followee == null || this.follows.contains(followee)) {
			return Status.ERROR;
		}
		
		this.follows.add(followee);
		
		return Status.OK;
	}
	
	public Status unfollow(User followee) {
		
		if(followee == null || !this.follows.contains(followee)) {
			return Status.ERROR;
		}
		
		this.follows.remove(followee);
		
		return Status.OK;
	}
	
	/***************** CHECKERS *******************/
	
	public Boolean isOverEighteen() {
		
		 LocalDate hoy = LocalDate.now();
		
		if(hoy.isBefore(this.birthDate.plus(18,ChronoUnit.YEARS)))
			return true;
	
		return false;
	}
	/*************** PREMIUM *******************/
	public Status goPremium(String cardNumber, Double amount) {

		if(!TeleChargeAndPaySystem.isValidCardNumber(cardNumber))
			return Status.ERROR;
		
		try {
			TeleChargeAndPaySystem.charge(cardNumber, "Mp3ball Subscription", amount);
		}
		catch(OrderRejectedException e) {
			return Status.ERROR;
		}
		
		this.userType = UserType.PREMIUM;
		return Status.OK;
	}
}
