package padsof.user;

import java.util.ArrayList;

import es.uam.eps.padsof.telecard.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import padsof.Status;
import padsof.interactions.Notification;
import padsof.system.System;

/**
 * Usuario del sistema, tanto registrados como premium como administradores 
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
public class User implements java.io.Serializable {

	/**
	 * Tipo de usuario, admin, registrado o estandar
	 */
	private UserType userType;

	/**
	 * Fecha de nacimiento del usuario
	 */
	private LocalDate birthDate;

	/**
	 * Pseudonimo del usuario
	 */
	private String nick;

	/**
	 * Nombre real del usuario
	 */
	private String name;

	/**
	 * Contrasenna del usuario en texto plano
	 */
	private String password;

	/**
	 * Boolean que indica si el usuario esta baneado o no
	 */
	private Boolean blocked;

	/**
	 * Numero de canciones que puede reproducir el usuario
	 */
	private long songCount;

	/**
	 * Lista de usuarios a los que sigue este usuario
	 */
	private ArrayList<User> follows;
	
	/**
	 * Lista de usuarios que sigen a este usuario
	 */
	private ArrayList<User> isFollowed;
	
	/**
	 * Lista de notificaciones
	 */
	private ArrayList<Notification> notifications;
	

	/**
	 * Fecha en que el usuario renovo el premium
	 */
	private LocalDate premiumDate;

	/**
	 * Fecha en que se registro el usuario
	 */
	private LocalDate registeredDate;

	/**
	 * Ultima tarjeta de credito usada por el usuario
	 */
	private String cardNumber;

	/**
	 * Numero de reproducciones de las canciones creadas por el usuario
	 */
	private long songsPlayCount;

	/**
	 * Constructor del usuario
	 * @param name nombre de usuario
	 * @param date fecha de nacimiento del usuario
	 * @param nick nick del usuario
	 * @param password contrasenna
	 */
	public User(String name, LocalDate date, String nick, String password) {
		
		this.userType = UserType.STANDARD;
		this.blocked = false;
		this.birthDate = date;
		this.name = name;
		this.nick = nick;
		this.password = password;
		if(!nick.contentEquals("admin"))
			this.songCount = System.getInstance().getMaxRegisteredSong();
		else
			this.songCount =  1000;
		this.follows = new ArrayList<User>();
		this.isFollowed = new ArrayList<User>();
		this.notifications = new ArrayList<Notification>();
		
		// Save date of registed
		this.registeredDate = LocalDate.now();
		
		this.songsPlayCount = 0;
		
	}

	/************************ SETTERS *****************************/
	/**
	 * Setter del user type
	 * @param userType nuevo tipo de usuario
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}	
	
	/**
	 * Setter de song count
	 * @param maxRegisteredSong numero de canciones
	 */
	public void setSongCount(long maxRegisteredSong) {
		this.songCount = maxRegisteredSong;
	}
	
	/************************* GETTERS *****************************/
	
	/**
	 * Getter de user type
	 * @return el tipo de usuario
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * Getter de fecha de nacimiento
	 * @return fecha de nacimiento
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}

	/**
	 * Getter del nick del usuario
	 * @return el nick del usuario
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * Getter del nombre real del usuario
	 * @return nombre real del usuario
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter de la contrasenna
	 * @return contrasenna
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Getter del estado de baneado
	 * @return true o false
	 */
	public Boolean getBlocked() {
		return blocked;
	}

	/**
	 * Getter de las canciones que te quedan
	 * @return numero de las canciones que te quedan por escuchar
	 */
	public long getSongCount() {
		return songCount;
	}

	/**
	 * Getter de la fecha en la que el usuario se convirtio en premium
	 * @return fecha de compra del premium
	 */
	public LocalDate getPremiumDate() {
		return premiumDate;
	}

	/**
	 * Getter de la ultima tarjeta de credito usada
	 * @return tarjeta de credito para pagos
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * Getter de la fecha en la que se creo el usuario
	 * @return fecha de creacion del usuario
	 */
	public LocalDate getRegisteredDate() {
		return registeredDate;
	}
	
	/******************* OTHER SETTERS *************************/
	
	/**
	 * Bloquea al usuario
	 */
	public void block() {
		this.blocked = true;
	}
	
	/**
	 * Desbloquea al usuario
	 */
	public void unblock() {
		this.blocked = false;
	}
	
	/** 
	 * Resta uno al numero de canciones restantes que puede reproducir el usuario
	 */
	public void increaseSongCount() {
		if(this.userType.equals(UserType.PREMIUM) || this.userType.equals(UserType.ADMIN))
			return;
		this.songCount--;
	}
	
	/**
	 * Sumar uno al numero de reproducciones que tiene un usuario dado en las canciones que ha subido
	 */
	public void increaseSongPlaycount() {
		this.songsPlayCount++;
		
		if(this.songsPlayCount >= System.getInstance().getPlaysToPremium())
			this.userType = UserType.PREMIUM;
			
	}
	
	/******************* LIST SETTERS *************************/
	
	/** 
	 * Sigue al usuario dado
	 * @param followee usuario a seguir
	 * @return status de la operacion
	 */
	public Status follow(User followee) {
		
		if(followee == null || this.follows.contains(followee)) {
			return Status.ERROR;
		}
		
		this.follows.add(followee);
		followee.isFollowed.add(this);
		
		return Status.OK;
	}

	/** 
	 * Deja de seguir al usuario dado
	 * @param followee usuario a dejar de seguir
	 * @return status de la operacion
	 */
	public Status unfollow(User followee) {
		
		if(followee == null || !this.follows.contains(followee)) {
			return Status.ERROR;
		}
		
		this.follows.remove(followee);
		followee.isFollowed.remove(this);
		
		return Status.OK;
	}
	
	/**
	 * Anade una notificacion para que el usuario pueda verla
	 * 
	 * @param n la notificacion
	 * 
	 * @return ERROR si la notificacion es null
	 */
	public Status notificate(Notification n) {
		if(n == null)
			return Status.ERROR;
		
		this.notifications.add(n);
		
		return Status.OK;
	}
	
	/***************** CHECKERS *******************/
	
	/**
	 * Comprueba que el usuario es mayor de 18
	 * @return true si es mayor y false si no
	 */
	public Boolean isOverEighteen() {
		
		 LocalDate hoy = LocalDate.now();
		
		if(hoy.isAfter(this.birthDate.plus(18,ChronoUnit.YEARS)))
			return true;
	
		return false;
	}
	/*************** PREMIUM *******************/
	
	/**
	 * Paga para que se convierta en premium el usuario
	 * @param cardNumber numero de la tarjeta de credito
	 * @return status de la operacion
	 */
	public Status goPremium(String cardNumber) {

		System sis = System.getInstance();
		
		if(!TeleChargeAndPaySystem.isValidCardNumber(cardNumber))
			return Status.ERROR;
		
		try {
			TeleChargeAndPaySystem.charge(cardNumber, "Mp3ball Subscription", sis.getPremiumPrice());
			// Save card number to recharge in 30 days
			this.cardNumber = cardNumber;
			// Update premium date
			this.premiumDate = LocalDate.now();
		}
		catch(OrderRejectedException e) {
			return Status.ERROR;
		}
		
		this.userType = UserType.PREMIUM;
		return Status.OK;
	}

	/**
	 * Guarda la fecha en la que se registro el usuario (o se renovo el registro)
	 * @param today nueva fecha (LocalData.now() probablemente)
	 */
	public void setRegisterdDate(LocalDate today) {
		this.registeredDate = today;
	}

	/**
	 * Getter del numero de reprucciones totales que tiene un usuario en sus canciones
	 * @return el numero de reproducciones de las canciones del usuario
	 */
	public long getSongPlaycount() {
		return this.songsPlayCount;
	}

	/**
	 * Getter de la lista de seguidores
	 * 
	 * @return ArrayList de seguidores
	 */
	public ArrayList<User> getIsFollowed() {
		return isFollowed;
	}

	/**
	 * Getter de la lista de notificaciones
	 * 
	 * @return Arraylist de notificaciones
	 */
	public ArrayList<Notification> getNotifications() {
		return notifications;
	}
	
	

}
