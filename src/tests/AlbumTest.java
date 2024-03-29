package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import padsof.Status;
import padsof.playable.Album;
import padsof.playable.Song;
import padsof.system.System;

public class AlbumTest {

	Album album = new Album("test", 3000);
	Song s = new Song("Africa", "music" + File.separator + "africa.mp3");
	Song d = new Song("Bejito", "music" + File.separator + "bejito.mp3");
	Song f = new Song("SomBODY", "music" + File.separator + "som.mp3");
	
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
		
		System.getInstance().register("Toto", "toto", 
				LocalDate.of(1980, Month.JANUARY, 1), "1234");
		System.getInstance().login("toto", "1234");
		
		assertEquals(Status.OK, album.play());
		
		album.deleteSong(s);
		album.deleteSong(d);
		album.deleteSong(f);
		
		System.getInstance().logout();
	}

	@Test
	public void testCanUserPlay() {
		album.addSong(s);
		album.addSong(d);
		album.addSong(f);
		
		assertFalse(album.canUserPlay());
		
		System.getInstance().register("Toto1", "toto1", 
				LocalDate.of(1980, Month.JANUARY, 1), "1234");
		System.getInstance().login("toto1", "1234");
		
		assertTrue(album.canUserPlay());
		
		album.deleteSong(s);
		album.deleteSong(d);
		album.deleteSong(f);
		
		System.getInstance().logout();
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
