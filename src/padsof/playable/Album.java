package padsof.playable;

import java.util.ArrayList;

import padsof.Status;
import padsof.sistem.Sistem;

public class Album extends CommentableObject{
	
	private int year;
	
	private ArrayList<Song> songList;

	@Override
	public Status play() {
		for (Song s : songList)	{
			if (s.play() == Status.ERROR) {
				continue;
			}
		}
		return Status.OK;
	}

	@Override
	protected Boolean canUserPlay() {
		if(Sistem.getInstance().getLoggedUser() == null)
			return false;
		
		return true;
	}

	@Override
	public Float getLength() {
		float total = 0;
		
		for (Song s : songList)	{
			total += s.getLength();
		}
		
		return total;
	}
	
	public Status addSong(Song poo) {
		if(poo != null) {
			songList.add(poo);
			return Status.OK;
		}
		
		return Status.ERROR;
	}
	
	public Status deleteSong(Song poo) {
		if(poo != null) {
			if(songList.remove(poo));
				return Status.OK;
		}
		
		return Status.ERROR;
	}

	public int getYear() {
		return year;
	}

	public Album(String title, int year) {
		super(title);
		this.year = year;
	}
}
