package padsof.interactions;

import padsof.playable.PlayableObject;
import padsof.user.User;

/**
 * Notificaciones de los usuarios a los que sigues
 * 
 * @author Equipo 1
 *
 */
public class Notification {

	/**
	 * Contenido de la notificacion
	 */
	private String notificationText;
	
	/**
	 * El receptor de la notificacion
	 */
	private User receiver;
	
	/**
	 * El objeto que ha lanzado la notificacion
	 */
	private PlayableObject trigger;
	
	/**
	 * Constructor de notificaciones
	 * 
	 * @param text Texto de la notificacion
	 * @param receiver Usuario que recibe la notificacion
	 * @param trigger PlayableObject que crea la notificacion
	 */
	public Notification(String text, User receiver, PlayableObject trigger) {
		notificationText = text;
		this.receiver = receiver;
		this.trigger = trigger;
	}
	
	/************************** Getters ***************************/
	
	/**
	 * Getter del texto de la notificacion
	 * 
	 * @return texto
	 */
	public String getNotificationText() {
		return notificationText;
	}

	/**
	 * Getter del receptor de la notificacion
	 * 
	 * @return user receptor
	 */
	public User getReceiver() {
		return receiver;
	}

	/**
	 * Getter del trigger
	 * 
	 * @return PlayableObject trigger
	 */
	public PlayableObject getTrigger() {
		return trigger;
	}
	
}
