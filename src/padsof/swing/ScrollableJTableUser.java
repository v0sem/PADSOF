package padsof.swing;

import java.util.List;

import padsof.user.User;

/**
 * Panel de tabla con scroll para insercion de usuarios
 *  
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
@SuppressWarnings("serial")
public class ScrollableJTableUser extends ScrollableJTable<User> {
	private static String[] titulos = {"Nombre", "Nick"};

	/**
	 * Constructor de la tabla
	 */
	public ScrollableJTableUser() {
		super(titulos);
	}

	/**
	 * Constructor de la tabla
	 * @param ancho Anchura de la tabla
	 * @param altura Altura de la tabla
	 */
	public ScrollableJTableUser(int ancho, int altura) {
		super(titulos, ancho, altura);
	}
	
	/**
	 * Insertar un solo usuario
	 */
	@Override
	public void insertSingle(User po) {
		objectList.add(po);
		tableModel.addRow(new Object[]{
				po.getName(),
				po.getNick()
		});
	}
	
	/**
	 * Insertar una lista de usuarios
	 */
	@Override
	public void insertMultiple(List<? extends User> pos) {
		for (User po : pos) {
			insertSingle(po);
		}
	}

}
