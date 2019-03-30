package padsof.playable;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import padsof.Status;
import padsof.sistem.Sistem;

public class AlbumTest {

	Album album = new Album("test", 3000);
	
	@Test
	public void testPlay() {
		album.addSong(new Song("Africa", "music/africa.mp3"));
		album.addSong(new Song("Bejito", "music/bejito.mp3"));
		album.addSong(new Song("SomBODY", "music/som.mp3"));
		
		Sistem.getInstance().register("Toto", "toto", 
				LocalDate.of(1980, Month.JANUARY, 1), "1234");
		Sistem.getInstance().login("toto", "1234");
		
		assertEquals(Status.OK, album.play());
	}

	@Test
	public void testCanUserPlay() {
		album.addSong(new Song("Africa", "music/africa.mp3"));
		album.addSong(new Song("Bejito", "music/bejito.mp3"));
		album.addSong(new Song("SomBODY", "music/som.mp3"));
		
		assertFalse(album.canUserPlay());
		
		Sistem.getInstance().register("Toto", "toto", 
				LocalDate.of(1980, Month.JANUARY, 1), "1234");
		Sistem.getInstance().login("toto", "1234");
		
		assertTrue(album.canUserPlay());
	}

	@Test
	public void testAddSong() {
		Song fake = null;
		Song som = new Song("SomBODY", "music/som.mp3");
		
		assertEquals(Status.ERROR, album.addSong(fake));
		assertEquals(Status.OK, album.addSong(som));
	}

	@Test
	public void testDeleteSong() {
		Song fake = null;
		Song som = (new Song("SomBODY", "music/som.mp3"));
		
		assertEquals(Status.ERROR, album.deleteSong(fake));
		assertEquals(Status.ERROR, album.deleteSong(som));
		album.addSong(som);
		assertEquals(Status.OK, album.deleteSong(som));
	}

	@Test
	public void testAlbum() {
		fail("Not yet implemented");
	}

}
