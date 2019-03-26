package padsof.playable;

import java.util.ArrayList;

import padsof.Status;
import padsof.user.User;

public class Playlist extends PlayableObject {

	private ArrayList<PlayableObject> playableObjectList;

	@Override
	public Status play(User u) {
		for (PlayableObject p : playableObjectList)	{
			if (p.play(u) == Status.ERROR) {
				continue;
			}
		}
		return Status.OK;
	}

	@Override
	protected Boolean canUserPlay(User u) {
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

	public Playlist(User author, String title) {
		super(author, title);
	}
}
