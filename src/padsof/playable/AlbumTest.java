package padsof.playable;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import padsof.Status;
import padsof.sistem.Sistem;

public class AlbumTest {

	Album album = new Album("test", 3000);
	Song s = new Song("Africa", "music/africa.mp3");
	Song d = new Song("Bejito", "music/bejito.mp3");
	Song f = new Song("SomBODY", "music/som.mp3");
	
	@Test
	public void testAlbum() {
		assertNotNull(this.album);	
		
		// Check all data is correct inside user
		assertEquals("test", album.getTitle());
		assertEquals(3000, album.getYear());
	}
	
	@Test
	public void testPlay() {
		album.addSong(s);
		album.addSong(d);
		album.addSong(f);
		
		Sistem.getInstance().register("Toto", "toto", 
				LocalDate.of(1980, Month.JANUARY, 1), "1234");
		Sistem.getInstance().login("toto", "1234");
		
		assertEquals(Status.OK, album.play());
		
		album.deleteSong(s);
		album.deleteSong(d);
		album.deleteSong(f);
		
		Sistem.getInstance().logout();
	}

	@Test
	public void testCanUserPlay() {
		album.addSong(s);
		album.addSong(d);
		album.addSong(f);
		
		assertFalse(album.canUserPlay());
		
		Sistem.getInstance().register("Toto", "toto", 
				LocalDate.of(1980, Month.JANUARY, 1), "1234");
		Sistem.getInstance().login("toto", "1234");
		
		assertTrue(album.canUserPlay());
		
		album.deleteSong(s);
		album.deleteSong(d);
		album.deleteSong(f);
		
		Sistem.getInstance().logout();
	}

	@Test
	public void testAddSong() {
		Song fake = null;
		
		assertEquals(Status.ERROR, album.addSong(fake));
		assertEquals(Status.OK, album.addSong(s));
	}

	@Test
	public void testDeleteSong() {
		Song fake = null;
		
		assertEquals(Status.ERROR, album.deleteSong(fake));
		album.addSong(s);
		assertEquals(Status.OK, album.deleteSong(s));
	}
}
