package padsof.playable;

import java.util.ArrayList;

import padsof.interactions.Comment;
import padsof.user.User;

public abstract class CommentableObject extends PlayableObject{
	
	private ArrayList<Comment> commentList;

	public CommentableObject(User author, String title) {
		super(author, title);
	}

	// Obtiene la lista de comentarios de un objeto comentable
	public ArrayList<Comment> getCommentList() {
		return commentList;
	}
}
