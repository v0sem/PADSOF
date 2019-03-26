package padsof.playable;

import java.util.ArrayList;

import padsof.Status;

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
		songList.add(poo);
		return Status.OK;
	}
	
	public Status deleteSong(Song poo) {
		songList.remove(poo);
		return Status.OK;
	}

	public int getYear() {
		return year;
	}

	public Album(String title, int year) {
		super(title);
		this.year = year;
	}
}
