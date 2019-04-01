package padsof;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

import padsof.playable.Album;
import padsof.playable.PlayableObject;
import padsof.playable.Song;
import padsof.sistem.Sistem;
import padsof.user.User;
import padsof.playable.SongState;

public class TestGlobal {
	private static String userName1Test = "Toto";
	private static String userNick1Test = "toto";

	private static String userName2Test = "Made";
	private static String userNick2Test = "made";

	private static LocalDate birthTest = LocalDate.of(1980, Month.JANUARY, 1);

	private static String passwordTest = "1234";

	private static String invalidString = "invalid";

	public static void main(String[] args) {
		Sistem sis = Sistem.getInstance();

		// Register Toto
		sis.register(userName1Test, userNick1Test, birthTest, passwordTest);

		// Try login with incorrect credentials
		sis.login(userNick1Test, invalidString);
		sis.login(invalidString, invalidString);

		// Try real login
		sis.login(userNick1Test, passwordTest);

		System.out.println("Logged in successfully as " + sis.getLoggedUser().getName() + " (@"
				+ sis.getLoggedUser().getNick() + ")");

		// Create new song and add it (revision pending)
		Song s1 = new Song("Africa", "music" + File.separator + "africa.mp3");
		sis.addSong(s1);

		// Log the user out
		sis.logout();

		// Log the admin in
		sis.login("admin", "admin");

		// Accept the song we uploaded
		s1.setState(SongState.ACCEPTED);

		s1.play();

		// Check how the song was uploaded
		System.out.println(
				"\nNow Playing:\n  " + s1.getAuthor().getName() + " - " + s1.getTitle() + " [" + s1.getLength() + "]");
		
		// Give the user 10 second to listen
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Check search by title
		for (PlayableObject p : sis.search("Africa", true, false, false)) {
			System.out.println("Results of searching by title \"Africa\":");
			System.out.println("\t> " + p.getTitle());
		}
		
		// Check search by author
		for (PlayableObject p : sis.search(userName1Test, false, true, false)) {
			System.out.println("Results of searching by author \"" + userName1Test + "\":");
			System.out.println("\t> " + p.getTitle());
		}

		// Logout from admin account
		sis.logout();
		
		// Login again as normal user
		if (sis.login(userNick1Test, "1234") == Status.ERROR)
			System.out.println("Login is not working properly");
		else
			System.out.println("Logged in successfully as " + sis.getLoggedUser().getName() + " (@"
					+ sis.getLoggedUser().getNick() + ")");
		sis.addAlbum(new Album("Africa - Single", 2001));
	}

}
