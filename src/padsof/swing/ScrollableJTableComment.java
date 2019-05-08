package padsof.swing;

import java.util.List;

import padsof.interactions.Comment;

/**
 * Panel de tabla con scroll para insercion de comentarios
 *  
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
@SuppressWarnings("serial")
public class ScrollableJTableComment extends ScrollableJTable<Comment> {
	private static String[] titulos = {"Autor", "Comentario"};
	
	public ScrollableJTableComment(int ancho, int altura) {
		super(titulos, ancho, altura);
	}
	
	public ScrollableJTableComment() {
		super(titulos);
	}

	@Override
	public void insertSingle(Comment a) {
		objectList.add(a);
		tableModel.addRow(new Object[]{
				a.getAuthor().getNick(), a.getText()
		});
	}

	@Override
	public void insertMultiple(List<? extends Comment> a) {
		for (Comment com : a) {
			insertSingle(com);
		}
	}

}
