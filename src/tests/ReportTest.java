package tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import padsof.Status;
import padsof.interactions.Report;
import padsof.playable.Song;
import padsof.system.System;
import padsof.user.User;

public class ReportTest {
	System sis = System.getInstance();
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
			java.lang.System.out.println("[ERROR] User not logged in, error in testAccept.");
		
		Report r = new Report(s1, u);
		r.accept();
		assertTrue(s1.getAuthor().getBlocked());
	}

}
