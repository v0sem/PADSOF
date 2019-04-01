/**
 * Esta clase define listas privadas de canciones. No es lo mismo que album.
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 */

package padsof.playable;

import java.util.ArrayList;

import padsof.Status;

public class Playlist extends PlayableObject {

	/**
	 * Constructor de playlist
	 * 
	 * @param title titulo del playlist
	 * @return objeto creado
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
		for (PlayableObject p : playableObjectList) {
			if (p.play() == Status.ERROR) {
				continue;
			}
		}
		return Status.OK;
	}

	/**
	 * Comprueba si el usuario logeado puede reproducir la playlist
	 * 
	 * @return boolean de la operacion
	 */
	@Override
	protected Boolean canUserPlay() {
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
}
