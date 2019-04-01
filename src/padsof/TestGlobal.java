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

	// Tiene controles muy especificos que iran en el JUnit, pero de momento vamos
	// probando aqui
	public static void main(String[] args) {
		Sistem sis = Sistem.getInstance();
		// TODO: hay que fixear read/write del .bal porque la userList se queda a null
		// al recargarlo con instance

		// Register Toto and log in
		if (sis.register("Toto", "toto", LocalDate.of(1980, Month.JANUARY, 1), "1234") == Status.ERROR)
			System.out.println("Error en register (el nick o nombre ya existen)");
		if (sis.login("toto", "1111") == Status.OK || sis.login("itsame", "mario") == Status.OK)
			System.out.println("Login is not working properly");
		if (sis.login("toto", "1234") == Status.ERROR)
			System.out.println("Login is not working properly");
		else
			System.out.println("Logged in successfully as " + sis.getLoggedUser().getName() + " (@"
					+ sis.getLoggedUser().getNick() + ")");

		// Toto going to upload Africa. Memes are coming
		Song s1 = new Song("Africa", "music" + File.separator + "africa.mp3");
		sis.addSong(s1); // Should be uploaded as REVISION_PENDING

		// Toto logs out, enough has been done
		if (sis.logout() == Status.ERROR || sis.getLoggedUser() != null)
			System.out.println("Logout falla: que ha pachado?");
		else
			System.out.println("Logging out. Com bac soon");

		// Admin logs in. Has to verify Toto's masterpiece. Logs out
		if (sis.login("toto", "1111") == Status.OK || sis.login("itsame", "mario") == Status.OK)
			System.out.println("Login is not working properly");
		s1.setState(SongState.ACCEPTED); // Admin validates the song. Should we check it's the admin?
		if (sis.logout() == Status.ERROR || sis.getLoggedUser() != null)
			System.out.println("Logout falla con mi amo: que ha pachado?");
		else
			System.out.println("Logging out. Com bac soon, mi amo");

		// Check how the song was uploaded
		System.out.println(
				"\nNow Playing:\n  " + s1.getAuthor().getName() + " - " + s1.getTitle() + " [" + s1.getLength() + "]");
		// s1.play();

		// Check search by title
		for (PlayableObject p : sis.search("Africa", true, false, false))
			/* p.play() */;
		for (PlayableObject p : sis.search("Toto", false, true, false))
			/* p.play() */;

		// Toto came bac to create a new album for Africa
		if (sis.login("toto", "1234") == Status.ERROR)
			System.out.println("Login is not working properly");
		else
			System.out.println("Logged in successfully as " + sis.getLoggedUser().getName() + " (@"
					+ sis.getLoggedUser().getNick() + ")");
		sis.addAlbum(new Album("Africa - Single", 2001));
		// Check search by album...
		// ...
		// To be continued
	}

}
