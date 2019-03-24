package padsof.playable;

import padsof.Status;
import padsof.user.User;

public abstract class PlayableObject {
	
	private User author;
	
	private String title;

	public User getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public abstract Status play(User u);
	
	protected abstract Boolean canUserPlay(User u);
	
	public abstract Float getLength();

}
