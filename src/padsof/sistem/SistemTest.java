package padsof.sistem;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import padsof.Status;

public class SistemTest {
	
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
		assertEquals(0, test.getAnonSongCount().compareTo(initialSongCount-1));
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
		// Valid login
		assertEquals(Status.OK, test.login("crabs", "1111"));
		// Shouldn't be able to login when someone is logged in
		assertEquals(Status.ERROR, test.login("mango", "1234"));
		// Login after logout
		test.logout();
		assertEquals(Status.ERROR, test.login("nonExistentNick", "1111"));
		assertEquals(Status.ERROR, test.login("mango", "invalidPass"));
		assertEquals(Status.OK, test.login("mango", "1234"));
	}
	
	@Test
	public void testLogout() {
		// Logout should always return OK. Else, there's a problem with saveData
		assertEquals(Status.OK, test.logout());
	}
	
	@Test
	public void testAdminIsLogged() {
		assertEquals(false, test.adminIsLogged());
		test.login("admin", "admin");
		assertEquals(true, test.adminIsLogged());
		test.logout();
		assertEquals(false, test.adminIsLogged());
	}

}
