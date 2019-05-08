package padsof.swing;

import java.util.List;

import padsof.interactions.Notification;

/**
 * Panel de tabla con scroll para insercion de notificaciones
 *  
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
@SuppressWarnings("serial")
public class ScrollableJTableNotification extends ScrollableJTable<Notification> {
	private static String[] titulos = {};

	/**
	 * Constructor de la tabla
	 */
	public ScrollableJTableNotification() {
		super(titulos);
	}

	/**
	 * Constructor de la tabla
	 * @param ancho Anchura de la tabla
	 * @param altura Altura de la tabla
	 */
	public ScrollableJTableNotification(int ancho, int altura) {
		super(titulos, ancho, altura);
	}
	
	/**
	 * Insertar una sola notificacion 
	 */
	@Override
	public void insertSingle(Notification po) {
		objectList.add(po);
		tableModel.addRow(new Object[]{
				po.getNotificationText()
		});
	}
	
	/**
	 * Insertar una lista de notificaciones
	 */
	@Override
	public void insertMultiple(List<? extends Notification> pos) {
		for (Notification po : pos) {
			insertSingle(po);
		}
	}

}
