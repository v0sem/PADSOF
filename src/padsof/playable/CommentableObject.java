/**
 * Esta clase engloba los playable objects que sean comentables
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 */

package padsof.playable;

import java.util.ArrayList;

import padsof.Status;
import padsof.interactions.Comment;
import padsof.sistem.Sistem;
import padsof.user.User;
import padsof.user.UserType;

public abstract class CommentableObject extends PlayableObject{

	/**
	 * Lista de los comentarios del Commentable Object
	 */
	private ArrayList<Comment> commentList;
	
	/**
	 * Constructor de los objetos comentables
	 * @param title titulo del objeto
	 * @return devuleve el objeto nuevo
	 */
	public CommentableObject(String title) {
		super(title);
	}

	/**
	 * Obtiene la lista de comentarios de un objeto comentable
	 * @return devuleve la lista de comentarios
	 */
	public ArrayList<Comment> getCommentList() {
		return commentList;
	}
	
	/**
	 * Permite agregar nuevos comentarios
	 * @param text texto del comentario
	 * @return status de la operacion
	 */
	public Status addComment(String text) {
		Sistem sis = Sistem.getInstance();
		
		if(text.length() > 0) {
			this.commentList.add(new Comment(text, sis.getLoggedUser()));
			return Status.OK;
		}
		
		return Status.ERROR;
	}
	
	/**
	 * Permite borrar comentarios
	 * @param texto del comentario que queremos borrar
	 * @return status de la operacion
	 */
	public Status removeComment(String text) {
		Sistem sis = Sistem.getInstance();
		
		for(Comment com : this.commentList) {
			if(text.contentEquals(com.getText()) && 
					(com.getAuthor() == sis.getLoggedUser() || auth.getUserType() == UserType.ADMIN)){
				this.commentList.remove(com);
				return Status.OK;
			}
		}
		
		return Status.ERROR;
	}
}
