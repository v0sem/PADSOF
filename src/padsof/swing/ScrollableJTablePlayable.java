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

	public ScrollableJTablePlayable() {
		super(titulos);
	}

	public ScrollableJTablePlayable(int ancho, int altura) {
		super(titulos, ancho, altura);
	}
	
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
