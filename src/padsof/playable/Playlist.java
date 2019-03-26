package padsof.playable;

import java.util.ArrayList;

import padsof.Status;

public class Playlist extends PlayableObject {

	private ArrayList<PlayableObject> playableObjectList;
	
	public Playlist(String title) {
		super(title);
	}

	@Override
	public Status play() {
		for (PlayableObject p : playableObjectList)	{
			if (p.play() == Status.ERROR) {
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
		
		for (PlayableObject p : playableObjectList)	{
			total += p.getLength();
		}
		
		return total;
	}
	
	public Status addPlayableObject(PlayableObject poo) {
		playableObjectList.add(poo);
		return Status.OK;
	}
	
	public Status deletePlayableObject(PlayableObject poo) {
		playableObjectList.remove(poo);
		return Status.OK;
	}
}
