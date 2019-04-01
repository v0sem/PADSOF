package padsof.interactions;

import static org.junit.Assert.*;

import org.junit.Test;

import padsof.sistem.Sistem;
import padsof.user.User;

public class CommentTest {
	Sistem sis = Sistem.getInstance();
	String comment = "test comment";
	User u = sis.getLoggedUser();
	Comment test = new Comment(comment, u);

	@Test
	public void test() {
		assertEquals(test.getText(), comment);
		assertEquals(test.getAuthor(), u);
	}

}
