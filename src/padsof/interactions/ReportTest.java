package padsof.interactions;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import padsof.Status;
import padsof.playable.Song;
import padsof.sistem.Sistem;
import padsof.user.User;

public class ReportTest {
	Sistem sis = Sistem.getInstance();
	Song s1 = new Song("Africa", "music" + File.separator + "africa.mp3");
	
	@Test
	public void testReport() {
		if (sis.register("Mr Crabs", "crabs", LocalDate.of(1970, Month.APRIL, 20), "1111") == Status.ERROR) {
			sis.login("crabs", "1111");
		}
		User u = sis.getLoggedUser();
		Report r = new Report(s1, u);
		assertNull(r.getDecisionDate());
	}

	@Test
	public void testReject() {
		if (sis.register("Mr Crabs", "crabs", LocalDate.of(1970, Month.APRIL, 20), "1111") == Status.ERROR) {
			sis.login("crabs", "1111");
		}
		User u = sis.getLoggedUser();
		Report r = new Report(s1, u);
		r.reject();
		assertNotNull(r.getDecisionDate());
	}

	@Test
	public void testAccept() {
		sis.register("Mr Crabs", "crabs", LocalDate.of(1970, Month.APRIL, 20), "1111");
		sis.login("crabs", "1111");
		
		User u = sis.getLoggedUser();
		if (u == null)
			System.out.println("User not logged in, error in testAccept.");
		
		Report r = new Report(s1, u);
		r.accept();
		assertTrue(s1.getAuthor().getBlocked());
	}

}
