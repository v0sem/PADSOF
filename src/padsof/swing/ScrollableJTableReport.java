package padsof.swing;

import java.util.List;

import padsof.interactions.Report;

/**
 * Panel de tabla con scroll para insercion de reportes
 *  
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
@SuppressWarnings("serial")
public class ScrollableJTableReport extends ScrollableJTable<Report> {
	private static String[] titulos = {"Cancion", "Usuario"};
		
	/**
	 * Constructor de la tabla
	 */
	public ScrollableJTableReport() {
		super(titulos);
	}

	/**
	 * Constructor de la tabla
	 * @param ancho Anchura de la tabla
	 * @param altura Altura de la tabla
	 */
	public ScrollableJTableReport(int ancho, int altura) {
		super(titulos, ancho, altura);
	}
	
	/**
	 * Insertar un solo usuario
	 */
	@Override
	public void insertSingle(Report po) {
		objectList.add(po);
		tableModel.addRow(new Object[]{
				po.getReportedSong().getTitle(),
				po.getReporter()
		});
	}
	
	/**
	 * Insertar una lista de reportes
	 */
	@Override
	public void insertMultiple(List<? extends Report> pos) {
		for (Report po : pos) {
			insertSingle(po);
		}
	}

}
