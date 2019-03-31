package padsof.playable;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

import padsof.Status;
import padsof.sistem.Sistem;

class CommentableObjectTest {
	
	Song test = new Song("Africa", "music/africa.mp3");
	Sistem sis = Sistem.getInstance();

	@Test
	void testAddComment() {
		assertEquals(Status.ERROR, test.addComment("This is a test"));
		sis.register("Toto", "toto", 
				LocalDate.of(1980, Month.JANUARY, 1), "1234");
		sis.login("toto", "1234");
		assertEquals(Status.OK, test.addComment("This is a test"));
	}

	@Test
	void testRemoveComment() {
		assertEquals(Status.ERROR, test.removeComment("This is a test"));
		sis.register("Toto", "toto", 
				LocalDate.of(1980, Month.JANUARY, 1), "1234");
		sis.login("toto", "1234");
		assertEquals(Status.ERROR, test.removeComment("This is a test"));
		test.addComment("This is a test");
		assertEquals(Status.OK, test.removeComment("This is a test"));
	}

}
