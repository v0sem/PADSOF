package padsof.swing;

import java.util.List;

import padsof.interactions.Notification;

@SuppressWarnings("serial")
public class ScrollableJTableNotification extends ScrollableJTable<Notification> {
	private static String[] titulos = {};

	public ScrollableJTableNotification() {
		super(titulos);
	}

	public ScrollableJTableNotification(int ancho, int altura) {
		super(titulos, ancho, altura);
	}
	
	@Override
	public void insertSingle(Notification po) {
		objectList.add(po);
		tableModel.addRow(new Object[]{
				po.getNotificationText()
		});
	}
	
	@Override
	public void insertMultiple(List<? extends Notification> pos) {
		for (Notification po : pos) {
			insertSingle(po);
		}
	}

}
