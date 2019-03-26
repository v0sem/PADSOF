package padsof.playable;

import padsof.Status;
import padsof.user.User;
import padsof.system.System;

public abstract class PlayableObject {
	
	public PlayableObject(String title) {
		this.author = System.getInstance().getLoggedUser();
		this.title = title;
	}

	private User author;
	
	private String title;

	public User getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public abstract Status play();
	
	protected abstract Boolean canUserPlay();
	
	public abstract Float getLength();

}
