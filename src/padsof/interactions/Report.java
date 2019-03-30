/**
 * Reporte de plagio con la cancion denunciada y el usuario que la denuncio
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 */

package padsof.interactions;

import java.time.LocalDate;

import padsof.playable.Song;
import padsof.playable.SongState;
import padsof.sistem.Sistem;
import padsof.user.User;

public class Report {
	
	/*
	 * Cancion que ha sido reportada por plagio
	 */
	private Song reportedSong;

	/*
	 * Usuario que ha reportado plagio en una cancion
	 */
	private User reporter;
	
	/*
	 * Fecha en que el admin acepta o rechaza el reporte de plagio 
	 */
	private LocalDate decisionDate;
	
	/**
	 * Constructor de reporte
	 * @param reportedSong cancion acusada de plagio
	 * @param reporter usuario que reporta plagio
	 * @return objeto creado
	 */
	public Report(Song reportedSong, User reporter) {
		
		this.reportedSong = reportedSong;
		this.reporter = reporter;
	}
	
	/**
	 * Permite rechazar un reporte (cuando una denuncia de plagio es falso)
	 */
	public void reject() {
		
		// se banea al denunciante algunos dias (fechasimulada)
		decisionDate = LocalDate.now();
		
		// cancion pasa de reportada a visible
		reportedSong.setState(SongState.ACCEPTED);
	}
	
	/*
	 * Permite aceptar un reporte (cuando una denuncia de plagio es verdadera)
	 */
	public void accept() {
		
		// banear al plagiador
		reporter.block();
		
		// cancion se borra por plagio (article 13)
		Sistem.getInstance().deleteSong(reportedSong);
	}

	/*
	 * Getter de decisionDate
	 * @return devuelve la fecha en que admin acepta o rechaza reporte
	 */
	public LocalDate getDecisionDate() {
		return decisionDate;
	}
}
