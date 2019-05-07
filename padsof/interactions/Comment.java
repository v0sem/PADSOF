package padsof.interactions;

import padsof.user.User;

/**
 * Comentarios realizados por los ususarios
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
public class Comment {
	/**
	 * Constructor de clase comentario
	 * 
	 * @param txt  Contenido del comentario
	 * @param auth Autor
	 */
	public Comment(String txt, User auth) {

		this.commentText = txt;
		this.commentAuthor = auth;
	}

	/**
	 * Texto contenido del comentario
	 */
	private String commentText;

	/**
	 * Autor del comentario
	 */
	private User commentAuthor;

	/*************** GETTERS ********************/

	/**
	 * Getter del contenido
	 * 
	 * @return contenido del comentario
	 */
	public String getText() {

		return this.commentText;
	}

	/**
	 * Getter del autor
	 * 
	 * @return autor del comentario
	 */
	public User getAuthor() {

		return this.commentAuthor;
	}
}
