package padsof.interactions;

import static org.junit.Assert.*;

import org.junit.Test;

import padsof.playable.Song;
import padsof.sistem.Sistem;
import padsof.user.User;

public class ReportTest {
	Sistem sis = Sistem.getInstance();
	Song s1 = new Song("Africa", "music/africa.mp3");
	User u = sis.getLoggedUser();
	Report r = new Report(s1, u);
	
	
	@Test
	public void testReport() {
		assertNull(r.getDecisionDate());
	}

	@Test
	public void testReject() {
		r.reject();
		assertNotNull(r.getDecisionDate());
	}

	@Test
	public void testAccept() {
		r.accept();
		assertTrue(u.getBlocked());
	}

}
