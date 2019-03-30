package padsof.interactions;

import static org.junit.Assert.*;

import org.junit.Test;

import padsof.playable.Song;
import padsof.sistem.Sistem;
import padsof.user.User;

public class NotificationTest {
	Sistem sis = Sistem.getInstance();
	String text = "test comment";
	User u = sis.getLoggedUser();
	Song s = new Song("Africa", "music/africa.mp3");
	Notification test = new Notification(text, u, s);
	
	@Test
	public void testNotification() {
		assertEquals(test.getNotificationText(), text);
		assertEquals(test.getReceiver(), u);
		assertEquals(test.getTrigger(), s);
	}

}
