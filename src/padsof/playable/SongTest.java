package padsof.playable;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

import padsof.Status;
import padsof.sistem.Sistem;

class SongTest {
	
	Song test = new Song("Africa", "music/africa.mp3");
	Sistem sis = Sistem.getInstance();

	@Test
	void testSong() {
		assertNotNull(this.test);
		
		assertEquals("Africa", test.getTitle());
		assertEquals("music/africa.mp3", test.getFileName());
	}
	
	@Test
	void testPlay() {
		test.setExplicit(true);
		assertEquals(Status.ERROR, test.play());
		sis.register("Toto", "toto", 
				LocalDate.of(1980, Month.JANUARY, 1), "1234");
		sis.login("toto", "1234");
		assertEquals(Status.OK, test.play());
		sis.logout();
	}

	@Test
	void testCanUserPlay() {
		test.setExplicit(true);
		assertFalse(test.canUserPlay());
		sis.login("toto", "1234");
		assertTrue(test.canUserPlay());
		sis.logout();
	}

	@Test
	void testReport() {
		assertEquals(Status.ERROR, test.report());
		sis.login("toto", "1234");
		assertEquals(Status.OK, test.report());
		sis.logout();
	}

	@Test
	void testReject() {
		assertEquals(Status.ERROR, test.reject());
		sis.login("admin", "admin");
		assertEquals(Status.OK, test.reject());
	}

}
