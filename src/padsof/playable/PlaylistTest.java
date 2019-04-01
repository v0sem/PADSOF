package padsof.playable;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import padsof.sistem.Sistem;
import padsof.Status;

public class PlaylistTest {

	Playlist test = new Playlist("test");
	Album album = new Album("test", 3000);
	Song s = new Song("Africa", "music/africa.mp3");
	Song d = new Song("Bejito", "music/bejito.mp3");
	Song f = new Song("SomBODY", "music/som.mp3");
	Sistem sis = Sistem.getInstance();
	
	@Test
	public void testPlaylist() {
		assertNotNull(this.test);
		
		assertEquals("test", test.getTitle());
	}


	@Test
	public void testPlay() {
		album.addSong(s);
		album.addSong(d);
		test.addPlayableObject(album);
		test.addPlayableObject(f);
		
		sis.register("Toto", "toto", 
				LocalDate.of(1980, Month.JANUARY, 1), "1234");
		sis.login("toto", "1234");
		
		assertEquals(Status.OK, album.play());
		
		sis.logout();
		
		album.deleteSong(s);
		album.deleteSong(d);
		test.deletePlayableObject(album);
		test.deletePlayableObject(f);
	}

	@Test
	public void testCanUserPlay() {
		test.addPlayableObject(f);
		
		assertFalse(album.canUserPlay());
		
		Sistem.getInstance().login("toto", "1234");
		
		assertTrue(album.canUserPlay());
		
		test.deletePlayableObject(f);
	}

	@Test
	public void testAddPlayableObject() {
		Song fake = null;
		
		assertEquals(Status.ERROR, test.addPlayableObject(fake));
		assertEquals(Status.OK, test.addPlayableObject(s));
		test.deletePlayableObject(s);
	}

	@Test
	public void testDeletePlayableObject() {
		Song fake = null;
		
		assertEquals(Status.ERROR, test.deletePlayableObject(fake));
		assertEquals(Status.ERROR, test.deletePlayableObject(fake));
		test.addPlayableObject(s);
		assertEquals(Status.OK, test.deletePlayableObject(s));
	}

}
