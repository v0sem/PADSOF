package padsof.user;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import padsof.Status;

public class UserTest {
	String name = "test";
	LocalDate birth = LocalDate.of(1980, Month.JANUARY, 1);
	String nick = "testNick";
	String password = "password";
	
	User test = new User(name, birth, nick, password);
	User test2 = new User("followee", birth, "followeeNick", password);
	
	@Test
	public void testUser() {
		// Check user is created and not null
		assertNotNull(this.test);	
		
		// Check all data is correct inside user
		assertEquals(this.name, test.getName());
		assertEquals(this.birth, test.getBirthDate());
		assertEquals(this.nick, test.getNick());
		assertEquals(this.password, test.getPassword());
	}

	@Test
	public void testBlock() {
		test.block();
		assertTrue(test.getBlocked());
	}

	@Test
	public void testUnblock() {
		test.unblock();
		assertFalse(test.getBlocked());
	}

	@Test
    public void testIncreaseSongCount() {
        test.setSongCount(10);
        long initialSongCount = test.getSongCount();
        test.increaseSongCount();
        assertEquals(initialSongCount, test.getSongCount() + 1);
    }

	@Test
	public void testIncreaseSongPlaycount() {
		long initialSongPlaycount = test.getSongPlaycount();
		test.increaseSongPlaycount();
		assertEquals(initialSongPlaycount + 1, test.getSongPlaycount());
	}

	@Test
	public void testFollow() {
		assertEquals(test.follow(test2), Status.OK);
		assertEquals(test.unfollow(test2), Status.OK);
	}

	@Test
	public void testUnfollow() {
		assertEquals(test.follow(test2), Status.OK);
		assertEquals(test.unfollow(test2), Status.OK);
	}

	@Test
	public void testIsOverEighteen() {
		assertTrue(test.isOverEighteen());
	}

	@Test
	public void testGoPremium() {
		assertEquals(test.goPremium("4485701273786870"), Status.OK);
		assertEquals(test.getUserType(), UserType.PREMIUM);
	}
}
