package padsof.playable;

import padsof.Status;
import padsof.user.User;

import java.io.FileNotFoundException;

import pads.musicPlayer.*;
import pads.musicPlayer.exceptions.Mp3PlayerException;

public class Song extends CommentableObject{

	private Boolean explicit;
	
	private SongState state;
	
	private String fileName;
	
	//TODO: Change dis please
	private Exception myWigglyDick;

	@Override
	public Status play(User u) {	
		if (this.canUserPlay(u) == false) {
			return Status.ERROR;
		}
		
		try {
			// TODO: you tell me
			Mp3Player poop = new Mp3Player(fileName);
			poop.play();
		} catch (FileNotFoundException | Mp3PlayerException e) {
			// notin
		}
		
		u.increaseSongCount();
		
		return Status.OK;
	}
	
	public Status report(User u) {
		this.setState(SongState.REPORTED);
		return Status.OK;
	}

	public Boolean getExplicit() {
		return explicit;
	}

	public void setExplicit(Boolean explicit) {
		this.explicit = explicit;
	}

	public SongState getState() {
		return state;
	}

	public void setState(SongState state) {
		this.state = state;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	protected Boolean canUserPlay(User u) {
		// Check if song is in a playable state
		if (state != SongState.ACCEPTED) {
			return false;
		}
		
		// Check if user can play the song
		if (u.getSongCount() <= 0) {
			return false;
		}
		
		// Check if kids are listening
		if (explicit == true && !u.isOverEighteen()) {
			return false;
		}
		
		return true;
	}

	@Override
	public Float getLength() {
		try {
			return (float) Mp3Player.getDuration(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public Song (User author, String title, String fileName) throws Exception {
		super(author, title);
		
		if (Mp3Player.isValidMp3File(fileName) == false)
		{
			throw myWigglyDick;
		}
		
		this.explicit = false;
		this.state = SongState.REVISION_PENDING;
		this.fileName = fileName;
	}
}
