package padsof.sistem;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import fechasimulada.FechaSimulada;
import padsof.Status;
import padsof.playable.Song;
import padsof.playable.SongState;
import padsof.user.User;
import padsof.interactions.Report;

public class SistemTest {

	File file = new File("System.bal");
	boolean flag = file.delete();
	Sistem test = Sistem.getInstance();

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
		assertEquals(0, test.getAnonSongCount().compareTo(initialSongCount - 1));
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

		FechaSimulada.restablecerHoyReal();
		System.out.println(FechaSimulada.getHoy());
		test.logout();
		test.register("Honest Man", "thetruth", LocalDate.of(1970, Month.APRIL, 20), "1111");
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
		assertEquals(SongState.ACCEPTED, s1.getState());
		assertTrue(uLiar.getBlocked());

		// 30 days later
		FechaSimulada.avanzar(40);
		System.out.println(FechaSimulada.getHoy());
		
		Sistem.getInstance().checkDate();

		assertFalse(uLiar.getBlocked());

		test.logout();
		test.deleteUser(uLiar);
	}
}
