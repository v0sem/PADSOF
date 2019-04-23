package padsof.interactions;

import static org.junit.Assert.*;

import org.junit.Test;

import padsof.system.System;
import padsof.user.User;

public class CommentTest {
	System sis = System.getInstance();
	String comment = "test comment";
	User u = sis.getLoggedUser();
	Comment test = new Comment(comment, u);

	@Test
	public void test() {
		assertEquals(test.getText(), comment);
		assertEquals(test.getAuthor(), u);
	}

}
