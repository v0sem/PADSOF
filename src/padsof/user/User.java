package padsof.user;

import java.util.ArrayList;

import es.uam.eps.padsof.telecard.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import padsof.Status;

public class User implements java.io.Serializable {

	private UserType userType;
	
	private LocalDate birthDate;
	
	private String nick;
	
	private String name;
	
	private String password;
	
	private Boolean blocked;
	
	private int songCount;
	
	private ArrayList<User> follows;
	
	private LocalDate premiumDate;
	
	private LocalDate registeredDate;

	private String cardNumber;

	public User(String name, LocalDate date, String nick, String password) {
		
		this.userType = UserType.STANDARD;
		this.blocked = false;
		this.birthDate = date;
		this.name = name;
		this.nick = nick;
		this.password = password;
		this.songCount = 0;
		
		// Save date of registed
		this.registeredDate = LocalDate.now();
	}

	/************************ SETTERS *****************************/
	
	public void setUserType(UserType userType) {
		this.userType = userType;
	}	
	
	public void setSongCount(int songCount) {
		this.songCount = songCount;
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
	
	public LocalDate getPremiumDate() {
		return premiumDate;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}

	public LocalDate getRegisteredDate() {
		return registeredDate;
	}
	
	/******************* OTHER GETTERS *************************/
	
	public void block() {
		this.blocked = true;
	}
	
	public void unblock() {
		this.blocked = false;
	}
	
	public void increaseSongCount() {
		this.songCount--;
	}
	
	/******************* OTHER SETTERS *************************/
	
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
			// Save card number to recharge in 30 days
			this.cardNumber = cardNumber;
			// Update premium date
			this.premiumDate = LocalDate.now();
		}
		catch(OrderRejectedException e) {
			return Status.ERROR;
		}
		
		return Status.OK;
	}

	public void setRegisterdDate(LocalDate today) {
		this.registeredDate = today;
	}
}
