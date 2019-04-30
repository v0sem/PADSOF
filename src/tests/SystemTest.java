package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import fechasimulada.FechaSimulada;
import padsof.Status;
import padsof.playable.Album;
import padsof.playable.Playlist;
import padsof.playable.Song;
import padsof.playable.SongState;
import padsof.system.System;
import padsof.user.User;
import padsof.interactions.Report;

public class SystemTest {

	File file = new File("System.bal");
	boolean flag = file.delete();
	System test = System.getInstance();

	@Test
	public void testSistem() {
		// Check sistem is created and not null
		assertNotNull(this.test);

		// Check sistem fields are not null
		assertNotNull(test.getUserList());
		assertNotNull(test.getSongList());
		assertNotNull(test.getAlbumList());
		assertNotNull(test.getPlaylistList());
		assertNotNull(test.getReportList());
		assertNotNull(test.getAdminUser());
	}

	@Test
	public void testIncreaseAnonSongCount() {
		Long initialSongCount = test.getAnonSongCount();
		test.increaseAnonSongCount();
		// Yes, songCounts should go backwards
		assertEquals(test.getAnonSongCount(), initialSongCount - 1);
		// Leave count as it was
		test.setAnonSongCount(initialSongCount);
	}

	@Test
	public void testRegister() {
		// Register with valid data
		assertEquals(Status.OK, test.register("Mr Crabs", "crabs", LocalDate.of(1970, Month.APRIL, 20), "1111"));
		// Nick or name already exist
		assertEquals(Status.ERROR, test.register("Mr Crabs", "themadman", LocalDate.of(1970, Month.APRIL, 20), "1111"));
		assertEquals(Status.ERROR, test.register("The Madman", "crabs", LocalDate.of(1970, Month.APRIL, 20), "1111"));
		// Null in strings should be controlled
		assertEquals(Status.ERROR, test.register(null, "pointer", LocalDate.of(1970, Month.APRIL, 20), "1111"));
		assertEquals(Status.ERROR, test.register("Yoda", null, LocalDate.of(1970, Month.APRIL, 20), "1111"));
		assertEquals(Status.ERROR, test.register("Another", "anotherone", LocalDate.of(1970, Month.APRIL, 20), null));
		// Register a second valid user
		assertEquals(Status.OK, test.register("My Mango", "mango", LocalDate.of(1970, Month.APRIL, 20), "1234"));
	}

	@Test
	public void testLogin() {
		test.register("Antonio", "antonio", LocalDate.of(1970, Month.APRIL, 20), "1111");
		test.register("Pablo", "pablo", LocalDate.of(1970, Month.APRIL, 20), "1234");
		// Valid login
		assertEquals(Status.OK, test.login("antonio", "1111"));
		// Shouldn't be able to login when someone is logged in
		assertEquals(Status.ERROR, test.login("pablo", "1234"));
		// Login after logout
		test.logout();
		assertEquals(Status.ERROR, test.login("nonExistentNick", "1111"));
		assertEquals(Status.ERROR, test.login("pablo", "invalidPass"));
		assertEquals(Status.OK, test.login("pablo", "1234"));
	}

	@Test
	public void testLogout() {
		// Logout should always return OK. Else, there's a problem with saveData
		assertEquals(Status.OK, test.logout());
	}

	@Test
	public void testAdminIsLogged() {
		test.logout();
		test.login("admin", "admin");
		assertEquals(true, test.adminIsLogged());
		test.logout();
		assertEquals(false, test.adminIsLogged());
	}

	@Test
	public void testCheckDate() {
		// Expected:
		// Check reports and unblock users that have been blocked for 30days
		// Restore the anonsongcount to maximum every 30days
		// IF someone is logged, check if they exceed the rep_count to make them premium
		// check if 30 day period exceeds mesiversario (getregistereddate) to reset the
		// count
		// If 30 days have passed since they paid premium, get they money again (if they
		// are not privileged)

		//FechaSimulada.restablecerHoyReal();
		java.lang.System.out.println(FechaSimulada.getHoy());
		test.logout();
		User uHonest = new User("Honest Man", LocalDate.of(1970, Month.APRIL, 20), "thetruth", "1111");
		test.addUser(uHonest);
		Song s1 = new Song("Very Original Song", "music/bejito.mp3");
		test.addSong(s1);

		test.logout();
		test.login("admin", "admin");
		s1.accept();

		test.logout();
		User uLiar = new User("A liar", LocalDate.of(1970, Month.APRIL, 20), "liar", "1111");
		test.addUser(uLiar);
		test.login("liar", "1111");
		s1.report();
		assertEquals(SongState.REPORTED, s1.getState());

		test.logout();
		test.login("admin", "admin");
		// admin rejects the report: song was not plagio
		for (Report r : test.getReportList())
			r.reject();
		test.logout();
		assertEquals(SongState.ACCEPTED, s1.getState());
		assertTrue(uLiar.getBlocked());
		
		// Anon plays song to decrease anonSongCount by 1
		s1.play();
		
		// User plays song to decrease individual SongCount by 1
		test.login("thetruth", "1111");
		s1.play();
		
		// 30 days later
		FechaSimulada.avanzar(40);
		java.lang.System.out.println(FechaSimulada.getHoy());
		
		System.getInstance().checkDate();

		assertFalse(uLiar.getBlocked());
		test.deleteUser(uLiar);
		
		// SongCounts should be updated
		assertEquals(test.getMaxRegisteredSong(), uHonest.getSongCount());
		assertEquals(test.getAnonSongCount(), test.getMaxAnonSong());
	}
	
	@Test
	public void testSearch() {
		test.logout();
		test.register("SmashMouth", "smashmouth", LocalDate.of(1970, Month.APRIL, 20), "1111");
		test.login("smashmouth", "1111");
		
		Song s1 = new Song("All Star", "music/som.mp3");
		test.addSong(s1);
		assertNotNull(test.search("All Star", true, false, false));
		assertEquals(1, test.search("All Star", true, false, false).size());
		
		Album a1 = new Album("Shrek The Movie", 2001);
		test.addAlbum(a1);
		assertNotNull(test.search("Shrek The Movie", false, false, true));
		assertEquals(1, test.search("Shrek The Movie", false, false, true).size());
		
		assertNotNull(test.search("SmashMouth", false, true, false));
		assertEquals(2, test.search("SmashMouth", false, true, false).size()); // Expected 2 (song + album)
	}
	
	@Test
	public void testSave() {
		test.logout();
		User u1 = new User("Fernando", LocalDate.of(1970, Month.APRIL, 20), "yaboi", "1111");
		User artist = new User("The Artist", LocalDate.of(1970, Month.APRIL, 20), "theoneandonly", "1111");
		test.addUser(u1);
		test.addUser(artist);
		test.login("theoneandonly", "1111");
		Song s1 = new Song("Silaba Tonica", "music/bejito.mp3");
		Song s2 = new Song("A continent", "music/africa.mp3");
		Song s3 = new Song("Somebody", "music/som.mp3");
		test.addSong(s1);
		test.addSong(s2);
		test.addSong(s3);
		Album a = new Album("Latin Hits", 2009);
		a.addSong(s1);
		a.addSong(s2);
		test.addAlbum(a);
		test.logout();
		
		test.login("yaboi", "1111");
		Playlist pl = new Playlist("Mi lista 1");
		pl.addPlayableObject(s3);
		pl.addPlayableObject(a);
		test.addPlaylist(pl);
		try {
			test.saveData();
		} catch (IOException e) {
			fail("IOException in saveData");
		}
		
		test.deleteAlbum(a);
		test.deleteSong(s1);
		test.deleteSong(s2);
		test.deleteSong(s3);
		
		File file = new File("System.bal");
		file.delete();
	}
	
	@Test
	public void testLoad() {
		test.logout();
		assertNotNull(test.loadData());
		assertNotNull(test.getUserList());
		assertNotNull(test.getSongList());
		assertNotNull(test.getAlbumList());
		assertNotNull(test.getPlaylistList());
		assertNotNull(test.getReportList());
		assertNotNull(test.getAdminUser());
		
		User u1 = new User("Fernando", LocalDate.of(1970, Month.APRIL, 20), "yaboi", "1111");
		User artist = new User("The Artist", LocalDate.of(1970, Month.APRIL, 20), "theoneandonly", "1111");
		test.addUser(u1);
		test.addUser(artist);
		test.login("theoneandonly", "1111");
		Song s1 = new Song("Le Bejo", "music/bejito.mp3");
		Song s2 = new Song("The mainland", "music/africa.mp3");
		Song s3 = new Song("No Star", "music/som.mp3");
		test.addSong(s1);
		test.addSong(s2);
		test.addSong(s3);
		Album a = new Album("Latin Hits", 2009);
		a.addSong(s1);
		a.addSong(s2);
		test.addAlbum(a);
		test.logout();
		
		test.login("yaboi", "1111");
		Playlist pl = new Playlist("Mi lista 1");
		pl.addPlayableObject(s3);
		pl.addPlayableObject(a);
		test.addPlaylist(pl);
		s3.report();
		test.logout();
		
		assertNotNull(test.loadData());
		// Check everything is how we left it at logout (logout saves data)
		assertNotNull(test.getUserList());
		assertNotNull(test.getSongList());
		assertNotNull(test.getAlbumList());
		assertNotNull(test.getPlaylistList());
		assertNotNull(test.getReportList());
		assertNotNull(test.getAdminUser());
		assertSame(Status.ERROR, test.register("Fernando", "yaboi", LocalDate.of(1970, Month.APRIL, 20), "1111"));
		assertSame(Status.ERROR, test.register("The Artist", "theoneandonly", LocalDate.of(1970, Month.APRIL, 20), "1111"));
		assertEquals(1, test.getReportList().size());
		assertEquals(3, test.getSongList().size());
		assertEquals(1, test.getAlbumList().size());
		assertEquals(1, test.getPlaylistList().size());
		test.deleteAlbum(a);
		test.deleteSong(s1);
		test.deleteSong(s2);
		test.deleteSong(s3);
		
		File file = new File("System.bal");
		file.delete();
	}
}
