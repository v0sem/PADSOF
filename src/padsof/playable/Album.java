/**
 * Esta clase se encarga de administrar los albumes
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 */

package padsof.playable;

import java.util.ArrayList;

import padsof.Status;
import padsof.sistem.Sistem;

public class Album extends CommentableObject{
	
	/**
	 * Anno de creacion del album
	 */
	private int year;
	
	/**
	 * Lista de canciones que forman parte de un album
	 */	
	private ArrayList<Song> songList;
	
	/**
	 * Constructor del album
	 * @param title titulo del album
	 * @param year time of creation
	 * @return objeto creado
	 */
	public Album(String title, int year) {
		super(title);
		this.year = year;
	}
	
	/**
	 * Permite reproducir un album
	 * @return status de la operacion
	 */
	@Override
	public Status play() {
		for (Song s : songList)	{
			if (s.play() == Status.ERROR) {
				continue;
			}
		}
		return Status.OK;
	}

	/**
	 * Comprueba si el usuario logeado puede reproducir el album
	 * @return boolean de la operacion
	 */
	@Override
	protected Boolean canUserPlay() {
		if(Sistem.getInstance().getLoggedUser() == null)
			return false;
		
		return true;
	}

	/**
	 * Calcula la longitud del album
	 * @return float con el tiempo total
	 */
	@Override
	public Float getLength() {
		float total = 0;
		
		for (Song s : songList)	{
			total += s.getLength();
		}
		
		return total;
	}

	/**
	 * Permite agregar nuevas canciones a un album
	 * @param s cancion nueva
	 * @return status de la operacion
	 */
	public Status addSong(Song s) {
		songList.add(s);
		return Status.OK;
	}

	/**
	 * Permite borrar canciones de un album
	 * @param s cancion que queremos borrar
	 * @return status de la operacion
	 */
	public Status deleteSong(Song s) {
		songList.remove(s);
		return Status.OK;
	}

	/**
	 * Nos devuelve Year 
	 * @return year of creation
	 */
	public int getYear() {
		return year;
	}
}
