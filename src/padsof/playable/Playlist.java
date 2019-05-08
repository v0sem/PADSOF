/**
 * Esta clase define listas privadas de canciones. No es lo mismo que album.
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 */

package padsof.playable;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3PlayerException;
import padsof.Status;
import padsof.system.System;
import padsof.user.User;
import padsof.user.UserType;

@SuppressWarnings("serial")
public class Playlist extends PlayableObject {

	/**
	 * Constructor de playlist
	 * 
	 * @param title titulo del playlist
	 */
	public Playlist(String title) {
		super(title);
		this.playableObjectList = new ArrayList<PlayableObject>();
	}

	/**
	 * Lista de canciones u otros objectos reproducibles que forman parte de esta
	 * playlist
	 */
	private ArrayList<PlayableObject> playableObjectList;

	/**
	 * Permite reproducir una playlist
	 * 
	 * @return status de la operacion
	 */
	@Override
	public Status play() {
		if (this.canUserPlay() == false) {
			return Status.ERROR;
		}
		
		if(songPlayer != null)
			songPlayer.stop();
		// Remove one from song count of the logged user (unless admin or premium)
		User u = System.getInstance().getLoggedUser();
		if (u != null) {
			if (u.getUserType() == UserType.STANDARD)
				u.increaseSongCount();
		} else {
			System.getInstance().increaseAnonSongCount();
		}

		// Add one to the plays of the author
		this.getAuthor().increaseSongPlaycount();
	
		List<String> songs =  new ArrayList<>();
		
		for(PlayableObject s: playableObjectList) {
			if(s.getClass() == Song.class)
				songs.add(((Song) s).getFileName());
			if(s.getClass() == Album.class)
				songs.addAll(((Album) s).getSongList());
		}
		
		// Try to play it
		try {
			Mp3Player player = new Mp3Player();
			player.add(songs.toArray(new String[songs.size()]));
			this.songPlayer = player;
			System.getInstance().setSongPlayer(songPlayer);
			player.play();
		} catch (FileNotFoundException | Mp3PlayerException e) {
			java.lang.System.out.println("[ERROR] Error playing the Album");
		}

		return Status.OK;
	}

	/**
	 * Comprueba si el usuario logeado puede reproducir la playlist
	 * 
	 * @return boolean de la operacion
	 */
	@Override
	public Boolean canUserPlay() {
		return true;
	}

	/**
	 * Calcula la longitud del playlist
	 * 
	 * @return float con el tiempo total
	 */
	@Override
	public Float getLength() {
		float total = 0;

		for (PlayableObject p : playableObjectList) {
			total += p.getLength();
		}

		return total;
	}

	/**
	 * Permite agregar nuevas canciones a una playlist
	 * 
	 * @param s cancion nueva
	 * @return status de la operacion
	 */
	public Status addPlayableObject(PlayableObject s) {
		if (s != null) {
			playableObjectList.add(s);
			return Status.OK;
		}

		return Status.ERROR;
	}

	/**
	 * Permite borrar canciones de una playlist
	 * 
	 * @param s cancion que queremos borrar
	 * @return status de la operacion
	 */
	public Status deletePlayableObject(PlayableObject s) {
		if (s != null) {
			if (playableObjectList.remove(s))
				;
			return Status.OK;
		}

		return Status.ERROR;
	}
	
	public ArrayList<PlayableObject> getList() {
		return this.playableObjectList;
	}
}
