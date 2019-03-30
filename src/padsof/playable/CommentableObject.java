package padsof.playable;

import java.util.ArrayList;

import padsof.Status;
import padsof.interactions.Comment;
import padsof.user.User;
import padsof.user.UserType;

public abstract class CommentableObject extends PlayableObject{
	
	private ArrayList<Comment> commentList;

	public CommentableObject(String title) {
		super(title);
	}

	// Obtiene la lista de comentarios de un objeto comentable
	public ArrayList<Comment> getCommentList() {
		return commentList;
	}
	
	public Status addComment(User auth, String text) {
		if(text.length() > 0) {
			this.commentList.add(new Comment(text, auth));
			return Status.OK;
		}
		
		return Status.ERROR;
	}
	
	public Status removeComment(User auth, String text) {
		
		for(Comment com : this.commentList) {
			if(text.contentEquals(com.getText()) && 
					(com.getAuthor() == auth || auth.getUserType() == UserType.ADMIN)){
				this.commentList.remove(com);
				return Status.OK;
			}
		}
		
		return Status.ERROR;
	}
}
