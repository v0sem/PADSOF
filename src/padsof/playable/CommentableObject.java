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

@SuppressWarnings("serial")
public abstract class CommentableObject extends PlayableObject{

	/**
	 * Lista de los comentarios del Commentable Object
	 */
	private ArrayList<Comment> commentList;
	
	/**
	 * Constructor de los objetos comentables
	 * @param title titulo del objeto
	 */
	public CommentableObject(String title) {
		super(title);
		this.commentList = new ArrayList<Comment>();
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
		User u = sis.getLoggedUser();
		
		if(text.length() > 0 && u != null) {
			this.commentList.add(new Comment(text, u));
			return Status.OK;
		}
		
		return Status.ERROR;
	}
	
	/**
	 * Permite borrar comentarios
	 * @param text del comentario que queremos borrar
	 * @return status de la operacion
	 */
	public Status removeComment(String text) {
		Sistem sis = Sistem.getInstance();
		
		for(Comment com : this.commentList) {
			if(text.contentEquals(com.getText()) && 
					(com.getAuthor() == sis.getLoggedUser() || sis.adminIsLogged())){
				this.commentList.remove(com);
				return Status.OK;
			}
		}
		
		return Status.ERROR;
	}
}
