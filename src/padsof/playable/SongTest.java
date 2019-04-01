package padsof.playable;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import padsof.sistem.Sistem;
import padsof.Status;

public class SongTest {
	Sistem sis = Sistem.getInstance();
	Status flag = sis.login("admin", "admin");
	Song test = new Song("Africa", "music/africa.mp3");

	@Test
	public void testSong() {
		assertNotNull(this.test);
		
		assertEquals("Africa", test.getTitle());
		assertEquals("music/africa.mp3", test.getFileName());
	}
	
	@Test
	public void testPlay() {
		sis.addSong(test);
		test.setExplicit(true);
		test.accept();
		sis.logout();
		assertEquals(Status.ERROR, test.play());
		sis.register("Toto", "toto", 
				LocalDate.of(1980, Month.JANUARY, 1), "1234");
		sis.login("toto", "1234");
		assertEquals(Status.OK, test.play());
		sis.logout();
	}

	@Test
	public void testCanUserPlay() {
		sis.addSong(test);
		test.setExplicit(true);
		test.accept();
		sis.logout();
		assertFalse(test.canUserPlay());
		sis.register("Toto", "toto", 
				LocalDate.of(1980, Month.JANUARY, 1), "1234");
		sis.login("toto", "1234");
		assertTrue(test.canUserPlay());
		sis.logout();
	}

	@Test
	public void testReport() {
		sis.addSong(test);
		test.accept();
		sis.logout();
		assertEquals(Status.ERROR, test.report());
		sis.login("toto", "1234");
		assertEquals(Status.OK, test.report());
		sis.logout();
	}

	@Test
	public void testReject() {
		sis.addSong(test);
		test.accept();
		sis.logout();
		assertEquals(Status.ERROR, test.reject());
		sis.login("admin", "admin");
		assertEquals(Status.OK, test.reject());
	}

}
