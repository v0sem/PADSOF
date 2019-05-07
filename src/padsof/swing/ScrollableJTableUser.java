package padsof.swing;

import java.util.List;

import padsof.user.User;

@SuppressWarnings("serial")
public class ScrollableJTableUser extends ScrollableJTable<User> {
	private static String[] titulos = {"Nombre", "Nick"};

	public ScrollableJTableUser() {
		super(titulos);
	}

	public ScrollableJTableUser(int ancho, int altura) {
		super(titulos, ancho, altura);
	}
	
	@Override
	public void insertSingle(User po) {
		objectList.add(po);
		tableModel.addRow(new Object[]{
				po.getName(),
				po.getNick()
		});
	}
	
	@Override
	public void insertMultiple(List<? extends User> pos) {
		for (User po : pos) {
			insertSingle(po);
		}
	}

}
