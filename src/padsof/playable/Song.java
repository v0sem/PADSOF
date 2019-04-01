/**
 * Esta clase es la unidad minima playable
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 */

package padsof.playable;

import padsof.Status;
import padsof.interactions.Report;
import padsof.sistem.Sistem;
import padsof.user.User;
import padsof.user.UserType;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import pads.musicPlayer.*;
import pads.musicPlayer.exceptions.*;

public class Song extends CommentableObject {
	/**
	 * Guarda el player de esta cancion
	 */
	private Mp3Player songPlayer;
	
	/**
	 * Guarda si la cancion tiene contenido explicito o no
	 */
	private Boolean explicit;

	/**
	 * Guarda el estado de la cancion (REVISION_PENDING, ACCEPTED, REJECTED,
	 * REPORTED)
	 */
	private SongState state;

	/**
	 * Path del MP3 de la cancion
	 */
	private String fileName;

	/**
	 * Aqui se guarda la fecha en la que se reporto la cancion
	 */
	private LocalDate rejectedDate;

	/**
	 * Constructor de cancion
	 * 
	 * @param title    titulo de la cancion a crear
	 * @param fileName path de la cancion a crear
	 * @return objeto creado
	 */
	public Song(String title, String fileName) {
		// Super checks if user is logged
		super(title);

		if (Mp3Player.isValidMp3File(fileName) == false)
			System.out.println("File path is incorrect");

		this.explicit = false;
		this.state = SongState.REVISION_PENDING;
		this.fileName = fileName;
		// songPlayer no se inicializa hasta que hagamos play()
	}

	/**
	 * Permite reproducir una cancion
	 * 
	 * @return status de la operacion
	 */
	@Override
	public Status play() {
		if (this.canUserPlay() == false) {
			return Status.ERROR;
		}

		// Try to play it
		try {
			Mp3Player player = new Mp3Player(fileName);
			this.songPlayer = player;
			player.play();
		} catch (FileNotFoundException | Mp3PlayerException e) {
			System.out.println("Error playing the song");
		}

		// Remove one from song count of the logged user (unless admin or premium)
		User u = Sistem.getInstance().getLoggedUser();
		if (u != null) {
			if (u.getUserType() == UserType.STANDARD)
				u.increaseSongCount();
		} else {
			Sistem.getInstance().increaseAnonSongCount();
		}

		// Add one to the plays of the author
		this.getAuthor().increaseSongPlaycount();

		return Status.OK;
	}
	
	/**
	 * Permite parar una cancion
	 * 
	 * @return status de la operacion
	 */
	public Status stop() {
		if (this.songPlayer == null)
			return Status.ERROR;

		this.songPlayer.stop();

		return Status.OK;
	}

	/**
	 * Permite reportar canciones
	 * 
	 * @param u usuario que esta reportando la cancion
	 * @return status de la operacion
	 */
	public Status report() {

		Sistem sis = Sistem.getInstance();
		// Cancion pasa a estado reportado
		this.setState(SongState.REPORTED);

		User u = sis.getLoggedUser();

		// Nuevo reporte con la cancion y el usuario que denuncia
		if (u != null)
			Sistem.getInstance().addReport(new Report(this, u));
		else
			return Status.ERROR;

		return Status.OK;
	}

	/**
	 * Permite rechazar una cancion pendiente de validacion
	 * 
	 * @return ERROR si el usuario loggeado no es un admin
	 */
	public Status reject() {
		Sistem sis = Sistem.getInstance();

		if (!sis.adminIsLogged())
			return Status.ERROR;

		this.rejectedDate = LocalDate.now();
		this.setState(SongState.REJECTED);

		return Status.OK;
	}

	/**
	 * Marca una cancion como aceptada
	 * 
	 * @return ERROR si el usuario loggeado no es un admin
	 */
	public Status accept() {
		Sistem sis = Sistem.getInstance();

		if (!sis.adminIsLogged())
			return Status.ERROR;
		this.setState(SongState.ACCEPTED);

		return Status.OK;
	}

	/**
	 *  Permite aceptar (pero solo para mayores de 18) una cancion pendiente de
	 *  validacion
	 *  
	 * @return ERROR si el usuario loggeado no es admin
	 */
	public Status acceptExplicit() {
		Sistem sis = Sistem.getInstance();
		if (!sis.adminIsLogged())
			return Status.ERROR;
		
		this.explicit = true;
		this.setState(SongState.ACCEPTED);

		return Status.OK;
	}

	/**
	 * Getter de explicit
	 * 
	 * @return devuleve si es explicita o no
	 */
	public Boolean getExplicit() {
		return explicit;
	}

	/**
	 * Setter del estado explicito de la cancion
	 * 
	 * @param explicit nuevo valor del campo explicito de la cancion
	 */
	public void setExplicit(Boolean explicit) {
		this.explicit = explicit;
	}

	/**
	 * Getter de explicit
	 * 
	 * @return devuleve si es explicita o no
	 */
	public SongState getState() {
		return state;
	}

	/**
	 * Setter del estado de la cancion
	 * 
	 * @param state nuevo estado de la cancion
	 */
	public void setState(SongState state) {
		this.state = state;
	}

	/**
	 * Getter de explicit
	 * 
	 * @return devuleve si es explicita o no
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Setter del path del MP3
	 * 
	 * @param fileName path nuevo
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Comprueba que el usuario puede reproducir la cancion
	 * 
	 * @return devuelve true si se puede reproducir o false si no
	 */
	@Override
	protected Boolean canUserPlay() {
		// Check if song is in a playable state
		if (state != SongState.ACCEPTED) {
			return false;
		}

		if (Sistem.getInstance().getLoggedUser() != null) {
			// Check if user can play the song
			if (Sistem.getInstance().getLoggedUser().getSongCount() <= 0) {
				return false;
			}
			// Check if kids are listening
			if (explicit == true && !Sistem.getInstance().getLoggedUser().isOverEighteen()) {
				return false;
			}
		} else if (Sistem.getInstance().getAnonSongCount() <= 0 || explicit == true) {
			return false;
		}

		return true;
	}

	/**
	 * Calcula la longitud de la cancion
	 * 
	 * @return devuleve la longitud o null si no existe la cancion
	 */
	@Override
	public Float getLength() {
		try {
			return (float) Mp3Player.getDuration(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Getter de la fecha en que se reporto la cancion
	 * 
	 * @return devuleve la fecha o null si no se ha reportado
	 */
	public LocalDate getRejectedDate() {
		return rejectedDate;
	}
}
