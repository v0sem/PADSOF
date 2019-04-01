/**
 * Esta clase engloba los objectos que se pueden escuchar con un player
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 */

package padsof.playable;

import padsof.Status;
import padsof.sistem.Sistem;
import padsof.user.User;

public abstract class PlayableObject implements java.io.Serializable {

	/**
	 * Constructor de playable object
	 * 
	 * @param title titulo del playable object
	 * @return objeto creado
	 */
	public PlayableObject(String title) {
		User u = Sistem.getInstance().getLoggedUser();
		if (u == null) {
			System.out.println("No user logged in, defaulting to admin user");
			this.author = Sistem.getInstance().getAdminUser();
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
	protected abstract Boolean canUserPlay();

	/**
	 * Devuleve la longitud del objeto playable
	 * 
	 * @return float con la longitud
	 */
	public abstract Float getLength();

}
