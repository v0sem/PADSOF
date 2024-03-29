/**
 * Esta clase engloba los objectos que se pueden escuchar con un player
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 */

package padsof.playable;

import pads.musicPlayer.Mp3Player;
import padsof.Status;
import padsof.system.System;
import padsof.user.User;

@SuppressWarnings("serial")
public abstract class PlayableObject implements java.io.Serializable {

	/**
	 * Guarda el player de esta cancion
	 */
	protected transient Mp3Player songPlayer;
	
	/**
	 * Constructor de playable object
	 * 
	 * @param title titulo del playable object
	 */
	public PlayableObject(String title) {
		User u = System.getInstance().getLoggedUser();
		if (u == null) {
			java.lang.System.out.println("[INFO] No user logged in, defaulting to admin user");
			this.author = System.getInstance().getAdminUser();
		} else {
			this.author = u;
		}

		this.title = title;
	}

	/**
	 * Autor del playableObject
	 */
	private User author;

	/**
	 * Titulo del playable object
	 */
	private String title;

	/**
	 * Getter del autor
	 * 
	 * @return objeto creado
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * Getter del titulo
	 * 
	 * @return objeto creado
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter del titulo
	 * 
	 * @param title titulo del objeto
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Permite reproducir un album
	 * 
	 * @return status de la operacion
	 */
	public abstract Status play();

	/**
	 * Te permite comprobar si puedes reproducir el objeto
	 * 
	 * @return boolean con el resultado
	 */
	public abstract Boolean canUserPlay();

	/**
	 * Devuleve la longitud del objeto playable
	 * 
	 * @return float con la longitud
	 */
	public abstract Float getLength();

	@Override
	public String toString() {
		return "[" + author.getName() + ", " + title + "]";
	}

}
