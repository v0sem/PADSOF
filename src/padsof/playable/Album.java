/**
 * Esta clase se encarga de administrar los albumes
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 */

package padsof.playable;

import java.util.ArrayList;

import padsof.Status;
import padsof.system.System;

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
		for (Song s : songList) {
			if (s.play() == Status.ERROR) {
				continue;
			}
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
}
