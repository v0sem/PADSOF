package padsof.interactions;

import java.time.LocalDate;

import padsof.playable.Song;
import padsof.playable.SongState;
import padsof.sistem.Sistem;
import padsof.user.User;

public class Report {
	
	private Song reportedSong;

	private User reporter;
	
	private LocalDate decisionDate;
	
	public Report(Song reportedSong, User reporter) {
		
		this.reportedSong = reportedSong;
		this.reporter = reporter;
	}
	
	public void reject() {
		
		// se banea al denunciante algunos dias (fechasimulada)
		decisionDate = LocalDate.now();
		
		// cancion pasa de reportada a visible
		reportedSong.setState(SongState.ACCEPTED);
	}
	
	public void accept() {
		
		// banear al plagiador
		reporter.block();
		
		// cancion se borra por plagio (article 13)
		Sistem.getInstance().deleteSong(reportedSong);
	}
}
