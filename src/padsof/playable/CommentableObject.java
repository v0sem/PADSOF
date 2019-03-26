package padsof.playable;

import java.util.ArrayList;

import padsof.interactions.Comment;

public abstract class CommentableObject extends PlayableObject{
	
	private ArrayList<Comment> commentList;

	public CommentableObject(String title) {
		super(title);
	}

	// Obtiene la lista de comentarios de un objeto comentable
	public ArrayList<Comment> getCommentList() {
		return commentList;
	}
}
