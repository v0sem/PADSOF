/**
 * Reporte de plagio con la cancion denunciada y el usuario que la denuncio
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 */

package padsof.interactions;

import java.time.LocalDate;

import fechasimulada.FechaSimulada;
import padsof.playable.Song;
import padsof.playable.SongState;
import padsof.sistem.Sistem;
import padsof.user.User;

public class Report implements java.io.Serializable {

	/**
	 * Constructor de reporte
	 * 
	 * @param reportedSong cancion acusada de plagio
	 * @param reporter     usuario que reporta plagio
	 */
	public Report(Song reportedSong, User reporter) {

		this.reportedSong = reportedSong;
		this.reporter = reporter;
		this.closed = false;
	}

	/**
	 * Cancion que ha sido reportada por plagio
	 */
	private Song reportedSong;

	/**
	 * Usuario que ha reportado plagio en una cancion
	 */
	private User reporter;

	/**
	 * Fecha en que el admin acepta o rechaza el reporte de plagio
	 */
	private LocalDate decisionDate;

	/**
	 * Indica si el administrador ya ha decidido si acepta o rechaza el reporte
	 */
	private Boolean closed;

	/**
	 * Permite rechazar un reporte (cuando una denuncia de plagio es falso)
	 */
	public void reject() {
		// se banea al denunciante (solo durante algunos dias -> checkDate)
		decisionDate = FechaSimulada.getHoy();
		reporter.block();
		// se cierra el reporte
		this.closed = true;
		// cancion pasa de reportada a visible
		reportedSong.setState(SongState.ACCEPTED);
	}

	/**
	 * Permite aceptar un reporte (cuando una denuncia de plagio es verdadera)
	 */
	public void accept() {
		// banear al plagiador
		reportedSong.getAuthor().block();
		// cancion se borra por plagio (article 13)
		Sistem.getInstance().deleteSong(reportedSong);
		// Borra el reporte
		Sistem.getInstance().deleteReport(this);
	}

	/**
	 * Getter de decisionDate
	 * 
	 * @return devuelve la fecha en que admin acepta o rechaza reporte
	 */
	public LocalDate getDecisionDate() {
		return decisionDate;
	}

	/**
	 * Getter de closed. Comprueba si el report esta cerrado
	 * 
	 * @return true si el report se ha cerrado
	 */
	public Boolean getClosed() {
		return closed;
	}

	/**
	 * Getter del reporter
	 * 
	 * @return usuario que ha creado el report
	 */
	public User getReporter() {
		return reporter;
	}
}
