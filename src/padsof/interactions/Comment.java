package padsof.interactions;

import padsof.user.User;

public class Comment {

	private String commentText;
	
	private User commentAuthor;
	
	public Comment(String txt, User auth) {
		
		this.commentText = txt;
		this.commentAuthor = auth;
	}
	
	/*************** GETTERS ********************/
	
	public String getText() {
		
		return this.commentText;
	}
	
	public User getAuthor() {
		
		return this.commentAuthor;
	}
}
