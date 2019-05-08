/**
 * Esta clase se encarga de administrar los albumes
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
public class Album extends CommentableObject {

	/**
	 * Constructor del album
	 * 
	 * @param title titulo del album
	 * @param year  time of creation
	 */
	public Album(String title, int year) {
		super(title);
		this.year = year;
		this.songList = new ArrayList<Song>();
	}

	/**
	 * Anno de creacion del album
	 */
	private int year;

	/**
	 * Lista de canciones que forman parte de un album
	 */
	private ArrayList<Song> songList;

	/**
	 * Permite reproducir un album
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
	
		List<String> songs =  getSongList();
		// Try to play it
		try {
			Mp3Player player = new Mp3Player();
			player.add((String[]) songs.toArray());
			this.songPlayer = player;
			System.getInstance().setSongPlayer(songPlayer);
			player.play();
		} catch (FileNotFoundException | Mp3PlayerException e) {
			java.lang.System.out.println("[ERROR] Error playing the Album");
		}

		return Status.OK;
	}
	

	/**
	 * Comprueba si el usuario logeado puede reproducir el album
	 * 
	 * @return boolean de la operacion
	 */
	@Override
	public Boolean canUserPlay() {
		if (System.getInstance().getLoggedUser() == null)
			return false;

		return true;
	}

	/**
	 * Calcula la longitud del album
	 * 
	 * @return float con el tiempo total
	 */
	@Override
	public Float getLength() {
		float total = 0;

		for (Song s : songList) {
			total += s.getLength();
		}

		return total;
	}

	/**
	 * Permite agregar nuevas canciones a un album
	 * 
	 * @param s cancion nueva
	 * @return status de la operacion
	 */
	public Status addSong(Song s) {
		if (s != null) {
			songList.add(s);
			return Status.OK;
		}

		return Status.ERROR;
	}

	/**
	 * Permite borrar canciones de un album
	 * 
	 * @param s cancion que queremos borrar
	 * @return status de la operacion
	 */
	public Status deleteSong(Song s) {
		if (s != null) {
			songList.remove(s);
			return Status.OK;
		}

		return Status.ERROR;
	}

	/**
	 * Nos devuelve Year
	 * 
	 * @return year of creation
	 */
	public int getYear() {
		return year;
	}


	public List<String> getSongList() {
		List<String> result = new ArrayList<String>();
		
		for(Song s: songList) {
			result.add(s.getFileName());
		}
		return result;
	}
}
