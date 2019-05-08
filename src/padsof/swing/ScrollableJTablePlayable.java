package padsof.swing;

import java.util.List;

import padsof.playable.PlayableObject;

/**
 * Panel de tabla con scroll para insercion de objetos
 * reproducibles
 *  
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
@SuppressWarnings("serial")
public class ScrollableJTablePlayable extends ScrollableJTable<PlayableObject> {
	private static String[] titulos = {"Titulo", "Autor", "Duracion"};

	/**
	 * Constructor de la tabla
	 */
	public ScrollableJTablePlayable() {
		super(titulos);
	}

	/**
	 * Constructor de la tabla
	 * @param ancho Anchura de la tabla
	 * @param altura Altura de la tabla
	 */
	public ScrollableJTablePlayable(int ancho, int altura) {
		super(titulos, ancho, altura);
	}

	/**
	 * Insertar un solo reproducible
	 */
	@Override
	public void insertSingle(PlayableObject po) {
		objectList.add(po);
		tableModel.addRow(new Object[]{
				po.getTitle(),
				po.getAuthor().getName(),
				po.getLength()
		});
	}
	
	@Override
	public void insertMultiple(List<? extends PlayableObject> pos) {
		for (PlayableObject po : pos) {
			insertSingle(po);
		}
	}

}
