package padsof;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import padsof.playable.*;
import padsof.sistem.Sistem;
import padsof.user.User;
import padsof.user.UserType;

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

		System.out.println("[INFO] Logged in successfully as " + sis.getLoggedUser().getName() + " (@"
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
				"\n[INFO] Now Playing:\n  " + s1.getAuthor().getName() + " - " + s1.getTitle() + " [" + s1.getLength() + "]");
		
		// Give the user 10 second to listen
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Stop it
		s1.stop();
		
		System.out.println("\n[INFO] Stopping song");

		// Check search by title
		System.out.println("\\n[INFO] Results of searching by title \"Africa\":");
		for (PlayableObject p : sis.search("Africa", true, false, false)) {
			System.out.println("\t> " + p.getTitle());
		}
		
		System.out.println("\\n[INFO] Number of results: " + sis.search("Africa", true, false, false).size());
		
		// Check search by author
		System.out.println("[INFO] Results of searching by author \"" + userName1Test + "\":");
		for (PlayableObject p : sis.search(userName1Test, false, true, false)) {
			System.out.println("\t> " + p.getTitle());
		}

		// Logout from admin account
		sis.logout();
		
		// Login again as normal user
		if (sis.login(userNick1Test, passwordTest) == Status.ERROR)
			System.out.println("[ERROR] Login is not working properly");
		else
			System.out.println("[INFO] Logged in successfully as " + sis.getLoggedUser().getName() + " (@"
					+ sis.getLoggedUser().getNick() + ")");
		
		// Add new album
		Album album = new Album("Africa - Single", 2001);
		sis.addAlbum(album);
		
		// Search our album
		ArrayList<PlayableObject> result = sis.search("Africa - Single", false, false, true);
		if(result.size() < 1) {
			System.out.println("[ERROR] Search is not working properly");
		}
		else {
			System.out.println("[INFO] Found Album" + result.get(0).getTitle());
		}
		
		// Adding song to album
		album.addSong(s1);
		if(Math.abs(album.getLength() % s1.getLength()) < 0.001) { //Usamos un threshold porque comparar doubles en java no esta bien
			System.out.println("[INFO] Added song correctly");
		}
		else {
			System.out.println("[ERROR] Something went wrong when adding a song to an album" + album.getLength() + " vs " + s1.getLength());
		}
		
		// Add new Playlist
		Playlist pl = new Playlist("Africa, but twice");
		sis.addPlaylist(pl);
		
		if(sis.getPlaylistList().contains(pl)) {
			System.out.println("[INFO] Playlist " + pl.getTitle() + " was correctly added to the system");
		}
		else {
			System.out.println("[ERROR] Playlist " + pl.getTitle() + " was not added to the sistem");
		}
		
		// Add items to the playlist and check that they are all in
		pl.addPlayableObject(s1);
		pl.addPlayableObject(album);
		if(Math.abs(pl.getLength() % (album.getLength() + s1.getLength())) < 0.001) { //Usamos un threshold porque comparar doubles en java no esta bien
			System.out.println("[INFO] Added " + s1.getTitle() + " and " + album.getTitle() + " correctly to " + pl.getTitle());
		}
		else {
			System.out.println("[ERROR] Something went wrong when adding a song to an album" + album.getLength() + " vs " + s1.getLength());
		}
		
		sis.logout();
		
		sis.login("admin", "admin");
		
		// Admin starts following Toto
		sis.getLoggedUser().follow(sis.getUserList().get(1));
		
		if (sis.logout() == Status.ERROR)
			System.out.println("[ERROR] Problem logging out...");
		
		sis.login(userNick1Test, passwordTest);
		
		if(sis.getLoggedUser().getIsFollowed().size() == 1) {
			System.out.println("[INFO] " + sis.getLoggedUser().getName() + " is being followed by " + sis.getLoggedUser().getIsFollowed().get(0).getName());
		}
		else {
			System.out.println("[ERROR] There was something wrong when following");
		}
		
		sis.addSong(new Song("All Star", "music" + File.separator + "som.mp3"));
		
		sis.logout();
		sis.login("admin", "admin");
		
		// Checking the notification
		if(sis.getLoggedUser().getNotifications().size() > 0) {
			System.out.println("[INFO] " + sis.getLoggedUser().getName() + " recieved --> " + sis.getLoggedUser().getNotifications().get(0).getNotificationText());
		}
		else {
			System.out.println("[ERROR] There was something wrong with the notifications");
		}
		
		// Reporting new song because is Smash Mouth's song
		result = sis.search("All Star", true, false, false);
		Song s2 = (Song) result.get(0);
		
		if(s2.report() == Status.OK) {
			System.out.println("[INFO] " + s2.getTitle() + " reported");
		}
		else {
			System.out.println("[ERROR] There was something wrong with the report");
		}
		
		if(sis.getReportList().size() > 0) {
			System.out.println("[INFO] Report is in system");
		}
		else {
			System.out.println("[ERROR] Report is not in system");
		}
		
		sis.logout();
		
		sis.login(userNick1Test, passwordTest);
		
		// Just in case, turn to standard
		sis.getLoggedUser().setUserType(UserType.STANDARD);
		
		// Buy premium
		sis.getLoggedUser().goPremium("4115528259915985");
		
		System.out.println("[INFO] User status > " + sis.getLoggedUser().getUserType());
		
		sis.logout();
		
		// Demonstrate the loading funcion (the save was executed in logout)
		sis.loadData();
		
		// Check search by author as before
		System.out.println("[INFO] Results of searching by author \"" + userName1Test + "\":");
		for (PlayableObject p : sis.search(userName1Test, false, true, false)) {
			System.out.println("\t> " + p.getTitle());
		}
	}

}
