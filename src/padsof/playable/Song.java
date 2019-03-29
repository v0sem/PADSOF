package padsof.playable;

import padsof.Status;
import padsof.sistem.Sistem;
import padsof.user.User;

import java.io.FileNotFoundException;

import pads.musicPlayer.*;
import pads.musicPlayer.exceptions.*;

public class Song extends CommentableObject{

	private Boolean explicit;
	
	private SongState state;
	
	private String fileName;

	public Song (String title, String fileName) {
		// TODO: debemos permitir crear cancion sin que usr haya iniciado sesion? pondra autor a null
		super(title);
		
		if (Mp3Player.isValidMp3File(fileName) == false)
			java.lang.System.out.println("eres un poco burro tu"); // TODO: dis
		
		this.explicit = false;
		this.state = SongState.REVISION_PENDING;
		this.fileName = fileName;
	}

	@Override
	public Status play() {	
		if (this.canUserPlay() == false) {
			return Status.ERROR;
		}
		
		try {
			// TODO: you tell me
			Mp3Player poop = new Mp3Player(fileName);
			poop.play();
		} catch (FileNotFoundException | Mp3PlayerException e) {
			// notin
		}
		
		User u;
		if((u = Sistem.getInstance().getLoggedUser()) != null) {
			u.increaseSongCount();
		}
			
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
	protected Boolean canUserPlay() {
		// Check if song is in a playable state
		if (state != SongState.ACCEPTED) {
			return false;
		}
		
		if(Sistem.getInstance().getLoggedUser() != null) {
			// Check if user can play the song
			if (Sistem.getInstance().getLoggedUser().getSongCount() <= 0) {
				return false;
			}
			// Check if kids are listening
			if (explicit == true && !Sistem.getInstance().getLoggedUser().isOverEighteen()) {
				return false;
			}
		}
		else if (Sistem.getInstance().getAnonSongCount() <= 0  ||  explicit == true) {
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
}
