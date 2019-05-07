package padsof.swing;

import java.util.List;

import padsof.interactions.Report;

@SuppressWarnings("serial")
public class ScrollableJTableReport extends ScrollableJTable<Report> {
	private static String[] titulos = {"Cancion", "Usuario"};
		
	public ScrollableJTableReport() {
		super(titulos);
	}

	public ScrollableJTableReport(int ancho, int altura) {
		super(titulos, ancho, altura);
	}
	
	@Override
	public void insertSingle(Report po) {
		objectList.add(po);
		tableModel.addRow(new Object[]{
				po.getReportedSong().getTitle(),
				po.getReporter()
		});
	}
	
	@Override
	public void insertMultiple(List<? extends Report> pos) {
		for (Report po : pos) {
			insertSingle(po);
		}
	}

}
