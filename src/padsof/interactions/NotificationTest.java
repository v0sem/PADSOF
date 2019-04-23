package padsof.interactions;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import padsof.playable.Song;
import padsof.system.System;
import padsof.user.User;

public class NotificationTest {
	System sis = System.getInstance();
	String text = "test comment";
	User u = sis.getLoggedUser();
	Song s = new Song("Africa", "music" + File.separator + "africa.mp3");
	Notification test = new Notification(text, u, s);
	
	@Test
	public void testNotification() {
		assertEquals(test.getNotificationText(), text);
		assertEquals(test.getReceiver(), u);
		assertEquals(test.getTrigger(), s);
	}

}
