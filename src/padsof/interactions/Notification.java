package padsof.interactions;

import padsof.playable.PlayableObject;
import padsof.user.User;

public class Notification {

	private String notificationText;
	
	private User receiver;
	
	private PlayableObject trigger;
	
	public Notification(String text, User receiver, PlayableObject trigger) {
		notificationText = text;
		this.receiver = receiver;
		this.trigger = trigger;
	}
	
	/************************** Getters ***************************/
	
	public String getNotificationText() {
		return notificationText;
	}

	public User getReceiver() {
		return receiver;
	}

	public PlayableObject getTrigger() {
		return trigger;
	}
	
}
