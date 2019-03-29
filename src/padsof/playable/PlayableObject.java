package padsof.playable;

import padsof.Status;
import padsof.sistem.Sistem;
import padsof.user.User;

public abstract class PlayableObject implements java.io.Serializable {
	
	public PlayableObject(String title) {
		User u = Sistem.getInstance().getLoggedUser();
		if (u != null) {
			this.author = u;
		}

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
